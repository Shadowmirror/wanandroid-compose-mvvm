package miao.kmirror.wanndroid.compose.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * 这个只是为了验证分库方案的
 * */
@Entity
class UserCustomEntity(
    @PrimaryKey
    val keyStr: String = "",
    val valueStr: String = "",
)