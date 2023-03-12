package com.example.sheduleproject.presentation.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
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

        setupDrawer()

        binding.progressBar.visibility = View.VISIBLE

        onGettingTimeslotsList()
        remakeClassesListRequest()

        setupWeekCustomView()
        setupFullDateTextView(binding.weekCustomView.getSelectedDate())

        onSwipeToRefreshPull()

        return binding.root
    }

    private fun setupDrawer() {
        onMenuButtonClick()
        onMenuItemClick()
        onGettingUserTokenExistenceResult()
    }

    private fun onMenuButtonClick() {
        binding.menuButton.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.END)
        }
    }

    private fun onMenuItemClick() {
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.schedule_button -> {
                    navigateToScheduleTypeChoiceFragment()
                }
                R.id.logout_button -> {
                    viewModel.makeLogoutRequest { onErrorAppearance(it) }

                    viewModel.getLogoutResultLiveData().observe(this.viewLifecycleOwner) {
                        viewModel.removeTokenFromLocalStorage()
                        viewModel.setUserAuthorizationFlag(false)

                        navigateToLoginFragment()
                    }
                }
                else -> {
                    viewModel.setUserAuthorizationFlag(false)
                    navigateToLoginFragment()
                }
            }
            false
        }
    }

    private fun onGettingUserTokenExistenceResult() {
        viewModel.getTokenExistenceResultLiveData().observe(this.viewLifecycleOwner) {
            if (!it) {
                binding.navigationView.menu.findItem(R.id.logout_button).isVisible = false
                binding.navigationView.menu.findItem(R.id.login_button).isVisible = true
            } else {
                binding.navigationView.menu.findItem(R.id.logout_button).isVisible = true
                binding.navigationView.menu.findItem(R.id.login_button).isVisible = false
            }

        }
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
            onErrorAppearance = { onErrorAppearance(it) }
        ).observe(this.viewLifecycleOwner) {
            viewModel.saveClassesListToLocalStorage(it)
            getClassesListByDateFromLocalStorage(binding.weekCustomView.getSelectedDate())
            setupOnWeekArrowsClickFunctions()

            binding.progressBar.visibility = View.GONE
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun onErrorAppearance(errorCode: Int) {
        binding.progressBar.visibility = View.VISIBLE
        binding.swipeToRefresh.isRefreshing = false

        if (errorCode == NoConnectivityException.ERROR_CODE) {
            Toast.makeText(
                requireContext(),
                getString(R.string.internet_connection_error),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.internal_server_error),
                Toast.LENGTH_SHORT
            ).show()
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

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_scheduleFragment_to_loginFragment)
    }

    private fun navigateToScheduleTypeChoiceFragment() {
        findNavController().navigate(R.id.action_scheduleFragment_to_scheduleChoiceFragment)
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
