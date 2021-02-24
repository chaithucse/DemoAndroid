package com.example.demoandroid.data.io

import com.example.demoandroid.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/?format=json")
    suspend fun getAllData(@Query("q", encoded = true) q: String): UserResponse
}