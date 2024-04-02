package pl.paullettuce.daznrecruitmenttask.ui.filter

import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import pl.paullettuce.daznrecruitmenttask.ui.utils.getStartAndEndTimestampsForDayOffset

sealed interface EventFilteringCriteria : FilteringCriteria<ViewSportEvent> {

    sealed class TimeRange(
        private val start: Long,
        private val end: Long
    ) : EventFilteringCriteria {
        constructor(pair: Pair<Long, Long>) : this(pair.first, pair.second)

        override fun isMatching(item: ViewSportEvent) = item.timestamp in start..end

        data object None : TimeRange(-1, -1) {
            override fun isMatching(item: ViewSportEvent) = true
        }

        data object Today : TimeRange(getStartAndEndTimestampsForDayOffset(0))
        data object Tomorrow : TimeRange(getStartAndEndTimestampsForDayOffset(1))
        data object Yesterday : TimeRange(getStartAndEndTimestampsForDayOffset(-1))
        data class Custom(val start: Long, val end: Long) : TimeRange(start, end)
    }
}