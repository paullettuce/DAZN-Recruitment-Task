package pl.paullettuce.daznrecruitmenttask.ui.screens.schedule

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.daznrecruitmenttask.domain.usecase.GetScheduledUseCase
import pl.paullettuce.daznrecruitmenttask.ui.filter.FilteringCriteria
import pl.paullettuce.daznrecruitmenttask.ui.filter.FilteringCriteria.TimeRange.Tomorrow
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEventMapper
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Data
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Error
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Loading
import pl.paullettuce.daznrecruitmenttask.ui.screens.base.EventListFragmentViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ScheduleFragmentViewModel @Inject constructor(
    private val getScheduledUseCase: GetScheduledUseCase,
    private val viewSportEventMapper: ViewSportEventMapper
) : EventListFragmentViewModel() {

    override val filteringCriteria: FilteringCriteria = Tomorrow

    override fun loadData() {
        getScheduledUseCase()
            .doOnSubscribe { _viewStateLiveData.postValue(Loading) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation()) // map on computation() thread
            .map {
                viewSportEventMapper.toViewSportEvents(it)
                    .performFiltering()
                    .performSorting()
            }
            .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _viewStateLiveData.postValue(Data(it))
            }, {
                _viewStateLiveData.postValue(Error(it.mapToErrorType()))
            })
            .disposeAutomatically()
    }
}

