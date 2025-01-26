package miao.kmirror.wanndroid.compose.database.dao

import androidx.room.*
import miao.kmirror.wanndroid.compose.database.entity.UserCustomEntity

@Dao
interface UserCustomDao {

    // 插入数据
    @Insert
    suspend fun insert(userCustomEntity: UserCustomEntity)

    // 根据 key 查询数据
    @Query("SELECT * FROM UserCustomEntity WHERE keyStr = :key LIMIT 1")
    suspend fun findByKey(key: String): UserCustomEntity?

    // 查询所有数据
    @Query("SELECT * FROM UserCustomEntity")
    suspend fun getAll(): List<UserCustomEntity>

    // 更新数据
    @Update
    suspend fun update(userCustomEntity: UserCustomEntity)

    // 删除数据
    @Delete
    suspend fun delete(userCustomEntity: UserCustomEntity)

    // 根据 key 删除数据
    @Query("DELETE FROM UserCustomEntity WHERE keyStr = :key")
    suspend fun deleteByKey(key: String)

    // 删除所有数据
    @Query("DELETE FROM UserCustomEntity")
    suspend fun deleteAll()
}