package miao.kmirror.wanndroid.compose.viewmodel.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import miao.kmirror.wanndroid.compose.utils.toPrettyJson
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TestViewModel(private val wanAndroidRepository: WanAndroidRepository) : ViewModel() {
    fun login() {
        viewModelScope.launch {
            val login = wanAndroidRepository.login("XXX", "XXX")
            Log.i("KmirrorTag", "login: ${login.toPrettyJson()}")

        }
    }

    fun getCoinBean() {
        viewModelScope.launch {
            val coin = wanAndroidRepository.getCoin()
            Log.i("KmirrorTag", "login: ${coin.toPrettyJson()}")
        }
    }

}