package miao.kmirror.wanndroid.compose.page.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import miao.kmirror.wanndroid.compose.viewmodel.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainPage(
//    navHostController: NavHostController,
    mainViewModel: MainViewModel = koinViewModel()
) {
    val articleList by remember { derivedStateOf { mainViewModel.articleList } }
    val listState = rememberLazyListState()


    LaunchedEffect(Unit) {
        if (articleList.isEmpty()){
            Log.i("KmirrorTag", "MainPage: mainViewModel.initData()")
            mainViewModel.initData()
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(articleList) { index, item ->
            CardItem(item)
            if (index == articleList.size - 5) {
                LaunchedEffect(Unit) {
                    mainViewModel.loadArticle()
                }
            }
        }
    }
}


