package com.nankung.kotlinmvvmstructure.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nankung.kotlinmvvmstructure.ui.login.LoginViewModel
import com.nankung.kotlinmvvmstructure.ui.main.HomeViewModel
import com.nankung.kotlinmvvmstructure.ui.main.MainViewModel
import com.nankung.network.database.MovieDatabase
import com.nankung.network.repository.MovieRepository
import com.nankung.network.service.ApiServiceFactory


class ViewModelFactory(
    private val application: Application,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(application, movieRepository)
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(application, movieRepository)
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(application, movieRepository)
                else ->
                    error("Invalid View Model class")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            return INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    ViewModelFactory(
                        application,
                        MovieRepository.getInstance(
                            MovieDatabase.getDatabase(application.applicationContext),
                            ApiServiceFactory.getService()
                        )
                    )
                        .also { INSTANCE = it }
                }
        }

    }

}