package com.example.sheduleproject.presentation.entrance.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_registration, container, false)
        binding = FragmentRegistrationBinding.bind(mainView)

        lifecycleScope.launch {
            delay(1000)
            navigateToScheduleFragment()
        }
        return binding.root
    }

    private fun navigateToScheduleFragment() {
        findNavController().navigate(R.id.action_registrationFragment_to_scheduleFragment)
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }

}