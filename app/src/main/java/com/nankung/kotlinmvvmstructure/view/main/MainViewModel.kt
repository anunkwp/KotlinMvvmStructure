package com.nankung.kotlinmvvmstructure.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nankung.network.model.PopularResult
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.MovieRepository

class MainViewModel(application : Application, private val movieRepository: MovieRepository) :  AndroidViewModel(application){

    private val apiKey = MutableLiveData<String>()

    val requestPopularResource: LiveData<Resource<List<PopularResult>>> =
        Transformations.switchMap(apiKey) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            movieRepository.requestPopularRepository(id)
        }
    }

    fun initData(api_key : String) {
        this.apiKey.value = api_key
    }

}