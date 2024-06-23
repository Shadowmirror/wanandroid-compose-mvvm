package miao.kmirror.wanandroid.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import miao.kmirror.wanandroid.ui.navigation.main.home.HomePage
import miao.kmirror.wanandroid.ui.navigation.main.project.ProjectPage


@Composable
fun NavGraph(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Route.HOME,
        modifier = Modifier.padding(paddingValues),
        // 禁用进入和退出动画
        enterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(0))
        }

    ) {
        composable(Route.HOME){
            HomePage()
        }
        composable(Route.PROJECT){
            ProjectPage()
        }
    }
}

object Route {
    const val HOME = "home"
    const val PROJECT = "project"
}
