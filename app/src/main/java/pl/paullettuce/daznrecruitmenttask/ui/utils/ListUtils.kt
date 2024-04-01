package pl.paullettuce.daznrecruitmenttask.ui.utils

import pl.paullettuce.daznrecruitmenttask.ui.filter.FilteringCriteria
import pl.paullettuce.daznrecruitmenttask.ui.sort.SortingCriteria

inline fun <reified T> List<T>.performFiltering(filteringCriteria: FilteringCriteria<T>) =
    filteredWith(filteringCriteria)

inline fun <reified T> List<T>.performSorting(sortingCriteria: SortingCriteria<T>) =
    sortedWith(sortingCriteria.comparator())

inline fun <reified T> List<T>.filteredWith(filteringCriteria: FilteringCriteria<T>) =
    filter { filteringCriteria.isMatching(it) }