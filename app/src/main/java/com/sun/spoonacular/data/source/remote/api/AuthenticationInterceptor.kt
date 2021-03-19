package com.sun.spoonacular.data.source.remote.api

import com.sun.spoonacular.utils.Constant
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(Constant.API_KEY, Constant.API_VALUE)
            .addHeader(Constant.HOST_KEY, Constant.HOST_VALUE)
            .build()
        return chain.proceed(newRequest)
    }
}
