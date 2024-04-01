package pl.paullettuce.daznrecruitmenttask.data.remote

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper.ApiScheduledSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper.ApiSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.data.utils.NetworkManager
import pl.paullettuce.daznrecruitmenttask.domain.exceptions.NoNetworkException
import javax.inject.Inject

class EventsRemoteDataSource @Inject constructor(
    private val apiService: EventsApiService,
    private val networkManager: NetworkManager,
    private val apiSportEventListMapper: ApiSportEventListMapper,
    private val apiScheduledSportEventListMapper: ApiScheduledSportEventListMapper
) {

    fun getEvents() = executeIfNetworkAvailable(apiService.getEvents())
        .map { apiSportEventListMapper.map(it) }

    fun getScheduled() = executeIfNetworkAvailable(apiService.getScheduled())
        .map { apiScheduledSportEventListMapper.map(it) }

    private inline fun <reified T : Any> executeIfNetworkAvailable(single: Single<T>): Single<T> {
        return if (!networkManager.isNetworkAvailable()) {
            Single.error(NoNetworkException())
        } else {
            single
        }
    }
}
