package pl.paullettuce.daznrecruitmenttask.ui.model


sealed class ViewState {
    data class Data(val events: List<ViewSportEvent>) : ViewState()
    data object Loading : ViewState()
    data class Error(val viewError: ViewError) : ViewState()
}