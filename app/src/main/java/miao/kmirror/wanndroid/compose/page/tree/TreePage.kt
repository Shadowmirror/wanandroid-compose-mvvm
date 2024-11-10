package miao.kmirror.wanndroid.compose.page.tree

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import miao.kmirror.wanndroid.compose.viewmodel.tree.ParentDir
import miao.kmirror.wanndroid.compose.viewmodel.tree.TreeViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TreePage(
//    navHostController: NavHostController,
    treeViewModel: TreeViewModel = koinViewModel()
) {
    val pageState by treeViewModel.pageState.collectAsState()
    val parentDirIndex by treeViewModel.parentDirIndex.collectAsState()
    val childDirIndexList by treeViewModel.childDirIndexList.collectAsState()
    LaunchedEffect(Unit) {
        treeViewModel.initializeData()
    }

    if (pageState) {
        // 创建和管理 LazyListState
        val childDirListState = ParentDir.testData.map { parentDir ->
            parentDir.childDirList.map { rememberLazyListState() }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(ParentDir.testData) { index, item ->
                    Button(
                        onClick = {
                            treeViewModel.updateParentDirIndex(index)
                        },
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Text(
                            text = item.desc,
                        )
                    }
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(ParentDir.testData[parentDirIndex].childDirList) { index, item ->
                    Button(
                        onClick = {
                            treeViewModel.updateChildDirIndex(parentDirIndex, index)
                        },
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Text(text = item.desc)
                    }
                }
            }

            LazyColumn(
                state = childDirListState[parentDirIndex][childDirIndexList[parentDirIndex]],
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                val random = Random.nextInt(30, 40)
                items(random) { item ->
                    Text(
                        text = "${ParentDir.testData[parentDirIndex].childDirList[childDirIndexList[parentDirIndex]].desc}: 文章 $item",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )
                }
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("请稍后")
        }
    }
}
