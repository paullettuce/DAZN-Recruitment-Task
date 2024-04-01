package pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper

import pl.paullettuce.daznrecruitmenttask.data.remote.model.ApiSportEvent
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import pl.paullettuce.daznrecruitmenttask.domain.model.mapper.Mapper
import pl.paullettuce.daznrecruitmenttask.domain.model.mapper.SimpleListMapper
import javax.inject.Inject

class ApiSportEventMapper @Inject constructor(
    private val dateParser: ApiDateParser
) : Mapper<ApiSportEvent, SportEvent> {
    override fun map(input: ApiSportEvent) = SportEvent(
        input.id,
        input.title,
        input.subtitle,
        dateParser.map(input.date),
        input.imageUrl,
        input.videoUrl.orEmpty()
    )
}

class ApiSportEventListMapper @Inject constructor(itemMapper: ApiSportEventMapper) :
    SimpleListMapper<ApiSportEvent, SportEvent>(itemMapper)

