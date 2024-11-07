package miao.kmirror.wanndroid.compose.page.maintab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import miao.kmirror.wanndroid.compose.page.main.MainPage
import miao.kmirror.wanndroid.compose.page.square.SquarePage
import miao.kmirror.wanndroid.compose.page.tree.TreePage


val tabItems = arrayListOf(NavMain, NavTree, NavSquare)

@Composable
fun MainTabPage(
    navHostController: NavHostController,
) {
    val mainNavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                val navBackStackEntry by mainNavHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                tabItems.forEach { tabItem ->
                    NavigationBarItem(
                        selected = currentDestination?.route == tabItem::class.qualifiedName,
                        onClick = {
                            mainNavHostController.navigate(route = tabItem) {
                                // 这里让多个Tab下返回时，不是回到首页，而是直接退出
                                currentDestination?.id?.let {
                                    popUpTo(it) {
                                        // 跳转时保存页面状态
                                        saveState = true
                                        // 回退到栈顶时，栈顶页面是否也关闭
                                        inclusive = true
                                    }
                                }
                                // 栈顶复用，避免重复点击同一个导航按钮，回退栈中多次创建实例
                                launchSingleTop = true
                                // 回退时恢复页面状态
                                restoreState = true
                            }
                        },
                        icon = {
                            when (tabItem) {
                                NavMain -> Icon(Icons.Default.Home, contentDescription = null)
                                NavTree -> Icon(Icons.Default.Place, contentDescription = null)
                                NavSquare -> Icon(Icons.Default.Notifications, contentDescription = null)
                            }
                        }
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValue ->
        NavHost(
            navController = mainNavHostController,
            startDestination = NavMain,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable<NavMain> {
                MainPage(navHostController)
            }
            composable<NavTree> {
                TreePage(navHostController)
            }
            composable<NavSquare> {
                SquarePage(navHostController)
            }
        }
    }
}


@Serializable
object NavMain

@Serializable
object NavSquare

@Serializable
object NavTree