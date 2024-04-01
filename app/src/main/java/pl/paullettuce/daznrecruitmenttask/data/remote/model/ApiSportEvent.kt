package pl.paullettuce.daznrecruitmenttask.data.remote.model

data class ApiSportEvent(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String?
)