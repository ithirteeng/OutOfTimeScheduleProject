package com.example.sheduleproject.presentation.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentScheduleBinding
import com.example.sheduleproject.domain.schedule.utils.DateTimeHelper
import com.example.sheduleproject.presentation.schedule.adapter.ClassesAdapter
import com.example.sheduleproject.presentation.schedule.model.listOfEntities
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment() {

    private val viewModel: ScheduleFragmentViewModel by viewModel()

    private lateinit var binding: FragmentScheduleBinding

    private lateinit var dateTimeHelper: DateTimeHelper

    private val classesAdapter by lazy {
        ClassesAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_schedule, container, false)
        binding = FragmentScheduleBinding.bind(mainView)
        dateTimeHelper = DateTimeHelper(requireContext())

        setupViews()
        binding.recyclerView.adapter = classesAdapter

        viewModel.getTimeSlotListLiveData().observe(this.viewLifecycleOwner) {
            Log.d("TIMESLOT", it.toString())
        }


        return binding.root
    }

    private fun setupViews() {
        classesAdapter.setList(listOfEntities)
        setupTextViews()
    }

    private fun setupTextViews() {
        binding.fullDateTextView.text = getFullCurrentDate()
    }


    private fun getFullCurrentDate(): String {
        val dayName = dateTimeHelper.getCurrentDayOfWeek()
        val dayNumber = dateTimeHelper.getCurrentDayOfMonth()
        val monthName = dateTimeHelper.getCurrentMonth()
        val yearName = dateTimeHelper.getCurrentYear()

        return "$dayName $dayNumber $monthName $yearName"
    }
}
