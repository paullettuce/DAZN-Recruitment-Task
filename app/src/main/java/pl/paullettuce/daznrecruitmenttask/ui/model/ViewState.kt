package pl.paullettuce.daznrecruitmenttask.ui.model

import androidx.annotation.StringRes
import pl.paullettuce.daznrecruitmenttask.R


sealed class ViewState {
    data class Data(val events: List<ViewSportEvent>) : ViewState()
    data object Loading : ViewState()
    data class Error(val errorType: ErrorType) : ViewState()
}

sealed class ErrorType(
    @StringRes val headerRes: Int,
    @StringRes val messageRes: Int
) {
    data object NoNetwork :
        ErrorType(R.string.error_header_no_internet, R.string.error_message_no_internet)

    data object ServerError :
        ErrorType(R.string.error_header_server, R.string.error_message_server)

    data object Other :
        ErrorType(R.string.error_message_generic, R.string.error_message_generic)

    data object FilteringError :
        ErrorType(R.string.error_header_filtering, R.string.error_message_filtering)
}