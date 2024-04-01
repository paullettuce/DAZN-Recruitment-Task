package pl.paullettuce.daznrecruitmenttask.ui.model.mapper

import android.content.Context
import android.text.format.DateUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.daznrecruitmenttask.core.model.mapper.Mapper
import javax.inject.Inject

class FriendlyDateMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : Mapper<Long, String> {
    override fun map(input: Long) = DateUtils.getRelativeDateTimeString(
        context,
        input,
        DateUtils.DAY_IN_MILLIS, // only hours, no minutes
        DateUtils.WEEK_IN_MILLIS, // up to 7 days, in or ago
        0
    ).toString()
}