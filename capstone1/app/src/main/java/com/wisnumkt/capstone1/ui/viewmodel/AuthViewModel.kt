package com.wisnumkt.capstone1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wisnumkt.capstone1.core.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _isAuthenticated = MutableStateFlow(false)
    private val _name = MutableStateFlow("Zaky")
    val firestore: FirebaseFirestore = Firebase.firestore
    val name: StateFlow<String> = _name.asStateFlow()

    init {
        Log.d("AuthViewModel", "ViewModel created")
    }

    fun searchName(email: String) {
        viewModelScope.launch {
            val name = withContext(Dispatchers.IO) {
                getNameByEmail(email)
            }
            if (name != null) {
                println("Nama: $name")
                _name.value = name
            } else {
                println("No name found for the specified email.")
            }
        }
    }

    suspend fun getNameByEmail(email: String): String? {
        val firestore = FirebaseFirestore.getInstance()
        val usersCollection = firestore.collection("users")

        return try {
            val querySnapshot = usersCollection.whereEqualTo("email", email).get().await()

            if (!querySnapshot.isEmpty) {
                // Assuming there is only one document with the specified email
                val userDocument = querySnapshot.documents.first()
                userDocument.getString("name")
            } else {
                // No document found with the specified email
                null
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., network issues, Firestore errors)
            null
        }
    }

    fun asyncLogin(
        email: String,
        password: String,
        navController: NavController?
    ) {
        viewModelScope.launch {
            login(email, password, navController)
        }
    }


    suspend fun login(email: String, password: String, navController: NavController?) {
        try {
            // Menggunakan Firebase untuk otentikasi
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            // Jika otentikasi berhasil
            _isAuthenticated.value = result.user != null

            if (_isAuthenticated.value) {
                navController?.navigate("main")
            } else {
                // Login gagal
                handleLoginFailure("User tidak ditemukan")
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            // Tangani kesalahan jika user tidak ditemukan
            handleLoginFailure("User tidak ditemukan")
        } catch (e: Exception) {
            // Tangani kesalahan Firebase atau kegagalan login
            handleLoginFailure("Gagal login: ${e.message}")
        }
    }

    fun asyncRegister(
        name: String,
        email: String,
        password: String,
        navController: NavController?
    ) {
        viewModelScope.launch {
            register(name, email, password, navController)
        }
    }

    suspend fun register(
        name: String,
        email: String,
        password: String,
        navController: NavController?
    ) {
        try {
            // Menggunakan Firebase untuk otentikasi
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            // Jika otentikasi berhasil
            _isAuthenticated.value = result.user != null

            if (_isAuthenticated.value) {
                val userId = result.user?.uid
                val user = User(name, email)
                userId?.let { firestore.collection("users").document(it).set(user) }
                navController?.navigate("main")
            } else {
                // Login gagal
                handleLoginFailure("Gagal mendaftar")
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            // Tangani kesalahan jika user tidak ditemukan
            handleLoginFailure("Email udah terdaftar")
        } catch (e: Exception) {
            // Tangani kesalahan Firebase atau kegagalan login
            handleLoginFailure("Gagal mendaftar: ${e.message}")
        }
    }

    private fun handleLoginFailure(errorMessage: String) {
        // Lakukan sesuatu dengan pesan kesalahan, misalnya, tampilkan di antarmuka pengguna
        println(errorMessage)
    }

}
