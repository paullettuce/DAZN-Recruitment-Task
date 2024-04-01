package pl.paullettuce.daznrecruitmenttask.core.model.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

abstract class SimpleListMapper<I, O>(
    private val itemMapper: Mapper<I, O>
) : ListMapper<I, O> {

    override fun map(input: List<I>) = input.map { itemMapper.map(it) }
}