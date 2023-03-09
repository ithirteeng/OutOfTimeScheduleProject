package com.example.sheduleproject.presentation.schedulechoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentScheduleTypeChoiceBinding
import com.example.sheduleproject.presentation.schedulechoice.model.ScheduleType

class ScheduleTypeChoiceFragment : Fragment() {

    private lateinit var binding: FragmentScheduleTypeChoiceBinding

    companion object {
        const val SCHEDULE_TYPE_KEY = "schedule type key"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_schedule_type_choice, container, false)
        binding = FragmentScheduleTypeChoiceBinding.bind(mainView)
        setupOnButtonClickFunction()

        return binding.root
    }

    private fun setupOnButtonClickFunction() {
        onEducatorButtonClick()
        onGroupButtonClick()
        onLectureHallButtonClick()
    }

    private fun onEducatorButtonClick() {
        binding.educatorButton.setOnClickListener {
            navigateToSecondFragment(setupBundle(ScheduleType.EDUCATOR))
        }
    }

    private fun onGroupButtonClick() {
        binding.groupButton.setOnClickListener {
            navigateToSecondFragment(setupBundle(ScheduleType.GROUP))
        }
    }

    private fun onLectureHallButtonClick() {
        binding.lectureHallButton.setOnClickListener {
            navigateToSecondFragment(setupBundle(ScheduleType.LECTURE_HALL))
        }
    }

    private fun setupBundle(scheduleType: ScheduleType): Bundle {
        val bundle = Bundle()
        bundle.putString(SCHEDULE_TYPE_KEY, scheduleType.toString())
        return bundle
    }

    private fun navigateToSecondFragment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_scheduleChoiceFragment_to_scheduleChoiceFragment2,
            bundle
        )
    }
}