package com.nankung.kotlinmvvmstructure.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


@Suppress("DEPRECATION")
fun <VM : ViewModel> AppCompatActivity.obtainMovieViewModel(
    viewModelClass: Class<VM>,
    viewModelFactory: ViewModelProvider.Factory? = null
): VM {
    return if (viewModelFactory == null) {
        ViewModelProviders.of(this, ViewModelFactory.getMovieInstance(application))
            .get(viewModelClass)
    } else {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}

//TODO (MultiRepo) จากนั้นก็มาเช็คเรียก Instance ใหม่ พอจะ Initialize ViewModel ค่อยเรียกเป็นตัวไป ตามที่จะแยก ใน View
@Suppress("DEPRECATION")
fun <VM : ViewModel> AppCompatActivity.obtainViewModel2(
    viewModelClass: Class<VM>,
    viewModelFactory: ViewModelProvider.Factory? = null
): VM {
    return if (viewModelFactory == null) {
        ViewModelProviders.of(this, ViewModelFactory.getInstance2(application)).get(viewModelClass)
    } else {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}

@Suppress("DEPRECATION")
fun <VM : ViewModel> Fragment.obtainMovieViewModel(
    viewModelClass: Class<VM>,
    viewModelFactory: ViewModelProvider.Factory? = null
): VM {
    return if (viewModelFactory == null) {
        ViewModelProviders.of(this, ViewModelFactory.getMovieInstance(this.activity?.application!!))
            .get(viewModelClass)
    } else {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}