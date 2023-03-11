package com.example.sheduleproject.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.common.UserType
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.databinding.FragmentSplashBinding
import com.example.sheduleproject.presentation.common.model.BundleHelper
import com.example.sheduleproject.presentation.common.model.ScheduleType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val viewModel: SplashFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_splash, container, false)
        binding = FragmentSplashBinding.bind(mainView)

        lifecycleScope.launch {
            delay(500)
            onGettingTimeSlotsList()
        }

        return binding.root
    }

    private fun onGettingCheckTokenExistenceLiveData() {
        viewModel.getTokenInfoLiveData().observe(this.viewLifecycleOwner) {
            if (!it) {
                navigateToScheduleTypeChoiceFragment()
            } else {
                makeGetUserDataRequest()
                onGettingUserData()
            }
        }
    }

    private fun makeGetUserDataRequest() {
        viewModel.getUserData { onErrorAppearance(it) }
    }

    private fun onGettingAuthorizationData() {
        viewModel.getUserAuthorizationFlagLiveData().observe(this.viewLifecycleOwner) {
            if (it) {
                onGettingCheckTokenExistenceLiveData()
            } else {
                navigateToLoginFragment()
            }
        }
    }

    private fun onGettingUserData() {
        viewModel.getUserdataLiveData().observe(this.viewLifecycleOwner) {
            var bundle = Bundle()

            if (it.accountType == UserType.STUDENT.getString()) {
                bundle = BundleHelper.setupBundle(ScheduleType.CLUSTER, it.clusterNumber)
            } else if (it.accountType == UserType.EDUCATOR.getString() &&
                checkIfEducatorIsVerified(it.verifiedRoles)
            ) {
                bundle = BundleHelper.setupBundle(ScheduleType.EDUCATOR, it.id)
            }

            navigateToScheduleFragment(bundle)
        }
    }

    private fun checkIfEducatorIsVerified(verifiedRoles: List<String>?): Boolean {
        return verifiedRoles?.contains(UserType.EDUCATOR.getString()) ?: false
    }

    private fun onGettingTimeSlotsList() {
        viewModel.getTimeSlotsLiveData { onErrorAppearance(it) }.observe(this.viewLifecycleOwner) {
            if (it != null) {
                onGettingAuthorizationData()
                viewModel.saveTimeSlotsListToLocalStorage(it)
            }
        }
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun navigateToScheduleFragment(bundle: Bundle) {
        findNavController().navigate(R.id.action_splashFragment_to_scheduleFragment, bundle)
    }

    private fun navigateToScheduleTypeChoiceFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_scheduleTypeChoiceFragment)
    }

    private fun onErrorAppearance(errorCode: Int) {
        when (errorCode) {
            400 -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_400_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
            NoConnectivityException.ERROR_CODE -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.internet_connection_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


}