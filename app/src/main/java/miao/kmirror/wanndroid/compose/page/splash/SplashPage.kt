package miao.kmirror.wanndroid.compose.page.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import miao.kmirror.wanndroid.compose.page.MainActivity
import miao.kmirror.wanndroid.compose.page.NavMainTab
import miao.kmirror.wanndroid.compose.page.NavSplash

@Composable
fun SplashPage(navHostController: NavHostController) {
    var countdown by remember { mutableIntStateOf(1) }
    val current = LocalContext.current as MainActivity
    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000)
            if (current.hasWindowFocus()) {
                countdown--
            }

        }
        withContext(Dispatchers.Main) {
            navHostController.navigate(route = NavMainTab) {
                popUpTo(NavSplash) { inclusive = true }
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
    ) {
        Text("启动页, $countdown 秒后进入主页")
    }
}