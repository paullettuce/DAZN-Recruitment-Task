package pl.paullettuce.daznrecruitmenttask.ui.sort

import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent

val DateAscending: Comparator<ViewSportEvent> =
    compareBy { it.timestamp }