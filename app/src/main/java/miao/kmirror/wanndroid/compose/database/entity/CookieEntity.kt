package miao.kmirror.wanndroid.compose.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.ktor.http.Cookie
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity
data class CookieEntity(
    @PrimaryKey val name: String,
    val cookieStr: String
) {
    companion object{
        fun getCookie(cookieStr: String): Cookie {
            return Json.decodeFromString<Cookie>(cookieStr)
        }

        fun getCookieStr(cookie: Cookie): String {
            return Json.encodeToString(cookie)
        }
    }
}