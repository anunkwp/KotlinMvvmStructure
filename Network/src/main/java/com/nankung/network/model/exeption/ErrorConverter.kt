package com.nankung.network.model.exeption

import android.content.Context
import android.widget.Toast
import org.json.JSONObject


object ErrorConverter {

    fun handlerErrorConverter(error:String,context:Context):List<String>? {
        var statusMessage = ""
        var statusCode = ""
        var listError :List<String>?=null
        try {
            val jObjError = JSONObject(error)
            statusCode =  jObjError.getInt("status_code").toString()
            statusMessage = jObjError.getString("status_message")
            listError = arrayListOf(statusCode,statusMessage)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        return listError
    }
}