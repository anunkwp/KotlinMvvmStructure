package com.nankung.network.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.nankung.network.model.PopularResult


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_popular")
    fun getPopular(): LiveData<List<PopularResult>>

    @Insert(onConflict = REPLACE)
    fun savePopular(popular: List<PopularResult>?)
}