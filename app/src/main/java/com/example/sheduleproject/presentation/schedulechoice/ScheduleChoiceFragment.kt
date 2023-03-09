package com.example.sheduleproject.presentation.schedulechoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentScheduleChoiceBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleChoiceFragment : Fragment() {

    private lateinit var binding: FragmentScheduleChoiceBinding

    private val viewModel: ScheduleChoiceFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_schedule_choice, container, false)
        binding = FragmentScheduleChoiceBinding.bind(mainView)

        val choiceType = arguments?.getString(ScheduleTypeChoiceFragment.SCHEDULE_TYPE_KEY)
        binding.choiceTypeTextView.text = choiceType
        onBackButtonClick()


        return binding.root
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            navigateToScheduleTypeChoiceFragment()
        }
    }

    private fun navigateToScheduleTypeChoiceFragment() {
        findNavController().navigate(R.id.action_scheduleChoiceFragment2_to_scheduleChoiceFragment)
    }


}