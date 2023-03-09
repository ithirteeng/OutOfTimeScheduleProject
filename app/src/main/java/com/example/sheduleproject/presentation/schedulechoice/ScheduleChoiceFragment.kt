package com.example.sheduleproject.presentation.schedulechoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentScheduleChoiceBinding

class ScheduleChoiceFragment : Fragment() {

    private lateinit var binding: FragmentScheduleChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_schedule_choice, container, false)
        binding = FragmentScheduleChoiceBinding.bind(mainView)

        val choiceType = arguments?.getString(ScheduleTypeChoiceFragment.SCHEDULE_TYPE_KEY)

        return binding.root
    }
}