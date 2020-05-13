package com.utmcat.data.api

import com.utmcat.data.model.Student
import retrofit2.http.GET

interface ApiService {
    @GET("studenti")
    suspend fun getStudenti(): List<Student>
}