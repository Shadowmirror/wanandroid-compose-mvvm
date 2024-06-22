package miao.kmirror.wanandroid.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import miao.kmirror.wanandroid.base.BaseActivity
import miao.kmirror.wanandroid.http.WanAndroidService
import miao.kmirror.wanandroid.ui.theme.WanandroidTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var mWanAndroidService: WanAndroidService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WanandroidTheme {

            }
        }
    }
}

