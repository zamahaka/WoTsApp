package com.zamahaka.wotsapp.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zamahaka.wotsapp.data.local.entity.SearchUserEntity
import com.zamahaka.wotsapp.data.remote.model.SearchUser

/**
 * Created by Ura on 04.07.2017.
 */
@Dao
interface SearchUserDao {

    @Query("SELECT * FROM searchUsers")
    fun getUsers(): LiveData<List<SearchUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: List<SearchUser>)

    @Query("SELECT * FROM searchUsers WHERE nickName LIKE ^nickName")
    fun findUser(nickName: String): LiveData<SearchUserEntity>

}