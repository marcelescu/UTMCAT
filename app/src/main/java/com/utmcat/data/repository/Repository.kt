package com.utmcat.data.repository

import com.utmcat.data.api.ApiHelper

class Repository(private val apiHelper: ApiHelper) {

    suspend fun getStudenti() = apiHelper.getStudenti()
}