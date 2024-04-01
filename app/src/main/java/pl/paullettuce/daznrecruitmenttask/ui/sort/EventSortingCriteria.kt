package pl.paullettuce.daznrecruitmenttask.ui.sort

import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent

sealed interface EventSortingCriteria : SortingCriteria<ViewSportEvent> {
    data object DateAscending : EventSortingCriteria {
        override fun comparator(): Comparator<ViewSportEvent> = compareBy { it.timestamp }
    }
}