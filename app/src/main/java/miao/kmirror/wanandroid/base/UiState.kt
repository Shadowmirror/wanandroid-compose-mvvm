package miao.kmirror.wanandroid.base

open class UiState <T>(
    val showLoading: Boolean = false,
    var data: T? = null,
    val error: String? = null,
    val showLoadingMore: Boolean = false,
    val noMoreData: Boolean = false,
)