package com.nankung.network.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gen_token", primaryKeys = ["id"])
data class TokenResponse(



    @SerializedName("id")
    val id: Int? = 10,

    @SerializedName("expires_at")
    val expires_at: String? = "",

    @SerializedName("request_token")
    val request_token: String? = ""
)
{
    override fun toString(): String {
        return "\n expires_at='$expires_at'," +
                "\n request_token='$request_token'\n"
    }
}