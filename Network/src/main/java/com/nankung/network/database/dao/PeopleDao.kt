package com.nankung.network.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nankung.network.model.response.CombinedResponse
import com.nankung.network.model.response.result.CombinedCastResult
import com.nankung.network.model.response.result.CombinedCrewResult
import com.nankung.network.model.response.result.PeopleResult

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */
@Dao
interface PeopleDao {

    @Query("SELECT * FROM people")
    fun getPeople(): LiveData<List<PeopleResult>>

    @Query("SELECT * FROM combined_cast")
    fun getComCast(): LiveData<List<CombinedCastResult>>

    @Query("SELECT * FROM combined_crew")
    fun getComCrew(): LiveData<List<CombinedCrewResult>>

    @Query("DELETE FROM combined_cast")        //วิธีการที่กรณีข้อมูลใน API แล้ว Response เหมือนกันและเป็นการ insert เพื่ออัพเดทจาก ID แต่ว่ามันคนละ ID
    fun deleteComCast()

    @Query("DELETE FROM combined_crew")
    fun deleteComCrew()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePeople(people: List<PeopleResult>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveComCast(combined: List<CombinedCastResult>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveComCrew(combined: List<CombinedCrewResult>?)
}