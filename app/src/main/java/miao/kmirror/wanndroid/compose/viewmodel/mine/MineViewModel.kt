package miao.kmirror.wanndroid.compose.viewmodel.mine

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.MyApp
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import miao.kmirror.wanndroid.compose.utils.toPrettyJson
import org.koin.android.annotation.KoinViewModel
import kotlin.math.log

@KoinViewModel
class MineViewModel(private val wanAndroidRepository: WanAndroidRepository) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")


    fun login() {
        if (username.value.isBlank() || password.value.isBlank()) {
            Toast.makeText(MyApp.getInstance(), "用户名或密码为空", Toast.LENGTH_SHORT).show()
        } else {
            viewModelScope.launch {
                val login = wanAndroidRepository.login(username.value, password.value, false)
                if (login.id == WanAndroidDbService.GUEST_ID){
                    Toast.makeText(MyApp.getInstance(), "游客状态", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(MyApp.getInstance(), "登录成功", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getCoinBean() {
        viewModelScope.launch {
            val coin = wanAndroidRepository.getCoin()
            Log.i("KmirrorTag", "login: ${coin.toPrettyJson()}")
        }
    }

}