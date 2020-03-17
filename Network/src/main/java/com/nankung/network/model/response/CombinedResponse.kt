    package com.nankung.network.model.response

import com.google.gson.annotations.SerializedName
import com.nankung.network.model.response.result.CombinedResult
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.model.response.result.PeopleResult

data class CombinedResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("cast")
    val cast: List<CombinedResult>?,

    @SerializedName("total_results")
    val totalResults: Int?
)

