package com.example.newzy.repositories

import com.example.newzy.network.WebService

/*
* Repository for Top-Headlines endpoint to communicate with Internet Network.
* This class is used for implementing MVVM pattern.
*/
class HeadlinesRepository(
    private val api : WebService
) : SafeApiRequest() {

    private val countryName : String = "us"

    suspend fun getHeadlines() = apiRequest {
        api.getHeadlines(countryName, WebService.apiKey)
    }

}