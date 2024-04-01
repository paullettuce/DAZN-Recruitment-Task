package pl.paullettuce.daznrecruitmenttask.ui.screens.schedule

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import pl.paullettuce.daznrecruitmenttask.ui.screens.base.EventListFragment

@AndroidEntryPoint
class ScheduleFragment : EventListFragment<ScheduleFragmentViewModel>() {

    override val viewModel: ScheduleFragmentViewModel by viewModels()

    override fun onItemClick(event: ViewSportEvent) {}
}