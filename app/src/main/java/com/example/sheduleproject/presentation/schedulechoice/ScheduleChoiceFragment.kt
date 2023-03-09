package com.example.sheduleproject.presentation.schedulechoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.databinding.FragmentScheduleChoiceBinding
import com.example.sheduleproject.presentation.schedulechoice.adapter.ScheduleChoicesAdapter
import com.example.sheduleproject.presentation.schedulechoice.model.ScheduleType
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleChoiceFragment : Fragment() {

    private lateinit var binding: FragmentScheduleChoiceBinding

    private val viewModel: ScheduleChoiceFragmentViewModel by viewModel()

    private val scheduleChoicesAdapter by lazy {
        ScheduleChoicesAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_schedule_choice, container, false)
        binding = FragmentScheduleChoiceBinding.bind(mainView)

        val choiceType = arguments?.getString(ScheduleTypeChoiceFragment.SCHEDULE_TYPE_KEY)
        binding.choiceTypeTextView.text = choiceType
        onBackButtonClick()

        if (choiceType != null) {
            makeCorrectRequest(choiceType)
        }

        onGettingClustersListLiveData()
        onGettingEducatorsListLiveData()
        onGettingLectureHallsListLiveData()


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

    private fun makeCorrectRequest(choiceType: String) {
        when (choiceType) {
            ScheduleType.GROUP.getString(requireContext()) -> {
                viewModel.makeGetClustersListRequest { handleApiError(it) }
            }
            ScheduleType.EDUCATOR.getString(requireContext()) -> {
                viewModel.makeGetEducatorsListRequest { handleApiError(it) }
            }
            else -> {
                viewModel.makeGetLectureHallsListRequest { handleApiError(it) }
            }
        }

    }

    private fun onGettingClustersListLiveData() {
        viewModel.getClustersListLiveData().observe(this.viewLifecycleOwner) {
            scheduleChoicesAdapter.setList(it)
            binding.recyclerView.adapter = scheduleChoicesAdapter
        }
    }

    private fun onGettingEducatorsListLiveData() {
        viewModel.getEducatorsListLiveData().observe(this.viewLifecycleOwner) {
            scheduleChoicesAdapter.setList(it)
            binding.recyclerView.adapter = scheduleChoicesAdapter
        }
    }

    private fun onGettingLectureHallsListLiveData() {
        viewModel.getLectureHallsListLiveData().observe(this.viewLifecycleOwner) {
            scheduleChoicesAdapter.setList(it)
            binding.recyclerView.adapter = scheduleChoicesAdapter
        }
    }

    private fun handleApiError(errorCode: Int) {
        if (errorCode == NoConnectivityException.ERROR_CODE) {
            Toast.makeText(
                requireContext(),
                getString(R.string.internet_connection_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}