package miao.kmirror.wanandroid.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import miao.kmirror.wanandroid.bean.ApiResponse

open class BaseAppViewModel: BaseViewModel<Unit>() {
    /** 请求异常（服务器请求失败，譬如：服务器连接超时等） */
    private val _exception = MutableSharedFlow<Exception>()
    val exception: SharedFlow<Exception> = _exception

    /** 请求服务器返回错误（服务器请求成功但status错误，譬如：登录过期等） */
    private val _errorResponse = MutableSharedFlow<ApiResponse<*>>()
    val errorResponse: SharedFlow<ApiResponse<*>?> = _errorResponse

    /** emit请求出错 */
    fun emitException(exception: Exception) {
        viewModelScope.launch {
            _exception.emit(exception)
        }
    }

    /** emit请求错误信息 */
    fun emitErrorResponse(apiResponse: ApiResponse<*>) {
        viewModelScope.launch {
            _errorResponse.emit(apiResponse)
        }
    }
}