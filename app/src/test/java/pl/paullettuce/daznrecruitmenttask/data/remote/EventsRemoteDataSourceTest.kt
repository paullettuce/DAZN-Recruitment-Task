package pl.paullettuce.daznrecruitmenttask.data.remote

import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import pl.paullettuce.daznrecruitmenttask.data.remote.model.ApiScheduledSportEvent
import pl.paullettuce.daznrecruitmenttask.data.remote.model.ApiSportEvent
import pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper.ApiScheduledSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper.ApiSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.data.utils.NetworkManager
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import pl.paullettuce.daznrecruitmenttask.domain.model.exceptions.NoNetworkException

class EventsRemoteDataSourceTest {

    private lateinit var apiService: EventsApiService
    private lateinit var networkManager: NetworkManager
    private lateinit var apiSportEventListMapper: ApiSportEventListMapper
    private lateinit var apiScheduledSportEventListMapper: ApiScheduledSportEventListMapper
    private lateinit var eventsRemoteDataSource: EventsRemoteDataSource

    @Before
    fun setup() {
        apiService = mock()
        networkManager = mock()
        apiSportEventListMapper = mock()
        apiScheduledSportEventListMapper = mock()
        eventsRemoteDataSource = EventsRemoteDataSource(
            apiService,
            networkManager,
            apiSportEventListMapper,
            apiScheduledSportEventListMapper
        )
    }

    @Test
    fun `getEvents should return mapped events when network is available`() {
        // Given
        val apiResponse: List<ApiSportEvent> = mock()
        val mappedEvents: List<SportEvent> = mock()
        doReturn(Single.just(apiResponse)).`when`(apiService).getEvents()
        doReturn(true).`when`(networkManager).isNetworkAvailable()
        doReturn(mappedEvents).`when`(apiSportEventListMapper).map(apiResponse)

        // When
        val result = eventsRemoteDataSource.getEvents().blockingGet()

        // Then
        assertEquals(mappedEvents, result)
        verify(apiService).getEvents()
        verify(networkManager).isNetworkAvailable()
        verify(apiSportEventListMapper).map(apiResponse)
    }

    @Test
    fun `getEvents should return NoNetworkException when network is not available`() {
        // Given
        doReturn(false).`when`(networkManager).isNetworkAvailable()

        // When
        val testObserver = eventsRemoteDataSource.getEvents().test()

        // Then
        testObserver.assertError(NoNetworkException::class.java)
        verify(networkManager).isNetworkAvailable()
        verifyNoMoreInteractions(apiSportEventListMapper)
    }

    @Test
    fun `getScheduled should return mapped scheduled events when network is available`() {
        // Given
        val apiResponse: List<ApiScheduledSportEvent> = mock()
        val mappedScheduledEvents: List<SportEvent> = mock()
        doReturn(Single.just(apiResponse)).`when`(apiService).getScheduled()
        doReturn(true).`when`(networkManager).isNetworkAvailable()
        doReturn(mappedScheduledEvents).`when`(apiScheduledSportEventListMapper).map(apiResponse)

        // When
        val result = eventsRemoteDataSource.getScheduled().blockingGet()

        // Then
        assertEquals(mappedScheduledEvents, result)
        verify(apiService).getScheduled()
        verify(networkManager).isNetworkAvailable()
        verify(apiScheduledSportEventListMapper).map(apiResponse)
    }

    @Test
    fun `getScheduled should return NoNetworkException when network is not available`() {
        // Given
        doReturn(false).`when`(networkManager).isNetworkAvailable()

        // When
        val testObserver = eventsRemoteDataSource.getScheduled().test()

        // Then
        testObserver.assertError(NoNetworkException::class.java)
        verify(networkManager).isNetworkAvailable()
        verifyNoMoreInteractions(apiScheduledSportEventListMapper)
    }
}
