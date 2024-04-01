package pl.paullettuce.daznrecruitmenttask.ui.screens.events

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.schedulers.Schedulers.computation
import io.reactivex.rxjava3.schedulers.Schedulers.io
import pl.paullettuce.daznrecruitmenttask.domain.usecase.GetEventsUseCase
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Loading
import pl.paullettuce.daznrecruitmenttask.ui.model.mapper.ViewErrorMapper
import pl.paullettuce.daznrecruitmenttask.ui.model.mapper.ViewSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.ui.screens.base.EventListFragmentViewModel
import javax.inject.Inject

@HiltViewModel
class EventsFragmentViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    viewSportEventListMapper: ViewSportEventListMapper,
    viewErrorMapper: ViewErrorMapper
) : EventListFragmentViewModel(viewSportEventListMapper, viewErrorMapper) {

    override fun loadData() {
        getEventsUseCase()
            .doOnSubscribe { updateViewState(Loading) }
            .subscribeOn(io())
            .observeOn(computation()) // map on computation() thread
            .prepareDataForView()
            .observeOn(mainThread())
            .subscribe({ data ->
                updateViewState(data)
            }, { error ->
                updateViewState(error)
            })
            .disposeAutomatically()
    }

}

