package com.example.sheduleproject.presentation.entrance.registration.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sheduleproject.R
import com.example.sheduleproject.common.UserType
import com.example.sheduleproject.data.common.network.interceptor.NoConnectivityException
import com.example.sheduleproject.databinding.FragmentRegistrationSecondBinding
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.presentation.common.model.BundleHelper
import com.example.sheduleproject.presentation.common.model.ScheduleType
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

        setupOnButtonClickFunctions()
        onBackButtonClick()
        onBackPress()
        setInputFilterToEditTexts()

        binding.repeatPasswordEditText.setEditTextHint(getString(R.string.repeat_password))

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setCorrectValuesToFields()
    }

    private fun setupRegistrationData(): RegistrationEntity {
        val data = arguments?.getSerializable(
            RegistrationFirstFragment.REGISTRATION_DATA_KEY
        ) as RegistrationEntity

        data.accountType = binding.userTypePicker.getCorrectMeaningOfUserType()
        data.gradeBookNumber = binding.gradeBookEditText.text.toString()
        data.clusterNumber = binding.clusterNumberEditText.text.toString()
        data.password = binding.passwordEditText.text().toString()

        return data
    }

    private fun setCorrectValuesToFields() {
        val data = arguments?.getSerializable(
            RegistrationFirstFragment.REGISTRATION_DATA_KEY
        ) as RegistrationEntity?

        if (!(data?.accountType == null || data.accountType == UserType.DEFAULT)) {
            binding.userTypePicker.setCorrectType(userType = data.accountType)
        }

        changeStudentTextsVisibility()

        binding.gradeBookEditText.setText(data?.gradeBookNumber)
        binding.clusterNumberEditText.setText(data?.clusterNumber)
        binding.passwordEditText.setText(data?.password)
        binding.repeatPasswordEditText.setText(data?.password)
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
            binding.registrationButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
            validateAllFields()
            if (!checkIfFieldsHaveErrors()) {
                makePostRegistrationRequest()
                onGettingRegistrationLiveData()
            } else {
                binding.progressBar.visibility = View.GONE
                binding.registrationButton.isEnabled = true
            }
        }
    }

    private fun makePostRegistrationRequest() {
        viewModel.makePostRegistrationDataRequest(
            setupRegistrationData(),
            onErrorAppearance = { handleApiErrors(it) }
        )
    }

    private fun onGettingRegistrationLiveData() {
        viewModel.getRegistrationResultLiveData().observe(this.viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            if (it != null) {
                viewModel.saveTokenToLocalStorage(it)
                if (binding.userTypePicker.getCorrectMeaningOfUserType() == UserType.STUDENT) {
                    navigateToScheduleFragment(setupScheduleBundle())
                } else {
                    navigateToChoiceScheduleTypeFragment()
                }
            }
        }
    }

    private fun setupScheduleBundle(): Bundle {
        val userType = binding.userTypePicker.getCorrectMeaningOfUserType()
        return if (userType == UserType.STUDENT) {
            BundleHelper.setupBundle(
                ScheduleType.CLUSTER,
                binding.clusterNumberEditText.text.toString()
            )
        } else {
            Bundle()
        }
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            navigateToFirstRegistrationFragment(setupRegistrationBundle(setupRegistrationData()))
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun onBackPress() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToFirstRegistrationFragment(
                        setupRegistrationBundle(
                            setupRegistrationData()
                        )
                    )
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this.viewLifecycleOwner,
            callback
        )
    }

    private fun setupRegistrationBundle(data: RegistrationEntity?): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(RegistrationFirstFragment.REGISTRATION_DATA_KEY, data)
        return bundle
    }

    private fun onPickerButtonClick() {
        binding.userTypePicker.onPickerButtonsClick {
            changeStudentTextsVisibility()
        }
    }

    private fun changeStudentTextsVisibility() {
        if (binding.userTypePicker.getCorrectMeaningOfUserType() == UserType.STUDENT) {
            binding.gradeBookEditText.visibility = View.VISIBLE
            binding.clusterNumberEditText.visibility = View.VISIBLE
        } else {
            binding.gradeBookEditText.visibility = View.GONE
            binding.gradleBookErrorTextView.visibility = View.GONE
            binding.clusterErrorTextView.visibility = View.GONE
            binding.clusterNumberEditText.visibility = View.GONE
        }
    }

    private fun navigateToScheduleFragment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_registrationSecondFragment_to_scheduleFragment,
            bundle
        )
    }

    private fun navigateToChoiceScheduleTypeFragment() {
        findNavController().navigate(R.id.action_registrationSecondFragment_to_scheduleTypeChoiceFragment)
    }

    private fun navigateToFirstRegistrationFragment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_registrationSecondFragment_to_registrationFragment,
            bundle
        )
    }

    private fun handleApiErrors(errorCode: Int) {
        binding.registrationButton.isEnabled = true
        when (errorCode) {
            409 -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.registration_409_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
            404 -> {
                if (binding.clusterNumberEditText.visibility == View.VISIBLE) {
                    showValidationError(
                        binding.clusterErrorTextView,
                        ValidationResult.CLUSTER_ERROR
                    )
                }
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
        validateGradleBookNumberField()
        validatePasswordField()
        validateClusterField()
        validateTwoPasswordsField()
    }

    private fun validatePasswordField() {
        viewModel
            .getPasswordValidationResultLiveData(binding.passwordEditText.text().toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.passwordErrorTextView, it)
            }
    }

    private fun validateClusterField() {
        if (binding.clusterNumberEditText.visibility == View.VISIBLE) {
            viewModel
                .getClusterValidationResultLiveData(binding.clusterNumberEditText.text.toString())
                .observe(this.viewLifecycleOwner) {
                    showValidationError(binding.clusterErrorTextView, it)
                }
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

    private fun validateGradleBookNumberField() {
        if (binding.gradeBookEditText.visibility == View.VISIBLE) {
            viewModel
                .getGradleBookNumberValidationResultLiveData(binding.gradeBookEditText.text.toString())
                .observe(this.viewLifecycleOwner) {
                    showValidationError(binding.gradleBookErrorTextView, it)
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
                textView.text = validationResult.getErrorString(requireContext())
                textView.visibility = View.VISIBLE
            }
        }
    }

    private fun setInputFilterToEditTexts() {
        binding.gradeBookEditText.setEditTextsInputSpaceFilter()
        binding.clusterNumberEditText.setEditTextsInputSpaceFilter()
    }

}