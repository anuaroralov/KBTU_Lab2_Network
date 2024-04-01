package com.example.kbtu_lab2_network.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val headerName: String, private val headerValue: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithHeader = originalRequest.newBuilder()
            .header(headerName, headerValue)
            .build()

        return chain.proceed(requestWithHeader)
    }
}
