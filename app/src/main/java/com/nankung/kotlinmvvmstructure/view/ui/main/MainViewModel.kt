package com.nankung.kotlinmvvmstructure.view.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nankung.network.engine.trigger.PopularTrigger
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.MovieRepository
import kotlinx.coroutines.Job

//What is LiveData ,Observe,ViewModel or switchMap
// -> Read Here:  https://bit.ly/3cVrHoy

class MainViewModel(application: Application, private val movieRepository: MovieRepository) :
    AndroidViewModel(application) {
    private val triggerPopular = MutableLiveData<PopularTrigger>()
    private val api_key = MutableLiveData<String>()
    var job: Job? = null

    val requestPopularResource: LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestPopularRepository(trigger)
            }
        }
    val requestTopRatedResource : LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestTopRatedRepository(trigger)
            }
        }

    val requestUpcomingResource : LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestUpcomingRepository(trigger)
            }
        }
    val requestNowPlayingResource : LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestNowPlayingRepository(trigger)
            }
        }

    fun initPopularData(api_key: String) {
        PopularTrigger(api_key).let {
            this.triggerPopular.value = it
            this.api_key.value = api_key
        }
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}