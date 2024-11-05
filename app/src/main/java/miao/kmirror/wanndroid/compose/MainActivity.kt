package miao.kmirror.wanndroid.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.network.WanAndroidApiService
import miao.kmirror.wanndroid.compose.ui.theme.WanAndroidComposeTheme
import miao.kmirror.wanndroid.compose.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val exampleApi = WanAndroidApiService.getWanAndroidApi()
        lifecycleScope.launch{
            val response = exampleApi.getBanner()
            Log.i("Kmirror", "onCreate: response = ${response}")
        }
        setContent {
            WanAndroidComposeTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent(
    viewModel: MainViewModel = viewModel()
) {

}
