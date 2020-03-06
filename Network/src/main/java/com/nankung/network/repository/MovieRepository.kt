package com.nankung.network.repository

import androidx.lifecycle.LiveData
import com.nankung.network.model.dao.MovieDao
import com.nankung.network.database.MovieDatabase
import com.nankung.network.model.PopularResponse
import com.nankung.network.model.PopularResult
import com.nankung.network.remote.ApiResponse
import com.nankung.network.remote.ContextProviders
import com.nankung.network.remote.NetworkBoundResource
import com.nankung.network.remote.Resource
import com.nankung.network.service.MovieService

class MovieRepository(
    private val db : MovieDatabase,
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
                MovieRepository(movieDb, movieDb.movieDao(), movieService, ContextProviders.getInstance())
                    .also { INSTANCE = it }
            }
    }

    fun requestPopularRepository(api_key: String): LiveData<Resource<List<PopularResult>>> {
        return object :
            NetworkBoundResource<List<PopularResult>, PopularResponse>(coroutineContext) {
            override fun saveCallResult(item: PopularResponse) =
                item.results.let {
                    db.runInTransaction{
                        movieDao.savePopular(it)
                    }
                }
            override fun createCall(): LiveData<ApiResponse<PopularResponse>> =
                movieService.requestPopularAPI(api_key)

            override fun shouldFetch(data: List<PopularResult>?): Boolean = true

            override fun loadFromDb(): LiveData<List<PopularResult>> = movieDao.getPopular()

        }.asLiveData()
    }

}