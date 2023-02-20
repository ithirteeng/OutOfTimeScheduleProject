package com.example.sheduleproject.presentation.entrance.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

	private lateinit var binding: FragmentLoginBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val mainView = inflater.inflate(R.layout.fragment_login, container, false)
		binding = FragmentLoginBinding.bind(mainView)

		return binding.root
	}

	private fun navigateToRegistrationFragment() {
		findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
	}

	private fun navigateToScheduleFragment() {
		findNavController().navigate(R.id.action_loginFragment_to_scheduleFragment)
	}
}