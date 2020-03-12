package com.nankung.network.model.response.body

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class  ValidateBody (
    @SerializedName("username") var username: String? = "",
    @SerializedName("password") var password: String? = "",
    @SerializedName("request_token") var request_token: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(request_token)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ValidateBody> {
        override fun createFromParcel(parcel: Parcel): ValidateBody {
            return ValidateBody(parcel)
        }

        override fun newArray(size: Int): Array<ValidateBody?> {
            return arrayOfNulls(size)
        }
    }
}