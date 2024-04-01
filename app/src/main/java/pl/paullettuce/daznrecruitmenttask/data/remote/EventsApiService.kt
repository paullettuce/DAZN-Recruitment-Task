package pl.paullettuce.daznrecruitmenttask.data.remote

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.daznrecruitmenttask.data.remote.model.ApiScheduledSportEvent
import pl.paullettuce.daznrecruitmenttask.data.remote.model.ApiSportEvent
import retrofit2.http.GET

interface EventsApiService {
    @GET("getEvents")
    fun getEvents(): Single<List<ApiSportEvent>>

    @GET("getSchedule")
    fun getScheduled(): Single<List<ApiScheduledSportEvent>>
}