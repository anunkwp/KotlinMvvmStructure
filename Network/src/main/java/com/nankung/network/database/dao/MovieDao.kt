package com.nankung.network.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.nankung.network.model.PopularResult
import com.nankung.network.model.TokenResponse


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_popular")
    fun getPopular(): LiveData<List<PopularResult>>

    @Query("SELECT * FROM gen_token")
    fun getToken(): LiveData<TokenResponse>

    @Insert(onConflict = REPLACE)
    fun saveToken(token:TokenResponse)

    @Insert(onConflict = REPLACE)
    fun savePopular(popular: List<PopularResult>?)


}