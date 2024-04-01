package pl.paullettuce.daznrecruitmenttask.domain.usecase

import pl.paullettuce.daznrecruitmenttask.data.repository.EventsRepository
import javax.inject.Inject

class GetScheduledUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {
    operator fun invoke() = eventsRepository.getScheduled()
}
