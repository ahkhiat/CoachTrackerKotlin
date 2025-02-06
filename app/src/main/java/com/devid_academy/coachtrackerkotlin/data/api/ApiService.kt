package com.devid_academy.coachtrackerkotlin.data.api

import DateJsonAdapter
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
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import java.security.KeyStore


object ApiService {

    private fun getClient() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // Desactiver la validation SSL, ATTENTION seulement en mode DEV
        // DEBUT
        val trustAllCertificates = object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        }

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf(TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).let {
            it.init(null as KeyStore?)
            trustAllCertificates
        }), SecureRandom())
        // FIN

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
                // Rajout de ces 2 lignes pour ignorer la validation SSL
                .sslSocketFactory(sslContext.socketFactory, trustAllCertificates)
                .hostnameVerifier { _, _ -> true }
                .build()

        val moshi = Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
//            add(DateJsonAdapter())
        }.build()

        return Retrofit.Builder()
            .baseUrl(ApiRoutes.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }
    fun getApi() = getClient().create(ApiInterface::class.java)
}




const val MY_TEAM_NAME = "U11F1"

fun getEvents(onResult: (List<EventDTO>) -> Unit) {
    val call: Call<List<EventDTO>>? = ApiService.getApi().getAllEvents(MY_TEAM_NAME)
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