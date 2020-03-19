package com.nankung.bottomnavex.ui.notifications

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nankung.common.module.base.viewmodel.BaseSharedViewModel
import com.nankung.network.repository.MovieRepository
import com.nankung.network.repository.PeopleRepository

class NotificationsViewModel  (application: Application, private val movieRepository: MovieRepository) :
BaseSharedViewModel(application) {

}