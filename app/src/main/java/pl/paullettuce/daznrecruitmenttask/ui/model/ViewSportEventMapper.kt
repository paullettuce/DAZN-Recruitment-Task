package pl.paullettuce.daznrecruitmenttask.ui.model

import android.content.Context
import android.text.format.DateUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import javax.inject.Inject

class ViewSportEventMapper @Inject constructor(@ApplicationContext private val context: Context) {

    fun toViewSportEvents(sportEvents: List<SportEvent>) = sportEvents.map { toViewSportEvent(it) }

    private fun toViewSportEvent(sportEvent: SportEvent) = ViewSportEvent(
        sportEvent.id,
        sportEvent.title,
        sportEvent.subtitle,
        sportEvent.timestamp,
        sportEvent.timestamp.toFriendlyDate(),
        sportEvent.imageUrl,
        sportEvent.videoUrl
    )

    private fun Long.toFriendlyDate() = DateUtils.getRelativeDateTimeString(
        context,
        this,
        DateUtils.DAY_IN_MILLIS, // only hours, no minutes
        DateUtils.WEEK_IN_MILLIS, // up to 7 days, in or ago
        0
    ).toString()
}