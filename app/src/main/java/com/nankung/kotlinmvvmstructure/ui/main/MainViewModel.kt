package com.nankung.kotlinmvvmstructure.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nankung.common.module.base.livedata.SingleLiveEvent
import com.nankung.common.module.base.viewmodel.BaseSharedViewModel
import com.nankung.network.engine.trigger.PopularTrigger
import com.nankung.network.model.response.result.*
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.MovieRepository

//What is LiveData ,Observe,ViewModel or switchMap?
// -> Read Here:  https://bit.ly/3cVrHoy

class MainViewModel(application: Application, private val movieRepository: MovieRepository) :
    BaseSharedViewModel(application) {

    //ยังไม่ได้ทำอะไร
}