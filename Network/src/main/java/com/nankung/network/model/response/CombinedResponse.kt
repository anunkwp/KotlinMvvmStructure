package com.nankung.network.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nankung.network.model.response.result.CombinedCastResult
import com.nankung.network.model.response.result.CombinedCrewResult

data class CombinedResponse(

    @SerializedName("cast")
    val cast: List<CombinedCastResult>?,

    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("crew")
    val crew: List<CombinedCrewResult>?

)
