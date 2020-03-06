package com.nankung.network.model

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("results")
    val results: List<PopularResult>?,

    @SerializedName("total_results")
    val totalResults: Int?
)

