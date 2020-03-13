package com.nankung.kotlinmvvmstructure.view.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nankung.common.module.base.livedata.SingleLiveEvent
import com.nankung.network.engine.trigger.PopularTrigger
import com.nankung.network.model.response.result.*
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.MovieRepository

//What is LiveData ,Observe,ViewModel or switchMap?
// -> Read Here:  https://bit.ly/3cVrHoy

class MainViewModel(application: Application, private val movieRepository: MovieRepository) :
    AndroidViewModel(application) {
    private val triggerPopular = SingleLiveEvent<PopularTrigger>()

    fun requestPopularResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {

                movieRepository.requestPopularRepository(trigger)
            }
        }


    fun requestTopRatedResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestTopRatedRepository(trigger)
            }
        }

    fun requestUpcomingResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestUpcomingRepository(trigger)
            }
        }

    fun requestNowPlayingResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestNowPlayingRepository(trigger)
            }
        }

    fun initPopularData(api_key: String) {
        PopularTrigger(api_key).let {
            // invoke นี้อยู่ใน SingleLiveEvent โดยเอา keys เข้า ObServer ปกติ แบบ triggerPopular.value = api_key
            triggerPopular.invoke(it)
        }
    }
}