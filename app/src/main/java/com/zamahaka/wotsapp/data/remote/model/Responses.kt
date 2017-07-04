package com.zamahaka.wotsapp.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ura on 02.07.2017.
 */
open class Response<out T>(@SerializedName("status") val status: String? = null,
                           @SerializedName("meta") val meta: Meta? = null,
                           @SerializedName("data") val data: T? = null,
                           @SerializedName("error") val error: Error? = null)

class UserSearchResponse : Response<List<SearchUser>>()

data class Meta(@SerializedName("count") val count: Int)

data class Error(@SerializedName("field") val field: String?,
                 @SerializedName("message") val message: String?,
                 @SerializedName("code") val code: Int?,
                 @SerializedName("value") val value: Any?)

data class SearchUser(@SerializedName("account_id") val accountId: Int,
                      @SerializedName("nickname") val nickName: String)