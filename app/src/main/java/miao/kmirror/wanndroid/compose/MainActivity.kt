package miao.kmirror.wanndroid.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.network.WanAndroidApi
import miao.kmirror.wanndroid.compose.network.WanAndroidApiService
import miao.kmirror.wanndroid.compose.ui.theme.WanAndroidComposeTheme
import miao.kmirror.wanndroid.compose.viewmodel.MainViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var wanAndroidApi: WanAndroidApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        miao()
        setContent {
            WanAndroidComposeTheme {
                MainContent()
            }
        }
    }

    fun miao(){
//        val exampleApi = WanAndroidApiService.getWanAndroidApi()
//        lifecycleScope.launch {
//            val response = exampleApi.getBanner()
//            Log.i("Kmirror", "onCreate: response = ${response}")
//        }
        
        lifecycleScope.launch {
            val banner = wanAndroidApi.getBanner()
            Log.i("Kmirror", "miao: banner = ${banner}")

        }
        
    }
}


@Composable
fun MainContent(
    viewModel: MainViewModel = viewModel()
) {

}
