package com.tweetsy.composedemoapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tweetsy.composedemoapp.viewmodel.TweetsViewModel

@Composable
fun DetailScreen(innerPadding: PaddingValues) {
    val tweetsViewModel: TweetsViewModel = hiltViewModel()

  /*  // Fetch tweets based on the category not called on recomposition of this function
    LaunchedEffect(category) {
        tweetsViewModel.getTweets(category)
    }*/

    val tweets = tweetsViewModel.tweets.collectAsState()

    if (tweets.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Loading...",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            content = {
                items(tweets.value) {
                    TweetListItem(tweet = it.text)
                }
            })
    }
}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(Color.Black),
        border = BorderStroke(1.dp, Color.Red),


    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            color = Color.White
            )
    }

}