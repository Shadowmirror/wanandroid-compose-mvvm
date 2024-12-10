package miao.kmirror.wanndroid.compose.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import miao.kmirror.wanndroid.compose.database.entity.User

@Dao
interface UserDao {
    // 插入用户
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    // 更新用户
    @Update
    suspend fun updateUser(user: User)

    // 删除用户
    @Delete
    suspend fun deleteUser(user: User)


    // 删除用户（通过数据库 ID）
    @Query("DELETE FROM User WHERE databaseId = :databaseId")
    suspend fun deleteUserByDatabaseId(databaseId: String)

    // 查询所有用户
    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>


    // 根据 databaseId 查询用户
    @Query("SELECT * FROM User WHERE databaseId = :databaseId")
    suspend fun getUserByDatabaseId(databaseId: String): User?

}