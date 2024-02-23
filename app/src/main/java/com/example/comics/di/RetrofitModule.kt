package com.example.comics.di

import com.example.comics.BuildConfig
import com.example.comics.BuildConfig.BASE_URL
import com.example.comics.BuildConfig.IS_MOCK
import com.example.comics.data.remote.Api
import com.example.comics.data.remote.MockApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single {
        retrofit(get())
    }
    single {

        MockApi().takeIf {
            IS_MOCK
        }?: get<Retrofit>().create(Api::class.java)
    }

    single {
        providesHttplogging()
    }

    single {
        providesOkHttpClient(get())
    }
}


private fun retrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(okHttpClient)
    .build()

fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

private fun providesHttplogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return interceptor
}