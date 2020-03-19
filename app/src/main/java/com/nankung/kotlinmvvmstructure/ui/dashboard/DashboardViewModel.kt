package com.nankung.kotlinmvvmstructure.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nankung.common.module.base.livedata.SingleLiveEvent
import com.nankung.common.module.base.viewmodel.BaseSharedViewModel
import com.nankung.network.engine.trigger.CombinedTrigger
import com.nankung.network.model.response.result.CombinedCastResult
import com.nankung.network.model.response.result.PeopleResult
import com.nankung.network.remote.AbsentLiveData
import com.nankung.network.remote.Resource
import com.nankung.network.repository.PeopleRepository

class DashboardViewModel (application: Application, private val peopleRepository: PeopleRepository) :
    BaseSharedViewModel(application)  {

    private val api_key = SingleLiveEvent<String>()
    private val triggerCombined = SingleLiveEvent<CombinedTrigger>()

    val requestPeopleResource: LiveData<Resource<List<PeopleResult>>> =
        Transformations.switchMap(api_key) { apiKey ->
            if (apiKey == null) {
                AbsentLiveData.create()
            } else {
                peopleRepository.requestPeopleRepository(apiKey)
            }
        }

    val requestCombined:LiveData<Resource<List<CombinedCastResult>>> =
        Transformations.switchMap(triggerCombined){trigger ->
            if (trigger == null){
                AbsentLiveData.create()
            }else{
                peopleRepository.requestCombinedRepository(trigger)
            }
        }

    fun initKeys(key:String){
        api_key.invoke(key)
    }

    fun initIdCombined(key:String,id:String){
        CombinedTrigger(key,id).let {
            this.triggerCombined.invoke(it)
        }
    }
}