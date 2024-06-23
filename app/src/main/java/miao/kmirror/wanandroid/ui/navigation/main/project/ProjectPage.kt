package miao.kmirror.wanandroid.ui.navigation.main.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun ProjectPage(
    viewModel: ProjectPageViewModel = hiltViewModel()
) {
    val rememberLazyListState = rememberLazyListState()
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope() // 这里不能删除, 这里删除了切换 Navigation 会导致 UI 重组
    LaunchedEffect(rememberLazyListState) {
        if (uiState.data.isNullOrEmpty()) {
            viewModel.getProjectArticlePageList(true)
        }
    }

    if (uiState.data.isNullOrEmpty()){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }else{
        LazyColumn(
            state = rememberLazyListState
        ) {
            uiState.data?.let { data ->
                itemsIndexed(data) { index, item ->
                    Text(
                        text = "Project: " + item.title,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 50.dp)
                    )

                    if (index == data.size - 5) {
                        LaunchedEffect(Unit) {
                            coroutineScope.launch {
                                viewModel.getProjectArticlePageList(false)
                            }
                        }
                    }
                }
            }

        }
    }


}