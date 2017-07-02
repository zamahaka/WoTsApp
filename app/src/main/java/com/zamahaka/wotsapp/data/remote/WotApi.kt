package com.zamahaka.wotsapp.data.remote

import com.zamahaka.wotsapp.data.remote.model.UserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ura on 02.07.2017.
 */
interface WotApi {

    @GET("account/list/")
    fun searchAccounts(@Query("search") search: String,
                       @Query("fields") fields: String = "",
                       @Query("language") language: String = "en",
                       @Query("limit") limit: Int = 100,
                       @Query("type") type: String = "startswith") : Call<UserSearchResponse>

}