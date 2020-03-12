package com.nankung.network.model.exeption

import android.content.Context
import android.widget.Toast
import org.json.JSONObject


object ErrorConverter {

    fun handlerErrorConverter(error:String):List<String>? {
        var statusMessage = ""
        var statusCode = ""
        var listError: List<String>?
        try {
            val jObjError = JSONObject(error)
            statusCode =  jObjError.getInt("status_code").toString()
            statusMessage = jObjError.getString("status_message")
            listError = listOf(statusCode,statusMessage)
        } catch (e: Exception) {
            e.printStackTrace()
            listError = listOf(e.toString())
        }
        return listError
    }
}