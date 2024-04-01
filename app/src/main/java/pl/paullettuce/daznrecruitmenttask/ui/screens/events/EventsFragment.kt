package pl.paullettuce.daznrecruitmenttask.ui.screens.events

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import pl.paullettuce.daznrecruitmenttask.ui.player.PlayerActivity
import pl.paullettuce.daznrecruitmenttask.ui.screens.base.EventListFragment

@AndroidEntryPoint
class EventsFragment : EventListFragment<EventsFragmentViewModel>() {

    override val viewModel: EventsFragmentViewModel by viewModels()

    override fun onItemClick(event: ViewSportEvent) {
        PlayerActivity.launch(requireContext(), event.videoUrl)
    }
}

