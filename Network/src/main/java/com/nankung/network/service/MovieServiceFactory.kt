package com.nankung.network.service

import com.nankung.network.network.DefaultHttpLoggerInterceptor
import com.nankung.network.remote.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieServiceFactory {
    companion object {

        @Volatile
        private var INSTANCE: MovieService? = null

        private const val BASE_URL = URLService.TMDB_BASE_URL

        fun getService(): MovieService {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(MovieService::class.java)
                INSTANCE = instance
                instance
            }
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(
                DefaultHttpLoggerInterceptor.getInterceptor(
                    true
                )
            )
            return client.build()
        }
    }
}