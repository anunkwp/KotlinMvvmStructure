package com.nankung.network.model.exeption

import android.content.Context
import android.widget.Toast
import org.json.JSONObject
import org.json.JSONStringer
import java.security.AccessController.getContext


object ErrorConverter {

    fun handlerErrorConverter(error:String,context:Context):String {
        var statusMessage = ""
        var statusCode = ""
        try {
            val jObjError = JSONObject(error)
            statusCode =  jObjError.getInt("status_code").toString()
            statusMessage = jObjError.getString("status_message")
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        return statusMessage
    }
}