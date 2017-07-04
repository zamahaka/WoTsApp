package com.zamahaka.wotsapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.zamahaka.wotsapp.data.local.dao.SearchUserDao
import com.zamahaka.wotsapp.data.local.entity.SearchUserEntity

/**
 * Created by Ura on 04.07.2017.
 */
@Database(entities = arrayOf(SearchUserEntity::class), version = 1, exportSchema = false)
abstract class SearchUsersDatabase : RoomDatabase() {
    abstract fun usersDao(): SearchUserDao
}