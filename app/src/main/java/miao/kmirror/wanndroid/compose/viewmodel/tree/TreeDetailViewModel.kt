package miao.kmirror.wanndroid.compose.viewmodel.tree

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.network.bean.ArticleDTO
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TreeDetailViewModel(private val wanAndroidRepository: WanAndroidRepository) : ViewModel() {

    val treeDetailMap: MutableMap<Int, TreeDetailBean> = mutableMapOf()
    private val _pageState = MutableStateFlow(false)
    val pageState: StateFlow<Boolean> = _pageState

    private var cid = 0

    fun initData(cid: Int) {
        _pageState.value = false
        this.cid = cid
        if (!treeDetailMap.containsKey(cid)) {
            treeDetailMap[cid] = TreeDetailBean(0, mutableStateListOf(), lazyListState = LazyListState(0))
            loadArticle()
        } else {
            _pageState.value = true
        }
    }


    fun loadArticle() {
        viewModelScope.launch {
            treeDetailMap[cid]?.apply {
                if (!noMore) {
                    val responseData = wanAndroidRepository.getArticle(treeDetailMap[cid]!!.currentPage, cid)
                    currentPage = responseData.data.curPage
                    articleDTOList.addAll(responseData.data.datas)
                    if (responseData.data.datas.size < 20) {
                        noMore = true
                    }
                }
            }

            _pageState.value = true
        }
    }

    class TreeDetailBean(var currentPage: Int, var articleDTOList: SnapshotStateList<ArticleDTO>, var lazyListState: LazyListState, var noMore: Boolean = false)

}