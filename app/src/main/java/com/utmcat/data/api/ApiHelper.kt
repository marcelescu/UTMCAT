package com.utmcat.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getStudenti() = apiService.getStudenti()
}