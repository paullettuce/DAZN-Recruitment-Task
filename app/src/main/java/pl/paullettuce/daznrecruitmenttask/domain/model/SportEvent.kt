package pl.paullettuce.daznrecruitmenttask.domain.model

data class SportEvent(
        val id: String,
        val title: String,
        val subtitle: String,
        val timestamp: Long,
        val imageUrl: String,
        val videoUrl: String
)