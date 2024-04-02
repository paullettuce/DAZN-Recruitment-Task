package pl.paullettuce.daznrecruitmenttask.ui.filter

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent

class EventFilteringCriteriaTest {

    @Test
    fun `test TimeRange None`() {
        val noneTimeRange = EventFilteringCriteria.TimeRange.None
        val event = createEvent(1000) // Any event, timestamp doesn't matter

        assertTrue(noneTimeRange.isMatching(event)) // None TimeRange should always match
    }

    @Test
    fun `test TimeRange Today`() {
        val todayTimeRange = EventFilteringCriteria.TimeRange.Today
        val currentTime = System.currentTimeMillis()
        val eventToday = createEvent(currentTime) // Event with current timestamp

        assertTrue(todayTimeRange.isMatching(eventToday)) // Today TimeRange should match events with today's timestamp
    }

    @Test
    fun `test TimeRange Tomorrow`() {
        val tomorrowTimeRange = EventFilteringCriteria.TimeRange.Tomorrow
        val currentTime = System.currentTimeMillis()
        val eventTomorrow =
            createEvent(currentTime + 24 * 60 * 60 * 1000) // Event with timestamp of tomorrow

        assertTrue(tomorrowTimeRange.isMatching(eventTomorrow)) // Tomorrow TimeRange should match events with tomorrow's timestamp
    }

    @Test
    fun `test TimeRange Yesterday`() {
        val yesterdayTimeRange = EventFilteringCriteria.TimeRange.Yesterday
        val currentTime = System.currentTimeMillis()
        val eventYesterday =
            createEvent(currentTime - 24 * 60 * 60 * 1000) // Event with timestamp of yesterday

        assertTrue(yesterdayTimeRange.isMatching(eventYesterday)) // Yesterday TimeRange should match events with yesterday's timestamp
    }

    @Test
    fun `test TimeRange Custom`() {
        val customTimeRange = EventFilteringCriteria.TimeRange.Custom(
            1000,
            2000
        ) // Custom TimeRange from 1000 to 2000
        val eventInRange = createEvent(1500) // Event with timestamp in the range
        val eventOutOfRange = createEvent(3000) // Event with timestamp outside the range

        assertTrue(customTimeRange.isMatching(eventInRange)) // Custom TimeRange should match events within its range
        assertFalse(customTimeRange.isMatching(eventOutOfRange)) // Custom TimeRange should not match events outside its range
    }

    private fun createEvent(timestamp: Long) =
        ViewSportEvent("", "", "", timestamp, "", "", "")
}
