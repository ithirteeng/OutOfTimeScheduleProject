package com.example.sheduleproject.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentSheduleBinding

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentSheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_shedule, container, false)
        binding = FragmentSheduleBinding.bind(mainView)


        return binding.root
    }

}