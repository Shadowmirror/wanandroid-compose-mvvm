package miao.kmirror.wanndroid.compose.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserInfoEntity(
    @PrimaryKey
    val id: Long = -1, // Id 是不会变更的
    var admin: Boolean = false,
    var coinCount: Int = 0,
    var email: String = "",
    var nickname: String = "",
    var password: String = "",
    var publicName: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = "",
    /**
     * 是否是当前登录用户
     * */
    var isCurrentUser: Boolean = false,
)