package com.nankung.network.remote

import android.annotation.SuppressLint
import android.util.Log

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

import okhttp3.logging.HttpLoggingInterceptor

class NetworkHttpLogger(private val PATH_URL: String) : HttpLoggingInterceptor.Logger {
    companion object {
        private val TAG = NetworkHttpLogger::class.java.simpleName
    }

    @SuppressLint("LogNotTimber")
    override fun log(message: String) {
        if (!message.startsWith("{")) {
            Log.d(TAG, message)
            return
        }
        try {
            val prettyPrintJson =
                GsonBuilder().setPrettyPrinting().create().toJson(JsonParser().parse(message))
            Log.d(TAG, "\n API IS : $PATH_URL \n$prettyPrintJson")
            //Log.d(TAG, " \n $prettyPrintJson")
        } catch (m: JsonSyntaxException) {
            m.printStackTrace()
            Log.d(TAG, message)
        }

    }

}
