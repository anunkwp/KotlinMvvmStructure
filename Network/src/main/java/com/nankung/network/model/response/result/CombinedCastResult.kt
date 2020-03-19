package com.nankung.network.model.response.result

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */

@Entity(tableName = "combined_cast", primaryKeys = ["id"])
data class CombinedCastResult(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("character")
    val character: String? = "",

    @SerializedName("original_title")
    val original_title: String? = "",

    @SerializedName("overview")
    val overview: String? = "",

    @SerializedName("vote_count")
    val vote_count: Int? = 0,

    @SerializedName("media_type")
    val media_type: String? = "",

    @SerializedName("poster_path")
    val poster_path: String? = "",

    @SerializedName("backdrop_path")
    val backdrop_path: String? = "",

    @SerializedName("popularity")
    val popularity: Double? = 0.0,

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("vote_average")
    val vote_average: Double? = 0.0,

    @SerializedName("release_date")
    val release_date: String? = "",

    @SerializedName("credit_id")
    val credit_id: String? = ""
)
{
    override fun toString(): String {
        return "\n id='$id'," +
                "\n character='$character'\n"+
                "\n original_title='$original_title'\n"+
                "\n overview='$overview'\n"+
                "\n vote_count='$vote_count'\n"+
                "\n media_type='$media_type'\n"+
                "\n poster_path='$poster_path'\n"+
                "\n backdrop_path='$backdrop_path'\n"+
                "\n popularity='$popularity'\n"+
                "\n title='$title'\n"+
                "\n vote_average='$vote_average'\n"+
                "\n release_date='$release_date'\n"+
                "\n credit_id='$credit_id'\n"
    }
}