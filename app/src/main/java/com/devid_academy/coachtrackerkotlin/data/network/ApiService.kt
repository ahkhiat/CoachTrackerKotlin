package com.devid_academy.coachtrackerkotlin.data.network

import androidx.lifecycle.ViewModel

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiService {

    private fun getClient() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(AuthInterceptor())
        }.build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder().apply {
            baseUrl(ApiRoutes.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            client(client)
        }.build()


    }
    fun getApi() = getClient().create(ApiInterface::class.java)
    fun ViewModel.getApi() = getClient().create(ApiInterface::class.java)
}
