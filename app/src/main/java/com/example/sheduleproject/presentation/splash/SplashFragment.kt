package com.example.sheduleproject.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

	private lateinit var binding: FragmentSplashBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val mainView = inflater.inflate(R.layout.fragment_splash, container, false)
		binding = FragmentSplashBinding.bind(mainView)

		lifecycleScope.launch {
			delay(1000)
			//navigateToLoginFragment()
			navigateToScheduleFragment()
		}

		return binding.root
	}

	private fun navigateToLoginFragment() {
		findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
	}

	private fun navigateToScheduleFragment() {
		findNavController().navigate(R.id.action_splashFragment_to_scheduleFragment)
	}


}