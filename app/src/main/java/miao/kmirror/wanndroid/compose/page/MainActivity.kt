package miao.kmirror.wanndroid.compose.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import miao.kmirror.wanndroid.compose.page.main.MainPage
import miao.kmirror.wanndroid.compose.page.splash.SplashPage


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            MainContent(navHostController = navHostController)
        }
    }
}


@Composable
fun MainContent(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavSplash,
    ) {
        composable<NavSplash> {
            SplashPage(navHostController)
        }
        composable<NavMain> {
            MainPage(navHostController)
        }
    }
}


@Serializable
object NavSplash

@Serializable
object NavMain



