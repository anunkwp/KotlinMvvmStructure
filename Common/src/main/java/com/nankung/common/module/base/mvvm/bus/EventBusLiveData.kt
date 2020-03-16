package com.nankung.common.module.base.mvvm.bus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */
class EventBusLiveData {
    private var livedata: MutableLiveData<Any> = MutableLiveData()

    fun post(obj: Any) {
        livedata.value = obj
    }

    fun newSubscribe(lifecycle: LifecycleOwner, observer: Observer<Any>) {
        livedata = MutableLiveData()
        subscribe(lifecycle, observer)
    }

    fun subscribe(lifecycle: LifecycleOwner, observer: Observer<Any>) {
        livedata.observe(lifecycle, observer)
    }
}