package pl.paullettuce.daznrecruitmenttask.ui.screens.schedule

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.schedulers.Schedulers.computation
import io.reactivex.rxjava3.schedulers.Schedulers.io
import pl.paullettuce.daznrecruitmenttask.domain.usecase.GetScheduledUseCase
import pl.paullettuce.daznrecruitmenttask.ui.filter.EventFilteringCriteria
import pl.paullettuce.daznrecruitmenttask.ui.filter.EventFilteringCriteria.TimeRange.Tomorrow
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Loading
import pl.paullettuce.daznrecruitmenttask.ui.model.mapper.ViewErrorMapper
import pl.paullettuce.daznrecruitmenttask.ui.model.mapper.ViewSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.ui.screens.base.EventListFragmentViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ScheduleFragmentViewModel @Inject constructor(
    private val getScheduledUseCase: GetScheduledUseCase,
    viewSportEventListMapper: ViewSportEventListMapper,
    viewErrorMapper: ViewErrorMapper
) : EventListFragmentViewModel(viewSportEventListMapper, viewErrorMapper) {

    override val filteringCriteria: EventFilteringCriteria = Tomorrow

    override fun loadData() {
        getScheduledUseCase()
            .doOnSubscribe { updateViewState(Loading) }
            .subscribeOn(io())
            .observeOn(computation()) // map on computation() thread
            .prepareDataForView()
            .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
            .observeOn(mainThread())
            .subscribe({ data ->
                updateViewState(data)
            }, { error ->
                updateViewState(error)
            })
            .disposeAutomatically()
    }
}

