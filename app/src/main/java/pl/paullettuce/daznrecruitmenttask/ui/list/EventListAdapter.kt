package pl.paullettuce.daznrecruitmenttask.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import pl.paullettuce.daznrecruitmenttask.databinding.ItemSportEventBinding
import pl.paullettuce.daznrecruitmenttask.ui.list.EventListAdapter.SportEventViewHolder
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent

class EventListAdapter(
    val onItemClickListener: (ViewSportEvent) -> Unit = { }
) : ListAdapter<ViewSportEvent, SportEventViewHolder>(ViewSportEventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportEventViewHolder {
        val binding =
            ItemSportEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportEventViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onBindViewHolder(
        holder: SportEventViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when (val latestPayload = payloads.lastOrNull()) {
            is ViewSportEventChangePayload.FriendlyDate -> holder.bindFriendlyDate(latestPayload.newFriendlyDate)
            else -> onBindViewHolder(holder, position)
        }
    }

    fun onItemsInserted(listener: (insertIndex: Int, insertedCount: Int) -> Unit) {
        registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                listener(positionStart, itemCount)
            }
        })
    }

    inner class SportEventViewHolder(private val itemBinding: ItemSportEventBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewSportEvent: ViewSportEvent) = with(itemBinding) {
            item = viewSportEvent
            root.setOnClickListener { onItemClickListener(viewSportEvent) }
        }

        fun bindFriendlyDate(newFriendlyDate: String) {
            itemBinding.date.text = newFriendlyDate
        }
    }
}
