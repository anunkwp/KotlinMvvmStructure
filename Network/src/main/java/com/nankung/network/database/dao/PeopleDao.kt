package com.nankung.network.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nankung.network.model.response.result.CombinedResult
import com.nankung.network.model.response.result.PeopleResult

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */
@Dao
interface PeopleDao {

    @Query("SELECT * FROM people")
    fun getPeople(): LiveData<List<PeopleResult>>

    @Query("SELECT * FROM combined")
    fun getCombined():LiveData<List<CombinedResult>>

    @Query("DELETE FROM combined")        //วิธีการที่กรณีข้อมูลใน API แล้ว Response เหมือนกันและเป็นการ insert เพื่ออัพเดทจาก ID แต่ว่ามันคนละ ID
    fun deleteCombined()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePeople(people: List<PeopleResult>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCombined(combined:List<CombinedResult>?)
}