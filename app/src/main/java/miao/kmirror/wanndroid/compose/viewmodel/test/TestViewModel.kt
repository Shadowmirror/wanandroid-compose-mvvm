package miao.kmirror.wanndroid.compose.viewmodel.test

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.User
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.database.utils.DbUtil
import org.koin.android.annotation.KoinViewModel
import kotlin.random.Random

@KoinViewModel
class TestViewModel(private val mWanAndroidDbService: WanAndroidDbService) : ViewModel() {
    val userList = mutableStateListOf<User>()

    fun queryAllUsers() {
        viewModelScope.launch {
            userList.clear()
            userList.addAll(mWanAndroidDbService.userDao.queryAllUsers().executeAsList())
        }
    }

    fun addRandomUser() {
        val userId = Random.nextLong(1000L)
        val user = User(userId = userId, userName = "Mirror_$userId", userDatabaseId = DbUtil.generateULID())
        userList.add(user)
        viewModelScope.launch(Dispatchers.IO) {
            mWanAndroidDbService.userDao.insertUser(user)
        }
    }

    fun deleteLastUser() {
        viewModelScope.launch {
            val userDatabaseId = userList.last().userDatabaseId
            userList.removeLastOrNull()
            mWanAndroidDbService.userDao.deleteUser(userDatabaseId)
        }
    }
}