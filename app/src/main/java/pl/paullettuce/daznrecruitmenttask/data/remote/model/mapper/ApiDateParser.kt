package pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper

import pl.paullettuce.daznrecruitmenttask.domain.model.mapper.Mapper
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

private const val API_DATE_FORMAT = "yyyy-MM-d'T'H:mm:ss.SSS'Z'"

class ApiDateParser @Inject constructor() : Mapper<String, Long> {
    override fun map(input: String) =
        SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
            .parse(input)
            ?.time ?: -1L // Return -1 if parsing fails
}