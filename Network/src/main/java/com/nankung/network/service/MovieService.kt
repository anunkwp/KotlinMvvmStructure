package com.nankung.network.service

import androidx.lifecycle.LiveData
import com.nankung.network.model.PopularResponse
import com.nankung.network.module.MovieApiService
import com.nankung.network.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(MovieApiService.POPULAR_MOVIE)
    fun requestPopularAPI(
        @Query("api_key") api_key : String

    ): LiveData<ApiResponse<PopularResponse>>
}