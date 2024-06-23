package miao.kmirror.wanandroid.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import miao.kmirror.wanandroid.base.BaseActivity
import miao.kmirror.wanandroid.ui.navigation.base.WanAndroidScreen


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            WanAndroidScreen(navHostController = navHostController)
        }
    }
}

