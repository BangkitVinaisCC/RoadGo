package com.wisnumkt.capstone1.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.wisnumkt.capstone1.core.model.Seller
import com.wisnumkt.capstone1.core.model.remote.DataItem
import com.wisnumkt.capstone1.core.repository.SellerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SellerRepository) : ViewModel() {
    private val _groupedHeroes = MutableStateFlow<Map<Char, List<DataItem>>>(emptyMap())
    private val _rekomById = MutableStateFlow(DataItem("","","","","",""))
    val rekomById: StateFlow<DataItem> = _rekomById.asStateFlow()

    val listSeller: StateFlow<Map<Char, List<DataItem>>> get() = _groupedHeroes

    init {
        viewModelScope.launch {
            loadRekom()
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private suspend fun loadRekom() {
        try {
            val rekomResponse = repository.fetchRekom()

            if (rekomResponse.isSuccessful) {
                val heroes = rekomResponse.body()?.data

                if (heroes != null) {
                    _groupedHeroes.value = heroes
                        .sortedBy { it.name }
                        .groupBy { it.name[0] }
                }
            } else {
                // Handle the error or log it
            }
        } catch (e: Exception) {
            Log.e("YourViewModel", "Exception: ${e.message}")
        }
    }

    fun search(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            _groupedHeroes.value = repository.searchRekom(newQuery)
                .sortedBy { it.name }
                .groupBy { it.name[0] }
        }
    }

    // Function to fetch food by ID
    fun fetchRekomById(rekomId: String) {
        // Launch a coroutine in the viewModelScope
        viewModelScope.launch {
            try {
                // Call the suspend function to get the food by ID
                val rekom = repository.getRekomById(rekomId)
                // Update the LiveData with the result
                _rekomById.value = rekom
            } catch (e: Exception) {
                // Handle errors or exceptions here
                // You might want to update another LiveData for error handling
                // _errorLiveData.value = "Error fetching food: ${e.message}"
            }
        }
    }
}

class ViewModelFactory(private val repository: SellerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}