package pl.paullettuce.daznrecruitmenttask.ui.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import pl.paullettuce.daznrecruitmenttask.ui.filter.EventFilteringCriteria
import pl.paullettuce.daznrecruitmenttask.ui.filter.EventFilteringCriteria.TimeRange.None
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError.FilteringError
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Data
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Error
import pl.paullettuce.daznrecruitmenttask.ui.model.mapper.ViewErrorMapper
import pl.paullettuce.daznrecruitmenttask.ui.model.mapper.ViewSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.ui.sort.EventSortingCriteria
import pl.paullettuce.daznrecruitmenttask.ui.sort.EventSortingCriteria.DateAscending
import pl.paullettuce.daznrecruitmenttask.ui.utils.performFiltering
import pl.paullettuce.daznrecruitmenttask.ui.utils.performSorting

abstract class EventListFragmentViewModel(
    private val viewSportEventListMapper: ViewSportEventListMapper,
    private val viewErrorMapper: ViewErrorMapper
) : ViewModel() {

    val viewStateLiveData: LiveData<ViewState>
        get() = _viewStateLiveData

    protected open val filteringCriteria: EventFilteringCriteria = None
    protected open val sortingCriteria: EventSortingCriteria = DateAscending
    private val _viewStateLiveData = MutableLiveData<ViewState>()
    private val disposables = CompositeDisposable()

    protected abstract fun loadData()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun onViewPaused() {
        disposables.clear()
    }

    fun onViewResumed() {
        loadData()
    }

    fun refreshData() {
        disposables.clear()
        loadData()
    }

    protected fun Single<List<SportEvent>>.prepareDataForView() = map {
        viewSportEventListMapper.map(it)
            .performFiltering(filteringCriteria)
            .performSorting(sortingCriteria)
    }

    protected fun updateViewState(viewState: ViewState) {
        _viewStateLiveData.postValue(viewState)
    }

    protected fun updateViewState(data: List<ViewSportEvent>) =
        updateViewState(if (data.isEmpty()) Error(FilteringError) else Data(data))

    protected fun updateViewState(exception: Throwable) =
        updateViewState(Error(viewErrorMapper.map(exception)))

    protected fun Disposable.disposeAutomatically() {
        disposables.add(this)
    }
}
