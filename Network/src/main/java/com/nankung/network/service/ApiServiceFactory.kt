package com.nankung.network.service

import com.nankung.common.module.base.URLService
import com.nankung.network.network.DefaultHttpLoggerInterceptor
import com.nankung.network.remote.LiveDataCallAdapterFactory
import com.nankung.network.service.interfaces.MovieService
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class ApiServiceFactory {
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
            val headerAuthorizationInterceptor: Interceptor = object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    //Add Header Here
                    var request = chain.request()
                    val headers: Headers = request.headers.newBuilder()
                        .add("language", "th")
                        .add("Accept", "application/json")
                        .build()
                    request = request.newBuilder().headers(headers).build()
                    return chain.proceed(request)
                }
            }
            val client = OkHttpClient.Builder()
            client.callTimeout(30, TimeUnit.SECONDS)
            client.writeTimeout(30, TimeUnit.SECONDS)
            client.connectTimeout(30, TimeUnit.SECONDS)
            client.addInterceptor(headerAuthorizationInterceptor)
            client.addInterceptor(
                DefaultHttpLoggerInterceptor.getInterceptor(
                    true
                )
            )
            return client.build()
        }
    }
}
