package miao.kmirror.wanandroid.ui.navigation.base

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import miao.kmirror.wanandroid.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class WanAndroidScreenViewModel @Inject constructor(): BaseViewModel<Unit>() {
    private val _isShowSplash = MutableStateFlow(true)
    val isShowSplash : StateFlow<Boolean> = _isShowSplash


    fun emitSplashStateChange(isShowSplash: Boolean){
        _isShowSplash.value = isShowSplash
    }
}