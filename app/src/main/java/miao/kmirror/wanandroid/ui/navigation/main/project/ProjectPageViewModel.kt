package miao.kmirror.wanandroid.ui.navigation.main.project

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import miao.kmirror.wanandroid.bean.Article
import miao.kmirror.wanandroid.http.WanAndroidService
import miao.kmirror.wanandroid.ui.navigation.main.ArticleViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectPageViewModel @Inject constructor() : ArticleViewModel() {

    @Inject
    lateinit var mWanAndroidService: WanAndroidService

    protected val articleProjectList = ArrayList<Article>()
    protected var currentProjectPage = 0
    fun getProjectArticlePageList(isRefresh: Boolean = true) {
        emitUiState(isRefresh, articleProjectList)
        launchTool({
            if (isRefresh) {
                articleProjectList.clear()
                currentProjectPage = 0
            }


            if (currentProjectPage == 0) {
                val jobGetArticle = async(Dispatchers.IO + SupervisorJob()) {
                    mWanAndroidService.getArticlePageList(
                        currentProjectPage++,
                        PAGE_SIZE, 294
                    )
                }
                try {
                    val articleListRequest = jobGetArticle.await()
                    handleRequest(response = articleListRequest) {
                        emitUiState(
                            data = articleProjectList.apply { addAll(articleListRequest.data.datas) },
                            showLoadingMore = true
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitUiState(showLoading = false, error = e.message)

                }
            } else {
                handleRequest(
                    mWanAndroidService.getArticlePageList(
                        currentProjectPage++,
                        PAGE_SIZE
                    )
                ) {
                    articleProjectList.addAll(it.data.datas)
                    if (articleProjectList.size == it.data.total) {
                        emitUiState(
                            data = articleProjectList,
                            showLoadingMore = false,
                            noMoreData = true
                        )
                        return@handleRequest
                    }
                    currentProjectPage++
                    emitUiState(data = articleProjectList, showLoadingMore = true)
                }
            }
        })
    }
}