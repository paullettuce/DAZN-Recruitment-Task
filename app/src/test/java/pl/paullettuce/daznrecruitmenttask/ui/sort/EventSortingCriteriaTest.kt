package pl.paullettuce.daznrecruitmenttask.ui.sort

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent

class EventSortingCriteriaTest {

    @Test
    fun `test DateAscending comparator`() {
        val dateAscendingComparator = EventSortingCriteria.DateAscending.comparator()

        // Create events with different timestamps
        val event1 = createEvent(1000)
        val event2 = createEvent(2000)
        val event3 = createEvent(1500)

        // Sort the events using the DateAscending comparator
        val sortedEvents = listOf(event1, event2, event3).sortedWith(dateAscendingComparator)

        // Ensure that events are sorted in ascending order of timestamp
        assertEquals(listOf(event1, event3, event2), sortedEvents)
    }

    private fun createEvent(timestamp: Long) =
        ViewSportEvent("", "", "", timestamp, "", "", "")
}
