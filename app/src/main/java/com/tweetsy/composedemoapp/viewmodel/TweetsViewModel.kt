package com.tweetsy.composedemoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tweetsy.composedemoapp.model.Tweet
import com.tweetsy.composedemoapp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(val repository: TweetRepository): ViewModel() {

    val tweets: StateFlow<List<Tweet>>
        get() = repository.tweet

    init {
        viewModelScope.launch {
            repository.getTweets("motivation")
        }
    }

}