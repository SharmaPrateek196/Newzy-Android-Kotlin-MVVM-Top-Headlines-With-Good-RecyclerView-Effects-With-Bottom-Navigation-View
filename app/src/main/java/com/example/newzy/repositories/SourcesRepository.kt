package com.example.newzy.repositories

import com.example.newzy.network.WebService

/*
The function inside this class will help us...
to call the apis and handle errors
*/
class SourcesRepository(private val api : WebService
) : SafeApiRequest() {

    suspend fun getSources() = apiRequest {
        api.getSources(WebService.apiKey)
    }

}