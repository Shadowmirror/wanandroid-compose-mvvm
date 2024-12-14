package miao.kmirror.wanndroid.compose.repository

import io.ktor.http.Cookie
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.database.entity.CookieEntity
import org.koin.core.annotation.Single


@Single
class CookieRepository(
    private val wanAndroidDbService: WanAndroidDbService,
) {


    // 插入或更新一个 Cookie
    suspend fun insertOrUpdateCookie(cookie: Cookie) {
        val cookieEntity = CookieEntity(
            name = cookie.name,
            cookieStr = CookieEntity.getCookieStr(cookie)
        )
        wanAndroidDbService.wanAndroidDb.getCookieDao().insertOrUpdate(cookieEntity)
    }


    // 获取所有 Cookie
    suspend fun getAllCookies(): List<Cookie> {
        return wanAndroidDbService.wanAndroidDb.getCookieDao().getAllCookies().map { CookieEntity.getCookie(it.cookieStr) }
    }


    // 删除所有 Cookie
    suspend fun deleteAllCookies() {
        wanAndroidDbService.wanAndroidDb.getCookieDao().deleteAllCookies()
    }

}