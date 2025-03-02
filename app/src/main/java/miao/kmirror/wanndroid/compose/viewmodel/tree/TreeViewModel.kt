package miao.kmirror.wanndroid.compose.viewmodel.tree

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import miao.kmirror.wanndroid.compose.network.bean.TreeDTO
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class TreeViewModel(private val wanAndroidRepository: WanAndroidRepository) : ViewModel() {

    private val _pageState = MutableStateFlow(false)
    val pageState: StateFlow<Boolean> = _pageState

    val treeDTOList = mutableStateListOf<TreeDTO>()


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
        treeDTOList.clear()
        treeDTOList.addAll(wanAndroidRepository.getTreeBean().data)
    }
}

