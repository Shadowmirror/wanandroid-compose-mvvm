package miao.kmirror.wanndroid.compose.viewmodel.test

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.database.entity.User
import miao.kmirror.wanndroid.compose.database.utils.DbUtil
import org.koin.android.annotation.KoinViewModel
import kotlin.random.Random

@KoinViewModel
class TestViewModel(private val wanAndroidDbService: WanAndroidDbService) : ViewModel() {

    val userList = mutableStateListOf<User>()

    fun queryAllUsers() {
        viewModelScope.launch {
            userList.clear()
            userList.addAll(wanAndroidDbService.wanAndroidDb.getUserDao().getAllUsers())
        }
    }

    fun addRandomUser() {
        val userId = Random.nextLong(1000L)
        val user = User(userId = userId, userName = "Mirror_$userId", databaseId = DbUtil.generateULID())
        userList.add(user)
        viewModelScope.launch(Dispatchers.IO) {
            wanAndroidDbService.wanAndroidDb.getUserDao().insertUser(user)
        }
    }

    fun deleteLastUser() {
        viewModelScope.launch {
            val userDatabaseId = userList.last().databaseId
            userList.removeLastOrNull()
            wanAndroidDbService.wanAndroidDb.getUserDao().deleteUserByDatabaseId(userDatabaseId)
        }
    }
}