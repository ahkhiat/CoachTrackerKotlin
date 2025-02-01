package com.devid_academy.coachtrackerkotlin.data.api

import android.content.ContentValues.TAG
import android.util.Log
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private fun getClient() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

//        val client = OkHttpClient.Builder()
//            .addInterceptor(interceptor).build()
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                    chain.proceed(request)
                }
                .build()

        val moshi = Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }.build()

        return Retrofit.Builder()
            .baseUrl(ApiRoutes.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }
    fun getApi() = getClient().create(ApiInterface::class.java)
}



const val MY_TEAM_ID = 3L

fun getEvents(onResult: (List<EventDTO>) -> Unit) {
    val call: Call<List<EventDTO>>? = ApiService.getApi().getAllEvents(MY_TEAM_ID)
    call?.enqueue(object : Callback<List<EventDTO>> {
        override fun onResponse(call: Call<List<EventDTO>>, response: Response<List<EventDTO>>) {
            response.body()?.let {
                onResult(it)
            }
        }
        override fun onFailure(call: Call<List<EventDTO>>, t: Throwable) {
            Log.e(TAG, t.message ?: "boo, error")
        }
    })
}

//fun getAddCountry(country: CountryDTO, onResult: (Boolean) -> Unit) {
//    val countryName = country.nom
//    val countryFlag = country.url
//
//    val call: Call<RetourDTO>? = ApiService.getApi().insertCountry(countryName, countryFlag, MY_ID_STAGIAIRE)
//    call?.enqueue(object : Callback<RetourDTO> {
//        override fun onResponse(call: Call<RetourDTO>, response: Response<RetourDTO>) {
//            response.body()?.let {
//                onResult(true)
//            }
//        }
//        override fun onFailure(call: Call<RetourDTO>, t: Throwable) {
//            Log.e(TAG, t?.message ?: "boo, error")
//        }
//    })
//}

//fun getEditCountry(country: CountryDTO, onResult: (Boolean) -> Unit) {
//
//    val call: Call<RetourDTO>? = ApiService.getApi().updateCountry(UpdateCountryDTO(
//        id = country.id,
//        nom = country.nom,
//        url = country.url
//    ))
//    call?.enqueue(object : Callback<RetourDTO> {
//        override fun onResponse(call: Call<RetourDTO>, response: Response<RetourDTO>) {
//            response.body()?.let {
//                onResult(true)
//            }
//        }
//        override fun onFailure(call: Call<RetourDTO>, t: Throwable) {
//            Log.e(TAG, t?.message ?: "boo, error")
//        }
//    })
//}

//fun deleteCountry(countryId: Long, onResult: (Boolean) -> Unit) {
//    val call: Call<RetourDTO>? = ApiService.getApi().deleteCountry(countryId)
//    call?.enqueue(object : Callback<RetourDTO> {
//        override fun onResponse(call: Call<RetourDTO>, response: Response<RetourDTO>) {
//            response.body()?.let {
//                onResult(true)
//            }
//        }
//        override fun onFailure(call: Call<RetourDTO>, t: Throwable) {
//            Log.e(TAG, t?.message ?: "boo, error")
//        }
//    })
//}