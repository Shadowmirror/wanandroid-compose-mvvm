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
                NavigationBarItem(
                    selected = currentDestination?.route == NavMain::class.qualifiedName,
                    onClick = {
                        mainNavHostController.navigate(route = NavMain)
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Main") }
                )

                NavigationBarItem(
                    selected = currentDestination?.route == NavTree::class.qualifiedName,
                    onClick = { mainNavHostController.navigate(route = NavTree) },
                    icon = { Icon(Icons.Default.Place, contentDescription = "Tree") }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == NavSquare::class.qualifiedName,
                    onClick = { mainNavHostController.navigate(route = NavSquare) },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Square") }
                )
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