package miao.kmirror.wanndroid.compose.page.tree

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.network.bean.TreeDTO
import miao.kmirror.wanndroid.compose.viewmodel.tree.TreeViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * 页面设计想法本来是想实现内容可以左右滑动的, 现在卡在了如何在滑动的时候去更新 TreeDetailPage 那就暂时先不滑动了
 * */

@Composable
fun TreePage(
//    navHostController: NavHostController,
    treeViewModel: TreeViewModel = koinViewModel()
) {
    val collectAsState by treeViewModel.pageState.collectAsState()
    val treeBeanList by remember { derivedStateOf { treeViewModel.treeDTOList } }

    LaunchedEffect(Unit) {
        if (treeBeanList.isEmpty()) {
            Log.i("KmirrorTag", "MainPage: treeViewModel.initData()")
            treeViewModel.initData()
        }
    }

    if (collectAsState) {
        val pagerState = rememberPagerState(pageCount = { treeBeanList.size })
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
                treeBeanList.forEachIndexed { index, parentDir ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(index, 0f)
                            }
                        },
                        text = { Text(parentDir.name) }
                    )
                }
            }

            HorizontalPager(
                userScrollEnabled = false,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                SecondaryTabScreen(page, treeBeanList)
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("请稍后")
        }
    }

}


@Composable
fun SecondaryTabScreen(parentPage: Int, treeDTOList: SnapshotStateList<TreeDTO>) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { treeDTOList[parentPage].children.size })

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            modifier = Modifier.padding(16.dp),
            indicator = { tabPosition ->
                TabRowDefaults.PrimaryIndicator(
                    Modifier.tabIndicatorOffset(tabPosition[pagerState.currentPage])
                )
            }

        ) {
            treeDTOList[parentPage].children.forEachIndexed { index, childDir ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index, 0f)
                        }
                    },
                    text = { Text(childDir.name) }
                )
            }
        }

        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            TreeDetailPage(treeDTOList[parentPage].children[page].id)
        }
    }
}