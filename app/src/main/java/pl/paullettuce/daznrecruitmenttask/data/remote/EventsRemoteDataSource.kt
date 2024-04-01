package pl.paullettuce.daznrecruitmenttask.data.remote

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.daznrecruitmenttask.data.remote.model.toSportEvent
import pl.paullettuce.daznrecruitmenttask.data.utils.NetworkManager
import pl.paullettuce.daznrecruitmenttask.domain.exceptions.NoNetworkException
import javax.inject.Inject

class EventsRemoteDataSource @Inject constructor(
    private val apiService: EventsApiService,
    private val networkManager: NetworkManager
) {

    fun getEvents() = executeIfNetworkAvailable(apiService.getEvents())
        .map { apiEvents -> apiEvents.map { it.toSportEvent() } }

    fun getScheduled() = executeIfNetworkAvailable(apiService.getScheduled())
        .map { apiScheduledEvents -> apiScheduledEvents.map { it.toSportEvent() } }

    private inline fun <reified T : Any> executeIfNetworkAvailable(single: Single<T>): Single<T> {
        return if (!networkManager.isNetworkAvailable()) {
            Single.error(NoNetworkException())
        } else {
            single
        }
    }
}
