package pl.paullettuce.daznrecruitmenttask.ui.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import pl.paullettuce.daznrecruitmenttask.domain.exceptions.NoNetworkException
import pl.paullettuce.daznrecruitmenttask.domain.exceptions.ServerException
import pl.paullettuce.daznrecruitmenttask.ui.filter.FilteringCriteria
import pl.paullettuce.daznrecruitmenttask.ui.filter.FilteringCriteria.TimeRange.None
import pl.paullettuce.daznrecruitmenttask.ui.model.ErrorType.NoNetwork
import pl.paullettuce.daznrecruitmenttask.ui.model.ErrorType.Other
import pl.paullettuce.daznrecruitmenttask.ui.model.ErrorType.ServerError
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState
import pl.paullettuce.daznrecruitmenttask.ui.sort.DateAscending

typealias SortingCriteria = Comparator<ViewSportEvent>

abstract class EventListFragmentViewModel : ViewModel() {

    val viewStateLiveData: LiveData<ViewState>
        get() = _viewStateLiveData

    protected open val filteringCriteria: FilteringCriteria = None
    protected open val sortingCriteria: SortingCriteria = DateAscending
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

    protected fun List<ViewSportEvent>.performFiltering() = filteredWith(filteringCriteria)

    protected fun List<ViewSportEvent>.performSorting() = sortedWith(sortingCriteria)

    protected fun Throwable.mapToErrorType() = when (this) {
        is NoNetworkException -> NoNetwork
        is ServerException -> ServerError
        else -> Other
    }

    protected fun Disposable.disposeAutomatically() {
        disposables.add(this)
    }

    private fun List<ViewSportEvent>.filteredWith(filteringCriteria: FilteringCriteria) =
        filter { filteringCriteria.isMatching(it) }
}
