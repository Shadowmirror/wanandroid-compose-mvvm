package miao.kmirror.wanndroid.compose.repository

import android.app.Application
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.database.entity.UserCustomEntity
import miao.kmirror.wanndroid.compose.database.entity.UserInfoEntity
import miao.kmirror.wanndroid.compose.network.WanAndroidApiService
import miao.kmirror.wanndroid.compose.network.bean.ApiResponse
import miao.kmirror.wanndroid.compose.network.bean.ArticleDTO
import miao.kmirror.wanndroid.compose.network.bean.BannerDTO
import miao.kmirror.wanndroid.compose.network.bean.CoinInfoDTO
import miao.kmirror.wanndroid.compose.network.bean.PageDTO
import miao.kmirror.wanndroid.compose.network.bean.TreeDTO
import org.koin.core.annotation.Single


@Single
class WanAndroidRepository(
    private val wanAndroidApiService: WanAndroidApiService,
    private val wanAndroidDbService: WanAndroidDbService,
) {
    // 使用 MutableStateFlow 存储当前用户信息
    private val _currentUser = MutableStateFlow<UserInfoEntity>(UserInfoEntity())
    val currentUser: StateFlow<UserInfoEntity> get() = _currentUser

    suspend fun getBanner(): ApiResponse<List<BannerDTO>> {
        return wanAndroidApiService.wanAndroidApi.getBanner()
    }


    suspend fun getArticle(curPage: Int = 0, cid: Int? = null): ApiResponse<PageDTO<ArticleDTO>> {
        return wanAndroidApiService.wanAndroidApi.getArticle(curPage, cid)
    }

    suspend fun getTreeBean(): ApiResponse<List<TreeDTO>> {
        return wanAndroidApiService.wanAndroidApi.getTreeBean()
    }

    suspend fun login(username: String = "", password: String = "", isAuto: Boolean = false): UserInfoEntity {
        var currentUser: UserInfoEntity? = wanAndroidDbService.publicDatabase.userInfoDao().getCurrentUser()

        if (isAuto) {
            // 自动登录逻辑
            if (currentUser != null) {
                if (currentUser.id == WanAndroidDbService.GUEST_ID) {
                    // 如果是 Guest 用户直接返回 Guest 的值即可
                } else {
                    // 如果是真是用户就进行登录
                    val login = wanAndroidApiService.wanAndroidApi.login(currentUser.username, currentUser.password)
                    if (login.errorCode == 0 && login.data != null) {
                        currentUser = UserInfoEntity(
                            id = login.data.id,
                            admin = login.data.admin,
                            coinCount = login.data.coinCount,
                            email = login.data.email,
                            nickname = login.data.nickname,
                            password = currentUser.password,
                            publicName = login.data.publicName,
                            token = login.data.token,
                            type = login.data.type,
                            username = currentUser.username,
                            isCurrentUser = true,
                        )
                    } else {
                        // 登录失败就变成 Guest 用户
                        wanAndroidDbService.publicDatabase.userInfoDao().logout(currentUser.id)
                        currentUser = wanAndroidDbService.publicDatabase.userInfoDao().getById(WanAndroidDbService.GUEST_ID) ?: UserInfoEntity()
                    }
                }
            } else {
                // 这是第一次进入 APP ，所以需要初始化数据库
                currentUser = UserInfoEntity().apply {
                    isCurrentUser = true
                }
                wanAndroidDbService.publicDatabase.userInfoDao().insertOrReplace(currentUser)
            }
        } else {
            val login = wanAndroidApiService.wanAndroidApi.login(username, password)
            if (login.data != null && login.errorCode == 0) {
                currentUser = UserInfoEntity(
                    id = login.data.id,
                    admin = login.data.admin,
                    coinCount = login.data.coinCount,
                    email = login.data.email,
                    nickname = login.data.nickname,
                    password = password,
                    publicName = login.data.publicName,
                    token = login.data.token,
                    type = login.data.type,
                    username = username,
                    isCurrentUser = true,
                )
                wanAndroidDbService.publicDatabase.userInfoDao().insertOrReplace(currentUser)
            } else {
                currentUser = wanAndroidDbService.publicDatabase.userInfoDao().getById(WanAndroidDbService.GUEST_ID) ?: UserInfoEntity().apply { isCurrentUser = true }
            }
        }
        // 更新 MutableStateFlow 的值
        _currentUser.value = currentUser
        wanAndroidDbService.switchUserDatabase(currentUser.id.toString())
        return currentUser
    }


    suspend fun getCoin(): ApiResponse<CoinInfoDTO> {
        return wanAndroidApiService.wanAndroidApi.getCoin()
    }


    suspend fun addUserCustomEntity(userCustomEntity: UserCustomEntity) {
        return wanAndroidDbService.getCurrentUserDatabase().userCustomDao().insert(userCustomEntity)
    }

}