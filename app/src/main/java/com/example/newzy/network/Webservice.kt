package com.example.newzy.network

import com.example.newzy.dataObjects.HeadlinesResponse
import com.example.newzy.dataObjects.SourcesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/*
Interface containing api endpoints and
newtwork variables' initializations
*/
interface WebService {

    companion object {
        //API key from NewsAPI site
        const val apiKey = "b33b8eb887f3464f8497f9f76ea1559a"
        const val BASE_URL = "https://newsapi.org/v2/"

        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        var client = OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        operator fun invoke() : WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WebService::class.java)
        }
    }

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Response<HeadlinesResponse>

    @GET("sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String?
    ): Response<SourcesResponse>

}