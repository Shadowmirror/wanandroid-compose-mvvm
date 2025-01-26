package miao.kmirror.wanndroid.compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import miao.kmirror.wanndroid.compose.database.dao.UserCustomDao
import miao.kmirror.wanndroid.compose.database.dao.UserInfoDao
import miao.kmirror.wanndroid.compose.database.entity.UserCustomEntity
import miao.kmirror.wanndroid.compose.database.entity.UserInfoEntity


@Database(entities = [UserInfoEntity::class], version = 1)
abstract class PublicDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}


@Database(entities = [UserCustomEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userCustomDao(): UserCustomDao
}



