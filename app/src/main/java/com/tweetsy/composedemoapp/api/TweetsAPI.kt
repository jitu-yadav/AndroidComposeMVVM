package com.tweetsy.composedemoapp.api

import com.tweetsy.composedemoapp.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsAPI {

    @GET("/v3/b/68ba7a1dae596e708fe31b84?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<Tweet>>


    @GET("/v3/b/68ba7a1dae596e708fe31b84?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories() : Response<List<String>>
}