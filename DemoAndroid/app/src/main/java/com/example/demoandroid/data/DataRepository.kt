package com.example.demoandroid.data

import com.example.demoandroid.data.io.ApiService
import javax.inject.Inject

/**
 * Repository class for handling data from network
 */
class DataRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDataList(query: String) = apiService.getAllData(query)
}