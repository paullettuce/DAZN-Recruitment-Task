package pl.paullettuce.daznrecruitmenttask.ui.screens

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import pl.paullettuce.daznrecruitmenttask.R
import pl.paullettuce.daznrecruitmenttask.databinding.ActivityNavigationBinding
import pl.paullettuce.daznrecruitmenttask.databinding.ItemTabNavigationBinding
import pl.paullettuce.daznrecruitmenttask.ui.screens.events.EventsFragment
import pl.paullettuce.daznrecruitmenttask.ui.screens.schedule.ScheduleFragment

@AndroidEntryPoint
class NavigationActivity : FragmentActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private val navigationTabs = arrayOf(NavigationTab.Events, NavigationTab.Schedule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        setupNavigation()
    }

    private fun setupNavigation() = with(binding) {
        viewPager.adapter = NavigationFragmentAdapter()
        TabLayoutMediator(bottomNavigation, viewPager) { tabView, position ->
            tabView.setup(navigationTabs[position])
        }.attach()
    }

    private fun TabLayout.Tab.setup(
        navigationTab: NavigationTab
    ) {
        val itemBinding: ItemTabNavigationBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_tab_navigation, null, false)
        itemBinding.tabIcon.setImageResource(navigationTab.iconRes)
        itemBinding.tabText.setText(navigationTab.textRes)
        customView = itemBinding.root
    }

    inner class NavigationFragmentAdapter : FragmentStateAdapter(this) {

        override fun getItemCount() = navigationTabs.size

        override fun createFragment(position: Int): Fragment =
            navigationTabs[position].fragmentClass.getConstructor().newInstance()
    }
}

sealed class NavigationTab(
    @StringRes val textRes: Int,
    @DrawableRes val iconRes: Int,
    val fragmentClass: Class<out Fragment>
) {
    data object Events :
        NavigationTab(R.string.events, R.drawable.ic_events, EventsFragment::class.java)

    data object Schedule :
        NavigationTab(R.string.schedule, R.drawable.ic_schedule, ScheduleFragment::class.java)
}