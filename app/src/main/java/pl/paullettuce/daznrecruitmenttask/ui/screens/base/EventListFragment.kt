package pl.paullettuce.daznrecruitmenttask.ui.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.paullettuce.daznrecruitmenttask.databinding.FragmentEventListBinding
import pl.paullettuce.daznrecruitmenttask.ui.list.EventListAdapter
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewError
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewSportEvent
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Data
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Error
import pl.paullettuce.daznrecruitmenttask.ui.model.ViewState.Loading
import pl.paullettuce.daznrecruitmenttask.ui.utils.addDefaultDivider
import pl.paullettuce.daznrecruitmenttask.ui.utils.addOnScrollUpListener
import pl.paullettuce.daznrecruitmenttask.ui.utils.animateHide
import pl.paullettuce.daznrecruitmenttask.ui.utils.animateShow
import pl.paullettuce.daznrecruitmenttask.ui.utils.hide
import pl.paullettuce.daznrecruitmenttask.ui.utils.scrollPosition
import pl.paullettuce.daznrecruitmenttask.ui.utils.show
import pl.paullettuce.daznrecruitmenttask.ui.utils.toast


abstract class EventListFragment<T : EventListFragmentViewModel> : Fragment() {

    protected abstract val viewModel: T

    private lateinit var binding: FragmentEventListBinding

    private val eventsAdapter = EventListAdapter { onItemClick(it) }
    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(FragmentEventListBinding.inflate(inflater, container, false)) {
        binding = this
        lifecycleOwner = this@EventListFragment
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareList()
        observeState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewResumed()
    }

    override fun onPause() {
        viewModel.onViewPaused()
        super.onPause()
    }

    protected abstract fun onItemClick(event: ViewSportEvent)

    private fun prepareList() {
        setupRecyclerView()
        setupSwipeRefresh()
        setupNewItemsPrompt()
    }

    private fun observeState() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Data -> updateList(it.events)
                is Error -> dispatchAndDisplayError(it.viewError)
                is Loading -> showLoading().also { hideErrorScreen() }
            }
        }
    }

    private fun updateList(events: List<ViewSportEvent>) {
        hideErrorScreen()
        hideLoading()
        eventsAdapter.submitList(events)
    }

    private fun dispatchAndDisplayError(viewError: ViewError) {
        hideLoading()
        if (eventsAdapter.itemCount > 0 || binding.errorScreen.isVisible) {
            showToast(viewError)
        } else {
            showErrorScreen(viewError)
        }
    }

    private fun showErrorScreen(error: ViewError) = with(binding) {
        errorScreen.show()
        errorHeader.setText(error.headerRes)
        errorMessage.setText(error.messageRes)
        retryButton.setOnClickListener {
            viewModel.refreshData()
        }
    }

    private fun showToast(viewError: ViewError) {
        toast?.cancel() // avoid stacking toasts
        toast = requireContext().toast(viewError.headerRes).also { it.show() }
    }

    private fun setupRecyclerView() = with(binding.eventsRecyclerView) {
        adapter = eventsAdapter
        layoutManager = LinearLayoutManager(requireContext())
        addDefaultDivider()
    }

    private fun setupSwipeRefresh() = with(binding.swipeRefresh) {
        setOnRefreshListener {
            // hide refreshing progress circle, there is a custom ProgressBar in layout
            isRefreshing = false
            viewModel.refreshData()
        }
    }

    private fun setupNewItemsPrompt() = with(binding.eventsRecyclerView) {
        eventsAdapter.onItemsInserted { insertIndex, insertedItemsCount ->
            if (areAddedItemsAboveScrollPosition(
                    scrollPosition(),
                    insertIndex,
                    insertedItemsCount
                )
            ) {
                showNewItemsPrompt()
                binding.newItemsButton.setOnClickListener {
                    smoothScrollToPosition(insertIndex)
                    hideNewItemsPrompt()
                }
            }
        }
        addOnScrollUpListener { hideNewItemsPrompt() }
    }

    private fun areAddedItemsAboveScrollPosition(
        scrollPosition: Int,
        positionStart: Int,
        insertedItemsCount: Int
    ) = scrollPosition >= positionStart + insertedItemsCount
            || scrollPosition == 0 && insertedItemsCount > 0


    private fun showLoading() = binding.loadingIndicator.show()

    private fun hideLoading() = binding.loadingIndicator.hide()

    private fun hideErrorScreen() = binding.errorScreen.hide()

    private fun showNewItemsPrompt() = binding.newItemsButton.animateShow()

    private fun hideNewItemsPrompt() = binding.newItemsButton.animateHide()
}