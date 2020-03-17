package com.nankung.network.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.network.database.dao.MovieDao
import com.nankung.network.database.dao.PeopleDao
import com.nankung.network.model.response.result.PeopleResult
import com.nankung.network.model.response.TokenResponse
import com.nankung.network.model.response.result.CombinedResult

@Database(entities = [MoviesResult::class, TokenResponse::class, PeopleResult::class, CombinedResult::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun peopleDao(): PeopleDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}