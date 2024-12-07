package miao.kmirror.wanndroid.compose.page.main

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.page.webview.FullScreenWebViewDialog
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


@Composable
fun CustomBanner(mainViewModel: MainViewModel = koinViewModel()) {
    val bannerList by remember { derivedStateOf { mainViewModel.bannerList } }
    var showDialog by remember { mutableStateOf(false) }

    if (bannerList.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            val pagerState = rememberPagerState { bannerList.size }
            val nowPageIndex = pagerState.currentPage
            val scope = rememberCoroutineScope()
            LaunchedEffect(pagerState.settledPage) {
                delay(3000)
                val scroller =
                    if (pagerState.currentPage + 1 == bannerList.size) 0 else pagerState.currentPage + 1
                pagerState.animateScrollToPage(scroller)
            }
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 50.dp),
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->

                val imgScale by animateFloatAsState(
                    targetValue = if (nowPageIndex == pageIndex) 1f else 0.8f,
                    animationSpec = tween(300), label = ""
                )
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .scale(imgScale)
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            showDialog = true
                        },
                    model = bannerList[pageIndex].imagePath,
                    loading = {
                        CircularProgressIndicator1()
                    },
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                bannerList.indices.forEach { radioIndex ->
                    RadioButton(selected = nowPageIndex == radioIndex, onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(radioIndex)
                        }
                    })
                }
            }

            if (showDialog) {
                FullScreenWebViewDialog(bannerList[nowPageIndex].url) {
                    showDialog = false
                }
            }
        }
    }
}


