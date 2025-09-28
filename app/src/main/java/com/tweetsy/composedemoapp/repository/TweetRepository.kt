package com.tweetsy.composedemoapp.repository

import com.tweetsy.composedemoapp.api.TweetsAPI
import com.tweetsy.composedemoapp.model.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(val api: TweetsAPI) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweet: StateFlow<List<Tweet>>
        get() = _tweets

    suspend fun getCategories() {
        val response = api.getCategories()
        if (response.isSuccessful) {
            _categories.value = response.body()?.distinct()!!
        } else {
            _categories.value = emptyList()
        }
    }

    suspend fun getTweets(category: String) {
        val response = api.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful) {
            _tweets.value = response.body()!!
        } else {
            _tweets.value = emptyList()
        }
    }
}