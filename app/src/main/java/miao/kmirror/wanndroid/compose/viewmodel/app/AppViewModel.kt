package miao.kmirror.wanndroid.compose.viewmodel.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AppViewModel(
    private val wanAndroidRepository: WanAndroidRepository
) : ViewModel() {
    fun autoLogin() {
        viewModelScope.launch {
            val login = wanAndroidRepository.login(isAuto = true)
            Log.i("KmirrorTag", "autoLogin: login = $login")
        }
    }
}