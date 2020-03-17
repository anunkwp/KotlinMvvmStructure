package com.nankung.network.model.response.result

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */

@Entity(tableName = "people", primaryKeys = ["id"])
data class PeopleResult(

    @SerializedName("popularity")
    val popularity: Double? = 0.0,

    @SerializedName("known_for_department")
    val known_for_department: String? = "",

    @SerializedName("gender")
    val gender: Int? = 0,

    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("profile_path")
    val profile_path: String? = "" ,

    @SerializedName("name")
    val name: String? = ""
)
{
    override fun toString(): String {
        return "\n popularity='$popularity'," +
                "\n known_for_department='$known_for_department'\n"+
                "\n gender='$gender'\n"+
                "\n id='$id'\n"+
                "\n profile_path='$profile_path'\n"+
                "\n name='$name'\n"
    }
}