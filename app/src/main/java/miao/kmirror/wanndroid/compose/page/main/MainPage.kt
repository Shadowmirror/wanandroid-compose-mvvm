package miao.kmirror.wanndroid.compose.page.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import miao.kmirror.wanndroid.compose.viewmodel.main.MainViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.material3.CircularProgressIndicator as CircularProgressIndicator1

@Composable
fun MainPage(
    navHostController: NavHostController,
    mainViewModel: MainViewModel = koinViewModel()
) {
    val articleList by remember { derivedStateOf { mainViewModel.articleList } }
    val listState = rememberLazyListState()


    LaunchedEffect(Unit) {
        if (articleList.isEmpty()) {
            Log.i("KmirrorTag", "MainPage: mainViewModel.initData()")
            mainViewModel.initData()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomBanner()
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
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
}


val imgList = listOf(
    "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
    "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
    "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png"
)

@Preview(showBackground = true)
@Composable
fun CustomBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        val pagerState = rememberPagerState { 3 }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            SubcomposeAsyncImage(
                model = imgList[pageIndex],
                loading = {
                    CircularProgressIndicator1()
                },
                contentDescription = null
            )
        }
    }
}


