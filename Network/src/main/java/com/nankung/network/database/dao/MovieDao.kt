package com.nankung.network.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.nankung.network.model.response.TokenResponse
import com.nankung.network.model.response.result.*


@Dao
interface MovieDao {

    @Query("SELECT * FROM now_play")
    fun getNowplay(): LiveData<List<NowPlayingResult>>

    @Query("SELECT * FROM upcoming")
    fun getUpcoming(): LiveData<List<UpcomingResult>>

    @Query("SELECT * FROM top_rate")
    fun getTopRated(): LiveData<List<TopRatedResult>>

    @Query("SELECT * FROM popular")
    fun getPopular(): LiveData<List<PopularResult>>

    @Query("SELECT * FROM gen_token")
    fun getToken(): LiveData<TokenResponse>

    @Insert(onConflict = REPLACE)
    fun saveToken(token: TokenResponse)

    @Insert(onConflict = REPLACE)
    fun saveNowPlay(nowPlay: List<NowPlayingResult>?)

    @Insert(onConflict = REPLACE)
    fun saveUpcoming(upcoming: List<UpcomingResult>?)

    @Insert(onConflict = REPLACE)
    fun saveTopRated(nowPlay: List<TopRatedResult>?)

    @Insert(onConflict = REPLACE)
    fun savePopular(popular: List<PopularResult>?)


}