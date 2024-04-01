package pl.paullettuce.daznrecruitmenttask.ui.screens.events

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.schedulers.Schedulers.computation
import io.reactivex.rxjava3.schedulers.Schedulers.io
import pl.paullettuce.daznrecruitmenttask.domain.usecase.GetEventsUseCase
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEventMapper
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Data
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Error
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Loading
import pl.paullettuce.daznrecruitmenttask.ui.screens.base.EventListFragmentViewModel
import javax.inject.Inject

@HiltViewModel
class EventsFragmentViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val viewSportEventMapper: ViewSportEventMapper
) : EventListFragmentViewModel() {

    override fun loadData() {
        getEventsUseCase()
            .doOnSubscribe { _viewStateLiveData.postValue(Loading) }
            .subscribeOn(io())
            .observeOn(computation()) // map on computation() thread
            .map {
                viewSportEventMapper
                    .toViewSportEvents(it)
                    .performFiltering()
                    .performSorting()
            }
            .observeOn(mainThread()).subscribe({
                _viewStateLiveData.postValue(Data(it))
            }, {
                _viewStateLiveData.postValue(Error(it.mapToErrorType()))
            })
            .disposeAutomatically()
    }
}

