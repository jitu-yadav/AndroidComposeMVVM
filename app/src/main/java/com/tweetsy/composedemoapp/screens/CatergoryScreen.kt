package com.tweetsy.composedemoapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tweetsy.composedemoapp.viewmodel.CategoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tweetsy.composedemoapp.R

@Composable
fun CatergoryScreen() {
    val catergoryViewModel: CategoryViewModel = viewModel()
    val categories: State<List<String>> = catergoryViewModel.categories.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(categories.value) {
            CategoryItem(it)
        }
        // Grid content
    }
}


@Composable
fun CategoryItem(catergory: String) {
    Box(modifier = Modifier
        .size(120.dp)
        .padding(4.dp)
        .clip(RoundedCornerShape(8.dp))
        .paint(painter = painterResource(id = R.drawable.bg),
            contentScale = ContentScale.Crop)
        .border(1.dp, Color.Gray),
        contentAlignment = Alignment.BottomCenter) {

        Text(text = catergory,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 16.dp))
    }
}