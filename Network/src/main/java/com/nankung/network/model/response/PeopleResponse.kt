package com.nankung.network.model.response

import com.google.gson.annotations.SerializedName
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.model.response.result.PeopleResult

data class PeopleResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("results")
    val results: List<PeopleResult>?,

    @SerializedName("total_results")
    val totalResults: Int?
)

