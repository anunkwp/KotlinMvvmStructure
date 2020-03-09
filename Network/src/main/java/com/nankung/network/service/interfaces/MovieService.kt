package com.nankung.network.service.interfaces

import androidx.lifecycle.LiveData
import com.nankung.network.model.PopularResponse
import com.nankung.network.model.TokenResponse
import com.nankung.network.model.body.ValidateBody
import com.nankung.network.module.MovieApiService
import com.nankung.network.remote.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {

    @POST(MovieApiService.VALIDATE_WITH_LOGIN)
    fun requestValidateToken(
        @Query("api_key") api_key: String,
        @Body body: ValidateBody
    ): LiveData<ApiResponse<TokenResponse>>

    @GET(MovieApiService.TOKEN_NEW)
    fun requestNewToken(
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<TokenResponse>>

    @GET(MovieApiService.POPULAR_MOVIE)
    fun requestPopularAPI(
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<PopularResponse>>


}