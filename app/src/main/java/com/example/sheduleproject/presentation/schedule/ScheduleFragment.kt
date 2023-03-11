package com.example.sheduleproject.presentation.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentScheduleBinding
import com.example.sheduleproject.domain.schedule.utils.DateTimeHelper
import com.example.sheduleproject.presentation.common.model.BundleHelper
import com.example.sheduleproject.presentation.schedule.adapter.ClassesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment() {

    private val viewModel: ScheduleFragmentViewModel by viewModel()

    private lateinit var binding: FragmentScheduleBinding

    private lateinit var dateTimeHelper: DateTimeHelper

    private val classesAdapter by lazy {
        ClassesAdapter {

        }
    }

    private val clusterNumber by lazy {
        getBundleData(BundleHelper.CLUSTER_KEY)
    }

    private val educatorId by lazy {
        getBundleData(BundleHelper.EDUCATOR_KEY)
    }

    private val lectureHallId by lazy {
        getBundleData(BundleHelper.LECTURE_HALL_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_schedule, container, false)

        binding = FragmentScheduleBinding.bind(mainView)
        dateTimeHelper = DateTimeHelper(requireContext())

        binding.weekCustomView.refreshData()

        binding.progressBar.visibility = View.VISIBLE

        onGettingTimeslotsList()
        remakeClassesListRequest()

        setupWeekCustomView()
        setupFullDateTextView(binding.weekCustomView.getSelectedDate())

        onSwipeToRefreshPull()

        return binding.root
    }

    private fun setupOnWeekArrowsClickFunctions() {
        binding.weekCustomView.onLeftArrowButtonClick {
            setupFullDateTextView(binding.weekCustomView.getSelectedDate())
            remakeClassesListRequest()

            binding.emptyListTextView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

        }

        binding.weekCustomView.onRightArrowButtonClick {
            setupFullDateTextView(binding.weekCustomView.getSelectedDate())
            remakeClassesListRequest()

            binding.emptyListTextView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun onSwipeToRefreshPull() {
        binding.swipeToRefresh.setOnRefreshListener {
            onGettingTimeslotsList()
            remakeClassesListRequest()
        }
    }

    private fun onErrorAppearance() {
        binding.progressBar.visibility = View.VISIBLE
        binding.swipeToRefresh.isRefreshing = false
    }

    private fun onGettingClassesList(
        startDate: String,
        endDate: String,
        clusterNumber: String?,
        educatorId: String?,
        lectureHallId: String?
    ) {
        viewModel.getClassesListLiveData(
            startDate = startDate,
            endDate = endDate,
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            lectureHallId = lectureHallId,
            onErrorAppeared = { onErrorAppearance() }
        ).observe(this.viewLifecycleOwner) {
            viewModel.saveClassesListToLocalStorage(it)
            getClassesListByDateFromLocalStorage(binding.weekCustomView.getSelectedDate())
            setupOnWeekArrowsClickFunctions()

            binding.progressBar.visibility = View.GONE
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun getClassesListByDateFromLocalStorage(date: String) {
        val list = viewModel.getClassesListByDateFromStorage(date)
        classesAdapter.setClassesList(ArrayList(list))
        if (list.isEmpty()) {
            binding.emptyListTextView.visibility = View.VISIBLE
        } else {
            binding.emptyListTextView.visibility = View.GONE
        }

        binding.recyclerView.adapter = classesAdapter
    }

    private fun setupWeekCustomView() {
        binding.weekCustomView.setupWeekCustomView(onDateClick = {
            val date = binding.weekCustomView.getSelectedDate()
            setupFullDateTextView(date)
            getClassesListByDateFromLocalStorage(date)
        })
    }

    private fun remakeClassesListRequest() {
        binding.emptyListTextView.visibility = View.GONE
        onGettingClassesList(
            startDate = binding.weekCustomView.getWeekDatesList()[0],
            endDate = binding.weekCustomView.getWeekDatesList()[6],
            clusterNumber = clusterNumber,
            educatorId = educatorId,
            lectureHallId = lectureHallId
        )
    }

    private fun getBundleData(key: String): String? {
        return arguments?.getString(key)
    }

    private fun setupFullDateTextView(date: String) {
        binding.fullDateTextView.text = getFullDate(date)
    }

    private fun onGettingTimeslotsList() {
        viewModel.getTimeSlotListLiveData().observe(this.viewLifecycleOwner) {
            classesAdapter.setTimeSlotsList(it)
        }
    }

    private fun getFullDate(date: String): String {
        dateTimeHelper.setDate(date)

        val dayName = dateTimeHelper.getCurrentDayOfWeek()
        val dayNumber = dateTimeHelper.getCurrentDayOfMonth()
        val monthName = dateTimeHelper.getCurrentMonth()
        val yearName = dateTimeHelper.getCurrentYear()

        return "$dayName $dayNumber $monthName $yearName"
    }
}
