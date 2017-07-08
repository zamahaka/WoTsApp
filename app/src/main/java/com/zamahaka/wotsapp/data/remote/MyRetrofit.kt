package com.zamahaka.wotsapp.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ura on 02.07.2017.
 */
object MyRetrofit {

    private val BASE_URL = "https://api.worldoftanks.ru/wot/"

    val wotApi: WotApi by lazy { retrofit.create(WotApi::class.java) }

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val originalRequest = chain.request()
                    val originalUrl = originalRequest.url()

                    val url = originalUrl.newBuilder()
                            .addQueryParameter("application_id", "a204c059c5b99b4336387e28f0b2852a")
                            .build()
                    chain.proceed(originalRequest.newBuilder().url(url).build())
                }
                .build()
    }
}