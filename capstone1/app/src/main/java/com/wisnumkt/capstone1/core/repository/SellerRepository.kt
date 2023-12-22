package com.wisnumkt.capstone1.core.repository

import com.wisnumkt.capstone1.core.api.ApiService
import com.wisnumkt.capstone1.core.model.Seller
import com.wisnumkt.capstone1.core.model.local.ListSeller
import com.wisnumkt.capstone1.core.model.remote.DataItem
import com.wisnumkt.capstone1.core.model.remote.RekomResponse
import retrofit2.Response

class SellerRepository (private val apiService: ApiService) {
    suspend fun fetchRekom(): Response<RekomResponse> {
        return apiService.searchRekom()
    }

    suspend fun searchRekom(query: String): List<DataItem> {
        try {
            val rekomResponse = fetchRekom()

            if (rekomResponse.isSuccessful) {
                val rekomData = rekomResponse.body()?.data

                if (rekomData != null) {
                    return rekomData.filter {
                        it.name.contains(query, ignoreCase = true)
                    }
                }
            } else {
                // Handle the error or log it
            }
        } catch (e: Exception) {
            // Handle the exception or log it
        }

        // Return an empty list or handle the absence of data based on your requirements
        return emptyList()
    }

    suspend fun getRekomById(rekomId: String): DataItem {
        // Fetch data using fetchRekom
        val rekomResponse = fetchRekom()

        // Assuming RekomResponse contains a list of foods, replace it with the actual structure
        val foodsList = rekomResponse.body()?.data ?: emptyList()

        // Find the food by id in the fetched list
        return foodsList.firstOrNull { it.id == rekomId }
            ?: throw NoSuchElementException("Food with id $rekomId not found")
    }
}