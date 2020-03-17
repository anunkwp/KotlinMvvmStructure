package com.nankung.kotlinmvvmstructure.util

import com.nankung.kotlinmvvmstructure.R
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.remote.Resource

/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */
object ResponseConverter {


    fun convertTopRateDESC(movie: Resource<List<MoviesResult>>): List<MoviesResult>? {
        movie.data
            ?.sortedByDescending {it.vote_average }
            .let {
                return it
            }
    }



}