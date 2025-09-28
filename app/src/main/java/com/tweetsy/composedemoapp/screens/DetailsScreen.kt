package com.tweetsy.composedemoapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tweetsy.composedemoapp.viewmodel.TweetsViewModel

@Composable
fun DetailScreen() {
    val tweetsViewModel: TweetsViewModel = viewModel()
    val tweets = tweetsViewModel.tweets.collectAsState()

    LazyColumn(content = {
      items(tweets.value)  {
          TweetListItem(tweet = it.text)
      }
    })
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