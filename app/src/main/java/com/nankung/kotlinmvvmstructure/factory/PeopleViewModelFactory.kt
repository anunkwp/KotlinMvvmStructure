package com.nankung.kotlinmvvmstructure.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nankung.kotlinmvvmstructure.ui.dashboard.DashboardViewModel
import com.nankung.network.database.MovieDatabase
import com.nankung.network.repository.PeopleRepository
import com.nankung.network.service.ApiServiceFactory

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */
class PeopleViewModelFactory(
    private val application: Application,
    private val peopleRepository: PeopleRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM =
        with(modelClass) {
            when {
                isAssignableFrom(DashboardViewModel::class.java) ->
                    DashboardViewModel(application, peopleRepository)
                else ->
                    error("Invalid View Model class")
            }
        } as VM

    companion object {
        @Volatile
        private var INSTANCE: PeopleViewModelFactory? = null

        fun getPeopleInstance(application: Application): PeopleViewModelFactory {
            return INSTANCE
                ?: synchronized(PeopleViewModelFactory::class.java) {
                    PeopleViewModelFactory(
                        application,
                        PeopleRepository.getInstance(
                            MovieDatabase.getDatabase(application.applicationContext),
                            ApiServiceFactory.getPeopleService()
                        )
                    )
                        .also { INSTANCE = it }
                }
        }
    }
}