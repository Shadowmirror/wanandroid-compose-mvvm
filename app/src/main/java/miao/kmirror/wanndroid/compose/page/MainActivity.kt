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
import kotlinx.serialization.Serializable
import miao.kmirror.wanndroid.compose.page.maintab.MainTabPage
import miao.kmirror.wanndroid.compose.page.setting.SettingPage
import miao.kmirror.wanndroid.compose.page.splash.SplashPage
import org.koin.androidx.compose.KoinAndroidContext


class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                navHostController = rememberNavController()
                MainContent(navHostController = navHostController)
            }
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
        composable<NavMainTab> {
            MainTabPage(navHostController)
        }
        composable<NavSetting> {
            SettingPage(navHostController)
        }
    }
}


@Serializable
object NavSplash

@Serializable
object NavMainTab

@Serializable
object NavSetting


