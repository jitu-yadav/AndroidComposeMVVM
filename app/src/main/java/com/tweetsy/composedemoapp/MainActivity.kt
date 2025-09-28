package com.tweetsy.composedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tweetsy.composedemoapp.ui.theme.ComposeDemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoAppTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "register") {
        composable(route = "register") {
            Registeration { email ->
                navController.navigate("login/${email}")
            }
        }
        composable(route = "login/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )) {
            it.arguments?.getString("email")?.let { email ->
                Login(email) {
                    navController.navigate("main")
                }
            }
        }
        composable(route = "main") {
            MainScreen {
                navController.navigate("register")
            }
        }
    }
}


@Composable
fun Registeration(onClick: (email:String) -> Unit) {
    Box(modifier =
        Modifier.padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "Registeration",
            fontSize = 24.sp,
            modifier = Modifier.clickable {
                onClick("jituyadav3353@gmail.com")
            })
    }
}

@Composable
fun Login(email:String, onClick: () -> Unit) {
    Box(modifier =
        Modifier.padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "Login - $email",
            fontSize = 24.sp,
            modifier = Modifier.clickable {
                onClick()
            })
    }
}

@Composable
fun MainScreen(onClick: () -> Unit) {
    Box(modifier =
        Modifier.padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "MainScreen",
            fontSize = 24.sp,
            modifier = Modifier.clickable {
                onClick()
            })
    }
}