package miao.kmirror.wanndroid.compose.network

import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import miao.kmirror.wanndroid.compose.repository.CookieRepository
import miao.kmirror.wanndroid.compose.utils.TimeUtil

class CustomCookiesStorage(private val cookieRepository: CookieRepository) : CookiesStorage {
    private val cookies = mutableListOf<Cookie>()
    private var isInitCookies = false


    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        cookies.add(cookie)
        cookieRepository.insertOrUpdateCookie(cookie)
    }

    override fun close() {
    }

    override suspend fun get(requestUrl: Url): List<Cookie> {
        if (cookies.isEmpty() && !isInitCookies) {
            isInitCookies = true
            cookies.addAll(cookieRepository.getAllCookies())
            for (cookie in cookies) {
                // 过期了, 就要清除 Cookie 数据
                cookie.expires?.apply {
                    if (TimeUtil.getNetworkTime() >= timestamp) {
                        cookies.clear()
                        cookieRepository.deleteAllCookies()
                        return emptyList()
                    }
                }
            }
        }
        return cookies
    }
}