package com.example.sheduleproject.presentation.entrance.registration.second

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
import com.example.sheduleproject.common.UserType
import com.example.sheduleproject.databinding.FragmentRegistrationSecondBinding
import com.example.sheduleproject.domain.entrance.registration.entity.first.RegistrationFirstEntity
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.toStringError
import com.example.sheduleproject.presentation.entrance.common.model.setEditTextsInputSpaceFilter
import com.example.sheduleproject.presentation.entrance.registration.first.RegistrationFirstFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationSecondFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationSecondBinding

    private val viewModel: RegistrationSecondViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_registration_second, container, false)
        binding = FragmentRegistrationSecondBinding.bind(mainView)

        val data =
            arguments?.getSerializable(RegistrationFirstFragment.FIRST_REGISTRATION_DATA_KEY) as RegistrationFirstEntity?

        setupOnButtonClickFunctions()
        onBackButtonClick(data)
        setInputFilterToEditTexts()

        binding.repeatPasswordEditText.setEditTextHint(getString(R.string.repeat_password))

        return binding.root
    }

    private fun setupOnButtonClickFunctions() {
        onRegistrationButtonClick()
        onPickerButtonClick()
        onPasswordsClick()
    }

    private fun onPasswordsClick() {
        with(binding) {
            passwordEditText.setupFunctions()
            repeatPasswordEditText.setupFunctions()
        }
    }

    private fun onRegistrationButtonClick() {
        binding.registrationButton.setOnClickListener {
            validateAllFields()
            if (!checkIfFieldsHaveErrors()) {
                navigateToScheduleFragment()
            }
        }
    }

    private fun onBackButtonClick(data: RegistrationFirstEntity?) {
        binding.backButton.setOnClickListener {
            setupBundle(data)
            navigateToFirstRegistrationFragment(setupBundle(data))
        }
    }

    private fun setupBundle(data: RegistrationFirstEntity?): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(RegistrationFirstFragment.FIRST_REGISTRATION_DATA_KEY, data)
        return bundle
    }

    private fun onPickerButtonClick() {
        binding.userTypePicker.onPickerButtonsClick {
            changeIdTextVisibility()
        }
    }

    private fun changeIdTextVisibility() {
        if (binding.userTypePicker.getCorrectMeaningOfUserType() == UserType.STUDENT) {
            binding.idEditText.visibility = View.VISIBLE
        } else {
            binding.idEditText.visibility = View.GONE
            binding.idErrorTextView.visibility = View.GONE
        }
    }

    private fun navigateToScheduleFragment() {
        findNavController().navigate(R.id.action_registrationSecondFragment_to_scheduleFragment)
    }

    private fun navigateToFirstRegistrationFragment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_registrationSecondFragment_to_registrationFragment,
            bundle
        )
    }

    private fun checkIfFieldsHaveErrors(): Boolean {
        for (view in binding.linearLayout) {
            if (view !is EditText && view is TextView) {
                if (view.visibility == View.VISIBLE) return true
            }
        }
        return false
    }

    private fun validateAllFields() {
        validateUserTypePickerField()
        validateIdNumberField()
        validatePasswordField()
        validateTwoPasswordsField()
    }

    private fun validatePasswordField() {
        viewModel
            .getPasswordValidationResultLiveData(binding.passwordEditText.text().toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.passwordErrorTextView, it)
            }
    }

    private fun validateUserTypePickerField() {
        val validationResult: ValidationResult =
            if (binding.userTypePicker.checkIsPickerInvolved()) {
                ValidationResult.OK
            } else {
                ValidationResult.EMPTY_ERROR
            }
        showValidationError(binding.pickerErrorTextView, validationResult)
    }

    private fun validateIdNumberField() {
        if (binding.idEditText.visibility == View.VISIBLE) {
            viewModel
                .getIdNumberValidationResultLiveData(binding.idEditText.text.toString())
                .observe(this.viewLifecycleOwner) {
                    showValidationError(binding.idErrorTextView, it)
                }
        }
    }

    private fun validateTwoPasswordsField() {
        val string = binding.passwordEditText.text().toString() +
                "\n" + binding.repeatPasswordEditText.text().toString()
        viewModel
            .getTwoPasswordsValidationResultLiveData(string)
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.twoPasswordErrorTextView, it)
                if (it != ValidationResult.OK) binding.repeatPasswordEditText.clearText()
            }
    }

    private fun showValidationError(textView: TextView, validationResult: ValidationResult) {
        when (validationResult) {
            ValidationResult.OK -> textView.visibility = View.GONE
            else -> {
                textView.text = validationResult.toStringError(requireContext())
                textView.visibility = View.VISIBLE
            }
        }
    }

    private fun setInputFilterToEditTexts() {
        binding.idEditText.setEditTextsInputSpaceFilter()
    }

}