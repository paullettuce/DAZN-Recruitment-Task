package pl.paullettuce.daznrecruitmenttask.ui.model.mapper

import androidx.media3.common.PlaybackException
import pl.paullettuce.daznrecruitmenttask.core.model.mapper.Mapper
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError
import javax.inject.Inject

class PlayerErrorMapper @Inject constructor() : Mapper<PlaybackException, ViewError> {

    override fun map(input: PlaybackException) =
        when (input.errorCode) {
            PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> ViewError.NoNetwork
            else -> ViewError.UnknownError
        }
}