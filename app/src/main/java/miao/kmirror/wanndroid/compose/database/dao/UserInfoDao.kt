package miao.kmirror.wanndroid.compose.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import miao.kmirror.wanndroid.compose.database.entity.UserInfoEntity

@Dao
interface UserInfoDao {
    /**
     * 插入或替换一条用户信息
     * @param userInfo 用户信息
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(userInfo: UserInfoEntity)

    /**
     * 根据用户 ID 查询用户信息
     * @param id 用户 ID
     * @return 用户信息
     */
    @Query("SELECT * FROM UserInfoEntity WHERE id = :id")
    suspend fun getById(id: Long): UserInfoEntity?

    /**
     * 查询所有用户信息
     * @return 所有用户信息列表
     */
    @Query("SELECT * FROM UserInfoEntity")
    suspend fun getAll(): List<UserInfoEntity>

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Query("SELECT * FROM UserInfoEntity WHERE username = :username")
    suspend fun getByUsername(username: String): UserInfoEntity?

    /**
     * 删除一条用户信息
     * @param UserInfoEntity 用户信息
     */
    @Query("DELETE FROM UserInfoEntity WHERE id = :id")
    suspend fun deleteById(id: Long)

    /**
     * 删除所有用户信息
     */
    @Query("DELETE FROM UserInfoEntity")
    suspend fun deleteAll()


    /**
     * 获取当前用户
     * */
    @Query("SELECT * FROM UserInfoEntity WHERE isCurrentUser = 1 LIMIT 1")
    suspend fun getCurrentUser(): UserInfoEntity?

    /**
     * 当前用户退出，当前用户的 isCurrentUser 置为 false，Guest（id 为 -1） 用户的 isCurrentUser 置为 true
     * */
    @Query(
        """
        UPDATE UserInfoEntity 
        SET isCurrentUser = CASE 
            WHEN id = :id THEN 0 
            WHEN id = -1 THEN 1 
            ELSE isCurrentUser 
        END
        WHERE id = :id OR id = -1
        """
    )
    suspend fun logout(id: Long)

}