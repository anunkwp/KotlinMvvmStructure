package com.nankung.kotlinmvvmstructure.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


@Suppress("DEPRECATION")
fun <T : ViewModel> AppCompatActivity.obtainViewModel(
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return if (viewModelFactory == null) {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)
    } else {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}

fun <T : ViewModel> Fragment.obtainViewModel(
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return if (viewModelFactory == null) {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(this.activity?.application!!)).get(viewModelClass)
    } else {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}