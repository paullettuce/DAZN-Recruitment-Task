package pl.paullettuce.daznrecruitmenttask.ui.filter

interface FilteringCriteria<T> {
    fun isMatching(item: T): Boolean
}