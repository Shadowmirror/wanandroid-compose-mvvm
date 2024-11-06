package miao.kmirror.wanndroid.compose.page.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.viewmodel.main.MainViewModel

@Composable
fun MainPage(
    navHostController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val articleList by remember { derivedStateOf { mainViewModel.articleList } }

    LaunchedEffect(Unit) {
        mainViewModel.initData()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(articleList) { index, item ->
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(width = 1.dp, color = Color.Black)
                    .padding(vertical = 3.dp)
            )

            if (index == articleList.size - 5) {
                LaunchedEffect(Unit) {
                    mainViewModel.loadArticleBanner()
                }
            }
        }
    }
}