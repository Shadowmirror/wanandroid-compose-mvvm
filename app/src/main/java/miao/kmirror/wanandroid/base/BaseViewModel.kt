package miao.kmirror.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import miao.kmirror.wanandroid.bean.ApiResponse
import javax.inject.Inject


@HiltViewModel
open class BaseViewModel<T>: ViewModel() {

    @Inject
    lateinit var mAppViewModel: AppViewModel

    @Inject
    lateinit var mBaseAppViewModel: BaseAppViewModel

    private val _uiState = MutableStateFlow<UiState<T>>(UiState(true))
    val uiState: StateFlow<UiState<T>>  = _uiState

    protected  fun emitUiState(
        showLoading: Boolean = false,
        data: T? = null,
        error: String? = null,
        showLoadingMore: Boolean = false,
        noMoreData: Boolean = false,
    ){
        _uiState.value   = UiState(showLoading, data, error, showLoadingMore, noMoreData)
    }


    fun launchTool(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.() -> Unit = {},
        finallyBlock: suspend CoroutineScope.() -> Unit = {}
    ){
        viewModelScope.launch{
            try {
                tryBlock()
            }catch (e: Exception){
                mBaseAppViewModel.emitException(e)
                catchBlock()
            }finally {
                finallyBlock()
            }
        }
    }


    suspend fun <T> handleRequest(
        response: ApiResponse<T>,
        errorBlock: suspend CoroutineScope.(response: ApiResponse<T>) -> Boolean = { false },
        successBlock: suspend CoroutineScope.(response: ApiResponse<T>) -> Unit = {}
    ) {
        coroutineScope {
            when (response.errorCode) {
                0 -> successBlock(response) // 服务器返回请求成功码
                else -> { // 服务器返回的其他错误码
                    if (!errorBlock(response)) {
                        // 只有errorBlock返回false不拦截处理时，才去统一提醒错误提示
                        mBaseAppViewModel.emitErrorResponse(response)
                    }
                }
            }
        }
    }
}