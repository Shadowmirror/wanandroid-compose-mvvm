package miao.kmirror.wanandroid.ui.navigation.main.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import miao.kmirror.wanandroid.bean.Article
import miao.kmirror.wanandroid.http.WanAndroidService
import miao.kmirror.wanandroid.ui.navigation.main.ArticleViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ArticleViewModel() {

    @Inject
    lateinit var mWanAndroidService: WanAndroidService

    protected val articleList = ArrayList<Article>()
    protected var currentPage = 0

    fun getHomeArticlePageList(isRefresh: Boolean = true) {
        emitUiState(isRefresh, articleList)
        launchTool({
            if (isRefresh) {
                articleList.clear()
                currentPage = 0
            }


            if (currentPage == 0) {
                val jobGetArticle = async(Dispatchers.IO + SupervisorJob()) {
                    mWanAndroidService.getArticlePageList(currentPage++, PAGE_SIZE)
                }
                try {
                    val articleListRequest = jobGetArticle.await()
                    handleRequest(response = articleListRequest) {
                        emitUiState(
                            data = articleList.apply { addAll(articleListRequest.data.datas) },
                            showLoadingMore = true
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitUiState(showLoading = false, error = e.message)

                }
            } else {
                handleRequest(mWanAndroidService.getArticlePageList(currentPage++, PAGE_SIZE)) {
                    articleList.addAll(it.data.datas)
                    if (articleList.size == it.data.total) {
                        emitUiState(data = articleList, showLoadingMore = false, noMoreData = true)
                        return@handleRequest
                    }
                    currentPage++
                    emitUiState(data = articleList, showLoadingMore = true)
                }
            }
        })
    }

}