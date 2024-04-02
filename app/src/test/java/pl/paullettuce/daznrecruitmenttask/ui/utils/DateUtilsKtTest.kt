package pl.paullettuce.daznrecruitmenttask.ui.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class TimestampsForDayOffsetTest {

    @Test
    fun `test getStartAndEndTimestampsForDayOffset`() {
        val currentTime = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTime

        // Calculate timestamps for today
        val todayStart = calendar.clone() as Calendar
        todayStart.set(Calendar.HOUR_OF_DAY, 0)
        todayStart.set(Calendar.MINUTE, 0)
        todayStart.set(Calendar.SECOND, 0)
        todayStart.set(Calendar.MILLISECOND, 0)

        val todayEnd = calendar.clone() as Calendar
        todayEnd.set(Calendar.HOUR_OF_DAY, 23)
        todayEnd.set(Calendar.MINUTE, 59)
        todayEnd.set(Calendar.SECOND, 59)
        todayEnd.set(Calendar.MILLISECOND, 999)

        val todayTimestamps = getStartAndEndTimestampsForDayOffset(0)
        assertEquals(todayStart.timeInMillis, todayTimestamps.first)
        assertEquals(todayEnd.timeInMillis, todayTimestamps.second)

        // Calculate timestamps for yesterday
        val yesterdayStart = calendar.clone() as Calendar
        yesterdayStart.add(Calendar.DAY_OF_YEAR, -1)
        yesterdayStart.set(Calendar.HOUR_OF_DAY, 0)
        yesterdayStart.set(Calendar.MINUTE, 0)
        yesterdayStart.set(Calendar.SECOND, 0)
        yesterdayStart.set(Calendar.MILLISECOND, 0)

        val yesterdayEnd = calendar.clone() as Calendar
        yesterdayEnd.add(Calendar.DAY_OF_YEAR, -1)
        yesterdayEnd.set(Calendar.HOUR_OF_DAY, 23)
        yesterdayEnd.set(Calendar.MINUTE, 59)
        yesterdayEnd.set(Calendar.SECOND, 59)
        yesterdayEnd.set(Calendar.MILLISECOND, 999)

        val yesterdayTimestamps = getStartAndEndTimestampsForDayOffset(-1)
        assertEquals(yesterdayStart.timeInMillis, yesterdayTimestamps.first)
        assertEquals(yesterdayEnd.timeInMillis, yesterdayTimestamps.second)
    }
}
