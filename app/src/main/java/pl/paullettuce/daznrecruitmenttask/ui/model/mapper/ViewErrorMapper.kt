package pl.paullettuce.daznrecruitmenttask.ui.model.mapper

import pl.paullettuce.daznrecruitmenttask.core.model.mapper.Mapper
import pl.paullettuce.daznrecruitmenttask.domain.model.exceptions.NoNetworkException
import pl.paullettuce.daznrecruitmenttask.domain.model.exceptions.ServerException
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError.NoNetwork
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError.ServerError
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError.UnknownError
import javax.inject.Inject

class ViewErrorMapper @Inject constructor() : Mapper<Throwable, ViewError> {
    override fun map(input: Throwable) = when (input) {
        is NoNetworkException -> NoNetwork
        is ServerException -> ServerError
        else -> UnknownError
    }
}