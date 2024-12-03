package miao.kmirror.wanndroid.compose.viewmodel.tree

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import miao.kmirror.wanndroid.compose.bean.TreeBean
import miao.kmirror.wanndroid.compose.network.WanAndroidApiService
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class TreeViewModel(private val mWanAndroidApiService: WanAndroidApiService) : ViewModel() {

    private val _pageState = MutableStateFlow(false)
    val pageState: StateFlow<Boolean> = _pageState

    val treeBeanList = mutableStateListOf<TreeBean>()


    fun initData() {
        viewModelScope.launch {
            _pageState.value = false

            withContext(Dispatchers.IO) {
                getTreeBean()
            }


            _pageState.value = true
        }
    }

    private suspend fun getTreeBean() {
        treeBeanList.clear()
        treeBeanList.addAll(mWanAndroidApiService.wanAndroidApi.getTreeBean().data)
    }
}

