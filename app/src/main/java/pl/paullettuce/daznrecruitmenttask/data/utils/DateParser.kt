package pl.paullettuce.daznrecruitmenttask.data.utils

import java.text.SimpleDateFormat
import java.util.Locale

const val API_DATE_FORMAT = "yyyy-MM-d'T'H:mm:ss.SSS'Z'"

fun String.toTimestamp() =
    SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
        .parse(this)
        ?.time ?: -1L // Return -1 if parsing fails
