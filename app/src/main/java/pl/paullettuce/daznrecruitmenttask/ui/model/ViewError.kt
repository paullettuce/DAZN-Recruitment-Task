package pl.paullettuce.daznrecruitmenttask.ui.model

import androidx.annotation.StringRes
import pl.paullettuce.daznrecruitmenttask.R

sealed class ViewError(
    @StringRes val headerRes: Int,
    @StringRes val messageRes: Int
) {
    data object NoNetwork :
        ViewError(R.string.error_header_no_internet, R.string.error_message_no_internet)

    data object ServerError :
        ViewError(R.string.error_header_server, R.string.error_message_server)

    data object UnknownError :
        ViewError(R.string.error_message_generic, R.string.error_message_generic)

    data object FilteringError :
        ViewError(R.string.error_header_filtering, R.string.error_message_filtering)
}