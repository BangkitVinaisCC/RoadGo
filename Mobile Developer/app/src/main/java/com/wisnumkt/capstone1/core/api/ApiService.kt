package com.wisnumkt.capstone1.core.api

import com.wisnumkt.capstone1.core.model.remote.RekomResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("sellerProfile")
    suspend fun searchRekom(
    ): Response<RekomResponse>
}