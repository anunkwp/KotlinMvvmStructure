package com.nankung.common.module.base.viewmodel

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.nankung.common.module.base.mvvm.bus.EventBusLiveData

/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */
abstract class BaseSharedViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        lateinit var busViewModel: EventBusLiveData
            private set
    }

    init {
        busViewModel =
            EventBusLiveData()
    }

    fun postEvent(obj: Any) {
        busViewModel.post(obj)
    }

    fun subscribeNewBus(activity: FragmentActivity, observer: Observer<Any>) {
        busViewModel.newSubscribe(activity, observer)
    }

    fun subscribeBus(activity: FragmentActivity, observer: Observer<Any>) {
        busViewModel.subscribe(activity, observer)
    }

    fun subscribeBus(fragment: Fragment, observer: Observer<Any>) {
        busViewModel.subscribe(fragment, observer)
    }

}