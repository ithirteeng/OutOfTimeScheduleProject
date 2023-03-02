package com.example.sheduleproject.presentation.entrance.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.FragmentLoginBinding
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.presentation.entrance.common.model.setEditTextsInputSpaceFilter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.bind(mainView)

        setupOnClickFunctions()
        binding.emailEditText.setEditTextsInputSpaceFilter()

        return binding.root
    }

    private fun setupOnClickFunctions() {
        onLoginButtonClick()
        onRegistrationButtonClick()
        onWithoutButtonClick()
        onPasswordEditTextClick()
    }

    private fun onPasswordEditTextClick() {
        binding.passwordEditText.setupFunctions()
    }

    private fun onLoginButtonClick() {
        binding.loginButton.setOnClickListener {
            validateAllFields()
            //if (!checkIfFieldsHaveErrors()) {
            navigateToScheduleFragment()
            //  }
        }
    }

    private fun onRegistrationButtonClick() {
        binding.registrationButton.setOnClickListener {
            navigateToRegistrationFragment()
        }
    }

    private fun onWithoutButtonClick() {
        binding.withoutButton.setOnClickListener {
            navigateToScheduleFragment()
        }
    }

    private fun navigateToRegistrationFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }

    private fun navigateToScheduleFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_scheduleFragment)
    }

    private fun validateAllFields() {
        validateEmailField()
        validatePasswordField()
    }

    private fun checkIfFieldsHaveErrors(): Boolean {
        for (view in binding.linearLayout) {
            if (view !is EditText && view is TextView) {
                if (view.visibility == View.VISIBLE) return true
            }
        }
        return false
    }

    private fun validateEmailField() {
        viewModel
            .getEmailValidationResultLiveData(binding.emailEditText.text.toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.emailErrorTextView, it)
            }
    }

    private fun validatePasswordField() {
        viewModel
            .getPasswordValidationResultLiveData(binding.passwordEditText.text().toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.passwordErrorTextView, it)
            }
    }

    private fun showValidationError(textView: TextView, validationResult: ValidationResult) {
        when (validationResult) {
            ValidationResult.OK -> textView.visibility = View.GONE
            else -> {
                textView.text = validationResult.getErrorString(requireContext())
                textView.visibility = View.VISIBLE
            }
        }
    }
}