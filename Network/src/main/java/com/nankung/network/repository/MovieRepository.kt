package com.nankung.network.repository

import androidx.lifecycle.LiveData
import com.nankung.network.database.dao.MovieDao
import com.nankung.network.model.PopularResponse
import com.nankung.network.model.PopularResult
import com.nankung.network.database.MovieDatabase
import com.nankung.network.engine.trigger.PopularTrigger
import com.nankung.network.engine.trigger.ValidateTrigger
import com.nankung.network.model.TokenResponse
import com.nankung.network.model.body.ValidateBody
import com.nankung.network.network.NetworkBoundResource
import com.nankung.network.remote.ApiResponse
import com.nankung.network.remote.ContextProviders
import com.nankung.network.remote.Resource
import com.nankung.network.service.interfaces.MovieService

class MovieRepository(
    private val db: MovieDatabase,
    private val movieDao: MovieDao,
    private val movieService: MovieService,
    private val coroutineContext: ContextProviders
) {

    companion object {
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            movieDb: MovieDatabase,
            movieService: MovieService
        ): MovieRepository = INSTANCE
            ?: synchronized(MovieRepository::class.java) {
                MovieRepository(
                    movieDb,
                    movieDb.movieDao(),
                    movieService,
                    ContextProviders.getInstance()
                )
                    .also { INSTANCE = it }
            }
    }

    fun requestTokenRepository(api_key: String):  LiveData<Resource<TokenResponse>> {
        return object :
            NetworkBoundResource<TokenResponse, TokenResponse>(coroutineContext) {
            override fun saveCallResult(item: TokenResponse) {
                item.let {
                    db.runInTransaction {
                        movieDao.saveToken(it)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<TokenResponse>> =
                movieService.requestNewToken(api_key)

            override fun shouldFetch(data: TokenResponse?): Boolean = true
            override fun loadFromDb(): LiveData<TokenResponse> = movieDao.getToken()
        }.asLiveData()
    }

    fun requestValidateTokenRepository(trigger:ValidateTrigger):  LiveData<Resource<TokenResponse>> {
        return object :
            NetworkBoundResource<TokenResponse, TokenResponse>(coroutineContext) {
            override fun saveCallResult(item: TokenResponse) {
                item.let {
                    db.runInTransaction {
                        movieDao.saveToken(it)
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<TokenResponse>> =
                movieService.requestValidateToken(trigger.apiKey,trigger.validateBody)

            override fun shouldFetch(data: TokenResponse?): Boolean = true
            override fun loadFromDb(): LiveData<TokenResponse> = movieDao.getToken()
        }.asLiveData()
    }

    fun requestPopularRepository(trigger: PopularTrigger): LiveData<Resource<List<PopularResult>>> {
        return object :
            NetworkBoundResource<List<PopularResult>, PopularResponse>(coroutineContext) {
            override fun saveCallResult(item: PopularResponse) =
                item.results.let {
                    db.runInTransaction {
                        movieDao.savePopular(it)
                    }
                }

            override fun createCall(): LiveData<ApiResponse<PopularResponse>> =
                movieService.requestPopularAPI(trigger.apiKey)

            override fun shouldFetch(data: List<PopularResult>?): Boolean = true

            override fun loadFromDb(): LiveData<List<PopularResult>> = movieDao.getPopular()

        }.asLiveData()
    }

}