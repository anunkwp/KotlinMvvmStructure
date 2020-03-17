package com.nankung.network.service.interfaces

import androidx.lifecycle.LiveData
import com.nankung.network.model.response.CombinedResponse
import com.nankung.network.model.response.PeopleResponse
import com.nankung.network.model.response.result.PeopleResult
import com.nankung.network.model.response.TokenResponse
import com.nankung.network.module.PeopleApiService
import com.nankung.network.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */

interface PeopleService {

    @GET(PeopleApiService.PEOPLE_POPULAR)
    fun requestPeoplePopular(
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<PeopleResponse>>

    @GET(PeopleApiService.COMBINED_CREDITS)
    fun requestCombinedCredit(
        @Path("person_id") person_id: String,
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<CombinedResponse>>




}