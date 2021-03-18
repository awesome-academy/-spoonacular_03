package com.sun.spoonacular.data.source.remote.api

import com.sun.spoonacular.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val CONNECT_TIMEOUT = 60L
    private const val READ_TIMEOUT = 30L

    private fun callApi(): Retrofit {
        val builder = OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        builder.interceptors().add(AuthenticationInterceptor())

        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: ApiService = callApi().create(ApiService::class.java)
}
