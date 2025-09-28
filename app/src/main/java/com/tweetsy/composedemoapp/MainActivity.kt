package com.tweetsy.composedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tweetsy.composedemoapp.screens.CatergoryScreen
import com.tweetsy.composedemoapp.screens.DetailScreen
import com.tweetsy.composedemoapp.ui.theme.ComposeDemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoAppTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = "Tweet App")
                    }, colors = TopAppBarColors(
                        containerColor = Color.Black,
                        scrolledContainerColor = Color.Black,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        subtitleContentColor = Color.White
                    )
                    )
                }) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.LightGray
                        )
                        .padding(innerPadding))
                    App(innerPadding)
                }
            }
        }
    }
}

@Composable
fun App(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "categories") {
        //Route1
        composable(route = "categories") {
            CatergoryScreen(innerPadding) {
                navController.navigate("detail/${it}")
            }
        }

        //Route2
        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )) {
            it.arguments?.getString("category")?.let { category ->
                DetailScreen(innerPadding)
            }
        }
    }
}

