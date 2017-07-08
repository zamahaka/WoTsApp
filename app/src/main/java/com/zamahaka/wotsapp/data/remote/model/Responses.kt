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

class TankopediaInfoResponse : Response<TankopediaInfo>()