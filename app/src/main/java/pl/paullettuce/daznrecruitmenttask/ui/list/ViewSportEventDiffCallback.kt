package pl.paullettuce.daznrecruitmenttask.ui.list

import androidx.recyclerview.widget.DiffUtil
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent


class ViewSportEventDiffCallback : DiffUtil.ItemCallback<ViewSportEvent>() {
    override fun areItemsTheSame(oldItem: ViewSportEvent, newItem: ViewSportEvent): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Checks whether the contents of the given [oldItem] and [newItem] are the same, excluding the `timestamp` property.
     * This method ensures smooth user experience by ignoring the `timestamp`, which may change frequently and cause
     * unnecessary view refreshing.
     *
     * @param oldItem The old [ViewSportEvent] item.
     * @param newItem The new [ViewSportEvent] item.
     * @return `true` if the contents of the items, excluding the `timestamp`, are the same; `false` otherwise.
     */
    override fun areContentsTheSame(oldItem: ViewSportEvent, newItem: ViewSportEvent): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.subtitle == newItem.subtitle &&
                oldItem.friendlyDate == newItem.friendlyDate &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.videoUrl == newItem.videoUrl
    }

    /**
     * Computes a payload object representing the change between the given [oldItem] and [newItem].
     * For now, this method only considers changes in the `friendlyDate` property.
     * This design allows for easy extension in the future to accommodate additional properties.
     *
     * @param oldItem The old [ViewSportEvent] item.
     * @param newItem The new [ViewSportEvent] item.
     * @return A payload object representing the change, or `null` if no change is detected.
     */
    override fun getChangePayload(oldItem: ViewSportEvent, newItem: ViewSportEvent) =
        when {
            oldItem.friendlyDate != newItem.friendlyDate ->
                ViewSportEventChangePayload.FriendlyDate(newItem.friendlyDate)

            else -> super.getChangePayload(oldItem, newItem)
        }
}

sealed class ViewSportEventChangePayload {
    data class FriendlyDate(val newFriendlyDate: String) : ViewSportEventChangePayload()
}
