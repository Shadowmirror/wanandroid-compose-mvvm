package miao.kmirror.wanandroid.ui.navigation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import miao.kmirror.wanandroid.ui.navigation.main.MainPage
import miao.kmirror.wanandroid.ui.navigation.splash.SplashPage

@Composable
fun WanAndroidScreen(
    navHostController: NavHostController,
    wanAndroidScreenViewModel: WanAndroidScreenViewModel = hiltViewModel()
){
    val isShowSplash by wanAndroidScreenViewModel.isShowSplash.collectAsState()


    if (isShowSplash){
        SplashPage {
            wanAndroidScreenViewModel.emitSplashStateChange(false)
        }
    }else{
        MainPage(navHostController)
    }
}