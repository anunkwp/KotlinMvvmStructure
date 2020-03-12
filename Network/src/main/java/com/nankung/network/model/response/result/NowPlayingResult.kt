package com.nankung.network.model.response.result

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "now_play", primaryKeys = ["id"])
data class NowPlayingResult(
    @SerializedName("overview")
    val overview: String? = "",

    @SerializedName( "original_language")
    val original_language: String? = "",

    @SerializedName( "original_title")
    val original_title: String? = "",

    @SerializedName( "video")
    val video: Boolean? = false,

    @SerializedName( "title")
    val title: String? = "",

    @SerializedName( "poster_path")
    val poster_path: String? = "",

    @SerializedName( "backdrop_path")
    val backdrop_path: String? = "",

    @SerializedName( "release_date")
    val release_date: String? = "",

    @SerializedName( "popularity")
    val popularity: Double? = 0.0,

    @SerializedName( "vote_average")
    val vote_average: Double? = 0.0,

    @SerializedName( "id")
    val id: Int? = 0,

    @SerializedName( "adult")
    val adult: Boolean? = false,

    @SerializedName( "vote_count")
    val vote_count: Int? = 0
)
{
    override fun toString(): String {
        return "\n overview='$overview'," +
                "\n original_language='$original_language'\n"+
                "\n original_title='$original_title'\n"+
                "\n video='$video'\n"+
                "\n title='$title'\n"+
                "\n poster_path='$poster_path'\n"+
                "\n backdrop_path='$backdrop_path'\n"+
                "\n release_date='$release_date'\n"+
                "\n popularity='$popularity'\n"+
                "\n vote_average='$vote_average'\n"+
                "\n id='$id'\n"+
                "\n adult='$adult'\n"+
                "\n vote_count='$vote_count'\n"
    }
}