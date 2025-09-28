package com.tweetsy.composedemoapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tweetsy.composedemoapp.viewmodel.CategoryViewModel
import com.tweetsy.composedemoapp.R
import java.util.Locale

@Composable
fun CatergoryScreen(paddingValues: PaddingValues, onClick: (category: String) -> Unit) {
    val catergoryViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = catergoryViewModel.categories.collectAsState()

    //For Empty list show loading
    if (categories.value.isEmpty()) {
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
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value) {
                CategoryItem(it, onClick)
            }
            // Grid content
        }
    }
}


@Composable
fun CategoryItem(catergory: String, onClick: (category: String) -> Unit) {
    Box(modifier = Modifier
        .size(120.dp)
        .padding(4.dp)
        .clickable{
            onClick(catergory)
        }
        .clip(RoundedCornerShape(16.dp))
        .paint(painter = painterResource(id = R.drawable.bg),
            contentScale = ContentScale.Crop)
        .border(1.dp, Color.Gray),
        contentAlignment = Alignment.BottomCenter) {

        Text(text = catergory.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 16.dp))
    }
}