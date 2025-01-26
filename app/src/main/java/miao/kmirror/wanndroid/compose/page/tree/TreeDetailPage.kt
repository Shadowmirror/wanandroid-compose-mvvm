package miao.kmirror.wanndroid.compose.page.tree

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import miao.kmirror.wanndroid.compose.page.main.CardItem
import miao.kmirror.wanndroid.compose.viewmodel.tree.TreeDetailViewModel
import org.koin.androidx.compose.koinViewModel


/**
 * 就是这个页面不知道怎么更新
 * */
@Composable
fun TreeDetailPage(
    cid: Int,
    treeDetailViewModel: TreeDetailViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        Log.i("KmirrorTag", "MainPage: treeDetailViewModel.initData()")
        treeDetailViewModel.initData(cid)
    }

    val collectAsState by treeDetailViewModel.pageState.collectAsState()
    if (collectAsState && treeDetailViewModel.treeDetailMap[cid] != null) {
        treeDetailViewModel.treeDetailMap[cid]?.apply {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(articleDTOList) { index, item ->
                    CardItem(item)
                    if (index == articleDTOList.size - 5) {
                        LaunchedEffect(Unit) {
                            treeDetailViewModel.loadArticle()
                        }
                    }
                }
            }
        }

    }
}
