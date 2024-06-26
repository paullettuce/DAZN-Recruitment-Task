package pl.paullettuce.daznrecruitmenttask.ui.model.mapper

import pl.paullettuce.daznrecruitmenttask.core.model.mapper.Mapper
import pl.paullettuce.daznrecruitmenttask.core.model.mapper.SimpleListMapper
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import javax.inject.Inject

class ViewSportEventMapper @Inject constructor(
    private val friendlyDateMapper: FriendlyDateMapper
) : Mapper<SportEvent, ViewSportEvent> {

    override fun map(input: SportEvent) = ViewSportEvent(
        input.id,
        input.title,
        input.subtitle,
        input.timestamp,
        friendlyDateMapper.map(input.timestamp),
        input.imageUrl,
        input.videoUrl
    )
}

class ViewSportEventListMapper @Inject constructor(itemMapper: ViewSportEventMapper) :
    SimpleListMapper<SportEvent, ViewSportEvent>(itemMapper)