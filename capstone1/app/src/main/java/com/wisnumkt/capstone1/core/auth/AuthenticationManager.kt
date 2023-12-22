package com.wisnumkt.capstone1.core.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.wisnumkt.capstone1.core.model.User
import com.wisnumkt.capstone1.core.utils.Result
import kotlinx.coroutines.tasks.await

class AuthenticationManager {
    private val auth = Firebase.auth

    suspend fun register(email: String, password: String): Result<User> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.Success(User(result.user!!.uid, email))
        } catch (e: Exception) {
            Result.Error(e.toString())
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(User(result.user!!.uid, email))
        } catch (e: Exception) {
            Result.Error(e.toString())
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUser(): FirebaseUser? {
        return auth.currentUser
    }
}