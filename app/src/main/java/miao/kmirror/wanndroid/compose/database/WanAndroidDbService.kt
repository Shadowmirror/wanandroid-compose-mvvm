package miao.kmirror.wanndroid.compose.database

import android.app.Application
import androidx.room.Room
import miao.kmirror.wanndroid.compose.MyApp
import org.koin.core.annotation.Single

@Single
class WanAndroidDbService(myApp: Application) {

    companion object {
        const val GUEST_ID = -1L
    }

    val publicDatabase by lazy {
        Room.databaseBuilder<PublicDatabase>(
            context = myApp.applicationContext,
            name = myApp.getDatabasePath("public_database.db").absolutePath
        ).build()
    }

    // 当前用户数据库实例
    private var userDatabase: UserDatabase? = null

    // 当前用户 ID，默认是 Guest 用户
    private var currentUserId: String = "-1"

    /**
     * 获取用户数据库实例
     */
    private fun getUserDatabase(userId: String): UserDatabase {
        return userDatabase ?: synchronized(this) {
            userDatabase ?: Room.databaseBuilder(
                MyApp.getInstance().applicationContext,
                UserDatabase::class.java,
                "user_${userId}.db" // 统一命名规则：user_-1.db 或 user_{userId}.db
            ).build().also { userDatabase = it }
        }
    }

    /**
     * 获取当前用户的数据库实例
     */
    fun getCurrentUserDatabase(): UserDatabase {
        return getUserDatabase(currentUserId)
    }

    /**
     * 切换用户数据库
     * @param newUserId 新用户的 ID，默认是 Guest 用户（-1）
     */
    fun switchUserDatabase(newUserId: String = "-1"): UserDatabase {
        // 关闭当前用户的数据库
        closeUserDatabase()

        // 更新当前用户 ID
        currentUserId = newUserId

        // 创建新用户的数据库
        return getUserDatabase(newUserId)
    }

    /**
     * 关闭当前用户的数据库实例
     */
    private fun closeUserDatabase() {
        userDatabase?.close()
        userDatabase = null
    }
}