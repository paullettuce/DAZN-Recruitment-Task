package pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper

import pl.paullettuce.daznrecruitmenttask.data.remote.model.ApiScheduledSportEvent
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import pl.paullettuce.daznrecruitmenttask.domain.model.mapper.Mapper
import pl.paullettuce.daznrecruitmenttask.domain.model.mapper.SimpleListMapper
import javax.inject.Inject

class ApiScheduledSportEventMapper @Inject constructor(
    private val dateParser: ApiDateParser
) : Mapper<ApiScheduledSportEvent, SportEvent> {
    override fun map(input: ApiScheduledSportEvent) = SportEvent(
        input.id,
        input.title,
        input.subtitle,
        dateParser.map(input.date),
        input.imageUrl,
        "" // event is only scheduled so no url yet
    )
}

class ApiScheduledSportEventListMapper @Inject constructor(itemMapper: ApiScheduledSportEventMapper) :
    SimpleListMapper<ApiScheduledSportEvent, SportEvent>(itemMapper)

