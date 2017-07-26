package com.zamahaka.wotsapp.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zamahaka.wotsapp.data.local.entity.SearchUserEntity

/**
 * Created by Ura on 04.07.2017.
 */
@Dao
interface SearchUserDao {

    @Query("SELECT * FROM searchUsers ORDER BY accountId")
    fun getUsers(): LiveData<List<SearchUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: List<SearchUserEntity>)

    @Query("SELECT * FROM searchUsers WHERE nickName LIKE :arg0")
    fun findUser(nickName: String): LiveData<SearchUserEntity>

}