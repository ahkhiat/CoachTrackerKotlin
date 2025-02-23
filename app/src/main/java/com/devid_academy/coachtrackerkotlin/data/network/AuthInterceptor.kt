package com.devid_academy.coachtrackerkotlin.data.network

import com.devid_academy.coachtrackerkotlin.data.manager.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferencesManager.getToken()
        val originalRequest: Request = chain.request()

        val newRequest = if (!token.isNullOrEmpty()) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }
        return chain.proceed(newRequest)
    }
}