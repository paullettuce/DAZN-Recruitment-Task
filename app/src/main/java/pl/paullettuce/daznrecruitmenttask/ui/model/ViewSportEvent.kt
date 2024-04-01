package pl.paullettuce.daznrecruitmenttask.ui.model

data class ViewSportEvent(
    var id: String,
    val title: String,
    val subtitle: String,
    val timestamp: Long,
    val friendlyDate: String,
    val imageUrl: String,
    val videoUrl: String
)