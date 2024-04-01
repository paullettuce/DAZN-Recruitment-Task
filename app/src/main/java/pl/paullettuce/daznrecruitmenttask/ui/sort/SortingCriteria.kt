package pl.paullettuce.daznrecruitmenttask.ui.sort

interface SortingCriteria<T> {
    fun comparator(): Comparator<T>
}