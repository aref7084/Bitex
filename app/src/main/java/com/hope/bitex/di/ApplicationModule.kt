package com.hope.bitex.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hope.bitex.data.api.TradeApiService
import com.hope.bitex.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    private val baseUrl = "https://6294760fa7203b3ed06971f5.mockapi.io/api/v1/"


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        builder.connectTimeout(30, TimeUnit.SECONDS) 
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS) 
        return builder.build()
    }


    @Provides
    @Singleton
    fun provideTradeApi(gson: Gson, client: OkHttpClient): TradeApiService =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(TradeApiService::class.java)

}