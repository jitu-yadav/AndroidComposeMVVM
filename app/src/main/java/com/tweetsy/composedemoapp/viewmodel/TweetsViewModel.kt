package com.tweetsy.composedemoapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tweetsy.composedemoapp.model.Tweet
import com.tweetsy.composedemoapp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    val repository: TweetRepository,
    val savedSateHandle: SavedStateHandle): ViewModel() {

    val tweets: StateFlow<List<Tweet>>
        get() = repository.tweet

    init {
        viewModelScope.launch {
            val category = savedSateHandle.get<String>("category") ?: "android"
            repository.getTweets(category)
        }
    }

    fun getTweets(category: String) {
        viewModelScope.launch {
            repository.getTweets(category)
        }
    }

}