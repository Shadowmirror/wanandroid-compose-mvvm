package miao.kmirror.wanndroid.compose.page.tree

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.viewmodel.tree.ParentDir

@Composable
fun TreePage(
//    navHostController: NavHostController,
) {

    val pagerState = rememberPagerState(pageCount = { ParentDir.testData.size })
    val scope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.padding(16.dp),
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.PrimaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            ParentDir.testData.forEachIndexed { index, parentDir ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index, 0f)
                        }
                    },
                    text = { Text(parentDir.desc) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            SecondaryTabScreen(page)
        }
    }
}


@Composable
fun SecondaryTabScreen(parentPage: Int) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { ParentDir.testData[parentPage].childDirList.size })

    Column {
        ScrollableTabRow (
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            modifier = Modifier.padding(16.dp),
            indicator = {tabPosition ->
                TabRowDefaults.PrimaryIndicator(
                    Modifier.tabIndicatorOffset(tabPosition[pagerState.currentPage])
                )
            }
        ) {
            ParentDir.testData[parentPage].childDirList.forEachIndexed { index, childDir ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index, 0f)
                        }
                    },
                    text = { Text(childDir.desc) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Text(
                text = "页面 ${ParentDir.testData[parentPage].childDirList[page].desc}",
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
