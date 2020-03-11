package com.nankung.kotlinmvvmstructure.view.module.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nankung.network.engine.trigger.PopularTrigger
import com.nankung.network.engine.trigger.ValidateTrigger
import com.nankung.network.model.PopularResult
import com.nankung.network.model.TokenResponse
import com.nankung.network.model.body.ValidateBody
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.MovieRepository
import kotlinx.coroutines.Job

class MainViewModel(application: Application, private val movieRepository: MovieRepository) :
    AndroidViewModel(application) {
    private val triggerPopular = MutableLiveData<PopularTrigger>()
    private val api_key = MutableLiveData<String>()
    var job: Job? = null

    val requestPopularResource: LiveData<Resource<List<PopularResult>>> =
        Transformations.switchMap(triggerPopular) { trigger ->
            if (trigger == null) {
                AbsentLiveData.create()
            } else {
                movieRepository.requestPopularRepository(trigger)
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