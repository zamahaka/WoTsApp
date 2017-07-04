package com.zamahaka.wotsapp.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Ura on 04.07.2017.
 */

@Entity(tableName = "searchUsers")
class SearchUserEntity(@PrimaryKey @SerializedName("account_id") val accountId: Int,
                       @SerializedName("nickname") val nickName: String)