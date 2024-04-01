package pl.paullettuce.daznrecruitmenttask.data.repository

import pl.paullettuce.daznrecruitmenttask.data.remote.EventsRemoteDataSource
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsRemoteDataSource: EventsRemoteDataSource
) {

    fun getEvents() = eventsRemoteDataSource.getEvents()

    fun getScheduled() = eventsRemoteDataSource.getScheduled()
}
