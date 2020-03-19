package com.nankung.kotlinmvvmstructure.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nankung.bottomnavex.ui.notifications.NotificationsViewModel
import com.nankung.kotlinmvvmstructure.ui.login.LoginViewModel
import com.nankung.kotlinmvvmstructure.ui.home.HomeViewModel
import com.nankung.kotlinmvvmstructure.ui.main.MainViewModel
import com.nankung.network.database.MovieDatabase
import com.nankung.network.repository.MovieRepository
import com.nankung.network.service.ApiServiceFactory


class MovieViewModelFactory(
    private val application: Application,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM =
        with(modelClass) {
            //ตรงนี้เหมือนมาประกาศ Assign ViewModel ว่า ViewModel นั้นจะผูกกับ Repo ไหน
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(application, movieRepository)
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(application, movieRepository)
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(application, movieRepository)
                isAssignableFrom(NotificationsViewModel::class.java) ->
                    NotificationsViewModel(application, movieRepository)
                else ->
                    error("Invalid View Model class")
            }
        } as VM

    companion object {
        @Volatile
        private var INSTANCE: MovieViewModelFactory? = null

        fun getMovieInstance(application: Application): MovieViewModelFactory {
            return INSTANCE
                ?: synchronized(MovieViewModelFactory::class.java) {
                    MovieViewModelFactory(
                        application,
                        MovieRepository.getInstance(
                            MovieDatabase.getDatabase(application.applicationContext),
                            ApiServiceFactory.getMovieService()
                        )
                    )
                        .also { INSTANCE = it }
                }
        }
    }
}