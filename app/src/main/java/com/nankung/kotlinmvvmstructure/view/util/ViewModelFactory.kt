package com.nankung.kotlinmvvmstructure.view.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nankung.kotlinmvvmstructure.view.main.MainViewModel
import com.nankung.network.database.MovieDatabase
import com.nankung.network.repository.MovieRepository
import com.nankung.network.service.MovieServiceFactory


class ViewModelFactory private constructor(
    private val application: Application,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(application, movieRepository)
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
                        MovieServiceFactory.getService()
                    )
                )
                    .also { INSTANCE = it }
            }
        }
    }
}