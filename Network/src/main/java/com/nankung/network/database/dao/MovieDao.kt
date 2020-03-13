package com.nankung.network.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.nankung.network.model.response.TokenResponse
import com.nankung.network.model.response.result.*


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_all")
    fun getMovies(): LiveData<List<MoviesResult>>

    @Query("DELETE FROM movies_all")        //วิธีการที่กรณีข้อมูลใน API แล้ว Response เหมือนกันและเป็นการ insert เพื่ออัพเดทจาก ID แต่ว่ามันคนละ ID
    fun deleteMovie()                               // ถ้า ID เดิมมันก็จะอัพเดทให้ แต่ถ้าไม่ ก็ลบก่อนแล้วค่อย Insert เป็นตัวใหม่ (อยู่ในช่วงกำลังศึกษา)

    @Query("SELECT * FROM gen_token")
    fun getToken(): LiveData<TokenResponse>

    @Insert(onConflict = REPLACE)
    fun saveToken(token: TokenResponse)

    @Insert(onConflict = REPLACE)
    fun saveMovies(movies: List<MoviesResult>?)


}