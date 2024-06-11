package miao.kmirror.wanandroid.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import miao.kmirror.wanandroid.http.WanAndroidService
import miao.kmirror.wanandroid.ui.theme.WanandroidTheme
import miao.kmirror.wanandroid.utils.LogUtil
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mWanAndroidService: WanAndroidService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WanandroidTheme {

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val response = mWanAndroidService.getBanner()
            when (response.errorCode) {
                0 -> {
                    response.data.forEach {
                        LogUtil.d(it.title)
                    }
                }

                else -> {
                    LogUtil.e("Error fetching banners: ${response.errorCode}")
                }
            }
        }
    }
}

