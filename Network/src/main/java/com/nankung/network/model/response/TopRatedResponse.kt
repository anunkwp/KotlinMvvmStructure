package com.nankung.network.model.response

import com.google.gson.annotations.SerializedName
import com.nankung.network.model.response.result.TopRatedResult

data class TopRatedResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("results")
    val results: List<TopRatedResult>?,

    @SerializedName("total_results")
    val totalResults: Int?
)

