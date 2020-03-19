package com.nankung.network.repository

import androidx.lifecycle.LiveData
import com.nankung.network.database.MovieDatabase
import com.nankung.network.database.dao.PeopleDao
import com.nankung.network.engine.trigger.CombinedTrigger
import com.nankung.network.model.response.CombinedResponse
import com.nankung.network.model.response.PeopleResponse
import com.nankung.network.model.response.result.CombinedCastResult
import com.nankung.network.model.response.result.PeopleResult
import com.nankung.network.network.NetworkBoundResource
import com.nankung.network.remote.ApiResponse
import com.nankung.network.remote.ContextProviders
import com.nankung.network.remote.Resource
import com.nankung.network.service.interfaces.PeopleService

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */
class PeopleRepository(
    private val db: MovieDatabase,
    private val peopleDao: PeopleDao,
    private val peopleService: PeopleService,
    private val coroutineContext: ContextProviders
) {
    companion object {
        private var INSTANCE: PeopleRepository? = null

        fun getInstance(
            movieDb: MovieDatabase,
            peopleService: PeopleService
        ): PeopleRepository = INSTANCE
            ?: synchronized(PeopleRepository::class.java) {
                PeopleRepository(
                    movieDb,
                    movieDb.peopleDao(),
                    peopleService,
                    ContextProviders.getInstance()
                )
                    .also { INSTANCE = it }
            }
    }

    fun requestPeopleRepository(api_key: String): LiveData<Resource<List<PeopleResult>>> {
        return object :
            NetworkBoundResource<List<PeopleResult>, PeopleResponse>(coroutineContext) {
            override fun saveCallResult(item: PeopleResponse) {
                item.results.let {
                    db.runInTransaction {
                        peopleDao.savePeople(it)
                    }
                }
            }
            override fun createCall(): LiveData<ApiResponse<PeopleResponse>> =
                peopleService.requestPeoplePopular(api_key)

            override fun shouldFetch(data: List<PeopleResult>?): Boolean = true
            override fun loadFromDb(): LiveData<List<PeopleResult>> = peopleDao.getPeople()
        }.asLiveData()
    }

    fun requestCombinedRepository(trigger: CombinedTrigger): LiveData<Resource<List<CombinedCastResult>>> {
        return object :
            NetworkBoundResource<List<CombinedCastResult>, CombinedResponse>(coroutineContext) {
            override fun saveCallResult(item: CombinedResponse) {
                item.cast.let {
                    peopleDao.deleteComCast()
                    db.runInTransaction {
                        peopleDao.saveComCast(it)
                    }
                }
                item.crew.let {
                    peopleDao.deleteComCrew()
                    db.runInTransaction {
                        peopleDao.saveComCrew(it)
                    }
                }

            }
            override fun createCall(): LiveData<ApiResponse<CombinedResponse>> =
                peopleService.requestCombinedCredit(trigger.id,trigger.apiKey)

            override fun shouldFetch(data: List<CombinedCastResult>?): Boolean = true
            override fun loadFromDb(): LiveData<List<CombinedCastResult>> = peopleDao.getComCast()
        }.asLiveData()
    }

}