package pl.paullettuce.daznrecruitmenttask.data.remote.model

import pl.paullettuce.daznrecruitmenttask.data.utils.toTimestamp
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent

data class ApiSportEvent(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String?
)

// Mapper functions
fun ApiSportEvent.toSportEvent() = SportEvent(
    id, title, subtitle, date.toTimestamp(), imageUrl, videoUrl.orEmpty()
)