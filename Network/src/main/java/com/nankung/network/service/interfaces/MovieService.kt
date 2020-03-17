package com.nankung.network.service.interfaces

import androidx.lifecycle.LiveData
import com.nankung.network.model.response.*
import com.nankung.network.model.response.body.ValidateBody
import com.nankung.network.model.response.MoviesResponse
import com.nankung.network.module.MovieApiService
import com.nankung.network.remote.ApiResponse
import retrofit2.http.*

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

    @GET(MovieApiService.NOW_PLAYING_MOVIE)
    fun requestNowPlayingAPI(
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<MoviesResponse>>

    @GET(MovieApiService.UPCOMING_MOVIE)
    fun requestUpcomingAPI(
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<MoviesResponse>>

    @GET(MovieApiService.TOP_RATED_MOVIE)
    fun requestTopRatedAPI(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): LiveData<ApiResponse<MoviesResponse>>

    @GET(MovieApiService.POPULAR_MOVIE)
    fun requestPopularAPI(
        @Query("api_key") api_key: String
    ): LiveData<ApiResponse<MoviesResponse>>


}