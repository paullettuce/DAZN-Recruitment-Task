package pl.paullettuce.daznrecruitmenttask.ui.utils

import java.util.Calendar

/**
 * Calculates the minimum and maximum timestamps for a specified day offset from the current day.
 *
 * @param dayOffset The number of days to offset from the current day. Positive values represent future days, negative values represent past days.
 * @return A Pair containing the start and end timestamps of the specified day. The start timestamp represents the beginning of the day (00:00:00) and the end timestamp represents the end of the day (23:59:59.999).
 */
fun getStartAndEndTimestampsForDayOffset(dayOffset: Int): Pair<Long, Long> {
    val calendar = Calendar.getInstance()

    // Set the calendar to the specified day
    calendar.add(Calendar.DAY_OF_YEAR, dayOffset)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    // Get the start timestamp of the specified day
    val startTimestamp = calendar.timeInMillis

    // Set the calendar to the end of the specified day
    calendar.add(Calendar.DAY_OF_YEAR, 1)
    calendar.add(Calendar.MILLISECOND, -1)

    // Get the end timestamp of the specified day
    val endTimestamp = calendar.timeInMillis

    return Pair(startTimestamp, endTimestamp)
}