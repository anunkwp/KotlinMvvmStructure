package com.nankung.network.network
import okhttp3.logging.HttpLoggingInterceptor


object DefaultHttpLoggerInterceptor {
    fun getInterceptor(isShowLog: Boolean): HttpLoggingInterceptor {
        return if (isShowLog) {
            val httpLoggingInterceptor = HttpLoggingInterceptor(NetworkHttpLogger())
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
