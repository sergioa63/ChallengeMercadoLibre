package com.example.sergio.challenge.presentation.common.retrofit

import android.content.Context
import android.os.Build
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.concurrent.TimeUnit

class RetrofitHandler constructor(context: Context){
    val timeOut = 50
    protected var headers: HashMap<String, String>? = null


    fun getRetrofit(): Retrofit? {
        val clientBuilder: OkHttpClient.Builder = getClientBuilder()
        return Retrofit.Builder().baseUrl("https://api.mercadolibre.com/").client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getClientBuilder(): OkHttpClient.Builder {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(Interceptor { chain ->
            val original : Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })
        clientBuilder.writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return clientBuilder.addInterceptor(interceptor)
    }
}