package com.nankung.kotlinmvvmstructure.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nankung.common.module.base.livedata.SingleLiveEvent
import com.nankung.common.module.base.viewmodel.BaseSharedViewModel
import com.nankung.network.engine.trigger.PopularTrigger
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.MovieRepository

class HomeViewModel (application: Application, private val movieRepository: MovieRepository) :
    BaseSharedViewModel(application) {
    private val trigger = SingleLiveEvent<PopularTrigger>()

    fun requestPopularResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(trigger) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestPopularRepository(trigger)
            }
        }


    fun requestTopRatedResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(trigger) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestTopRatedRepository(trigger)
            }
        }

    fun requestUpcomingResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(trigger) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestUpcomingRepository(trigger)
            }
        }

    fun requestNowPlayingResource(): LiveData<Resource<List<MoviesResult>>> =
        Transformations.switchMap(trigger) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestNowPlayingRepository(trigger)
            }
        }

    fun initPopularData(api_key: String) {
        PopularTrigger(api_key).let {
            // invoke นี้อยู่ใน SingleLiveEvent โดยเอา keys เข้า ObServer ปกติ แบบ triggerPopular.value = api_key
            trigger.invoke(it)
        }
    }
}