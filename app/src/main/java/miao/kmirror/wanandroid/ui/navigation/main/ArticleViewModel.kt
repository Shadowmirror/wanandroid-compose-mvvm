package miao.kmirror.wanandroid.ui.navigation.main

import dagger.hilt.android.lifecycle.HiltViewModel
import miao.kmirror.wanandroid.base.BaseViewModel
import miao.kmirror.wanandroid.bean.Article
import javax.inject.Inject

@HiltViewModel
open class ArticleViewModel @Inject constructor() : BaseViewModel<List<Article>>() {
    companion object {
        /** 每页显示的条目大小 */
        const val PAGE_SIZE = 10
    }
}