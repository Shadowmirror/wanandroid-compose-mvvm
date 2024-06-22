package miao.kmirror.wanandroid.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import miao.kmirror.wanandroid.R
import miao.kmirror.wanandroid.utils.LogUtil
import miao.kmirror.wanandroid.utils.ToastUtil
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity: ComponentActivity() {


    @Inject
    lateinit var mBaseAppViewModel: BaseAppViewModel

    @Inject
    lateinit var mAppViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserver()
    }

    protected open fun startObserver(){
        mBaseAppViewModel.apply {
            viewModelScope.launch {
                exception.flowWithLifecycle(lifecycle).collect { e ->
                    LogUtil.e("网络请求错误：${e.message}")
                    when (e) {
                        is SocketTimeoutException -> ToastUtil.showShort(
                            this@BaseActivity,
                            getString(R.string.request_time_out)
                        )

                        is ConnectException, is UnknownHostException -> ToastUtil.showShort(
                            this@BaseActivity,
                            getString(R.string.network_error)
                        )

                        else -> ToastUtil.showShort(
                            this@BaseActivity,
                            e.message ?: getString(R.string.response_error)
                        )
                    }
                }
            }

            viewModelScope.launch {
                errorResponse.flowWithLifecycle(lifecycle).collect { response ->
                    response?.let { it1 -> ToastUtil.showShort(this@BaseActivity, it1.errorMsg) }
                }
            }
        }
    }
}