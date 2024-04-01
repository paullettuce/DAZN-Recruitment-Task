package pl.paullettuce.daznrecruitmenttask.ui.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import pl.paullettuce.daznrecruitmenttask.domain.exceptions.NoNetworkException
import pl.paullettuce.daznrecruitmenttask.domain.exceptions.ServerException
import pl.paullettuce.daznrecruitmenttask.domain.model.SportEvent
import pl.paullettuce.daznrecruitmenttask.ui.filter.EventFilteringCriteria
import pl.paullettuce.daznrecruitmenttask.ui.filter.EventFilteringCriteria.TimeRange.None
import pl.paullettuce.daznrecruitmenttask.ui.model.ErrorType.NoNetwork
import pl.paullettuce.daznrecruitmenttask.ui.model.ErrorType.Other
import pl.paullettuce.daznrecruitmenttask.ui.model.ErrorType.ServerError
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEventListMapper
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState
import pl.paullettuce.daznrecruitmenttask.ui.sort.EventSortingCriteria
import pl.paullettuce.daznrecruitmenttask.ui.sort.EventSortingCriteria.DateAscending
import pl.paullettuce.daznrecruitmenttask.ui.utils.performFiltering
import pl.paullettuce.daznrecruitmenttask.ui.utils.performSorting

abstract class EventListFragmentViewModel(
    private val viewSportEventListMapper: ViewSportEventListMapper
) : ViewModel() {

    val viewStateLiveData: LiveData<ViewState>
        get() = _viewStateLiveData

    protected open val filteringCriteria: EventFilteringCriteria = None
    protected open val sortingCriteria: EventSortingCriteria = DateAscending
    protected val _viewStateLiveData = MutableLiveData<ViewState>()
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

    protected fun Throwable.mapToErrorType() = when (this) {
        is NoNetworkException -> NoNetwork
        is ServerException -> ServerError
        else -> Other
    }

    protected fun Disposable.disposeAutomatically() {
        disposables.add(this)
    }

}
