package com.example.sheduleproject.presentation.entrance.registration.first

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
import com.example.sheduleproject.databinding.FragmentRegistrationBinding
import com.example.sheduleproject.domain.entrance.registration.entity.RegistrationEntity
import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.presentation.entrance.common.model.setEditTextsInputSpaceFilter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFirstFragment : Fragment() {

    companion object {
        const val REGISTRATION_DATA_KEY = "first bundle key"
    }

    private val viewModel: RegistrationFirstViewModel by viewModel()

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainView = inflater.inflate(R.layout.fragment_registration, container, false)
        binding = FragmentRegistrationBinding.bind(mainView)

        setInputFilterToEditTexts()
        setupOnClickFunctions()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val data =
            arguments?.getSerializable(REGISTRATION_DATA_KEY) as RegistrationEntity?
        setupTextFields(data)
    }

    private fun setupTextFields(data: RegistrationEntity?) {
        if (data != null) {
            binding.emailEditText.setText(data.email)
            binding.nameEditText.setText(data.firstName)
            binding.surnameEditText.setText(data.lastName)
            binding.patronymicEditText.setText(data.middleName)
        }
    }

    private fun setupOnClickFunctions() {
        onNextPageButtonClick()
        onBackButtonClick()
    }


    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            navigateToLoginFragment()
        }
    }

    private fun onNextPageButtonClick() {
        binding.nextPageButton.setOnClickListener {
            validateAllFields()
            if (!checkIfFieldsHaveErrors()) {
                navigateToSecondRegistrationFragment(setupBundle())
            }

        }

    }

    private fun setupBundle(): Bundle {
        val bundle = Bundle()
        val data =
            arguments?.getSerializable(REGISTRATION_DATA_KEY) as RegistrationEntity?
        with(binding) {
            val entity = RegistrationEntity(
                email = emailEditText.text.toString(),
                lastName = surnameEditText.text.toString(),
                firstName = nameEditText.text.toString(),
                middleName = patronymicEditText.text.toString(),
                accountType = if (data?.accountType == null) UserType.DEFAULT else data.accountType,
                gradeBookNumber = data?.gradeBookNumber,
                password = data?.password,
                clusterNumber = data?.clusterNumber
            )
            bundle.putSerializable(REGISTRATION_DATA_KEY, entity)
        }
        return bundle
    }

    private fun navigateToSecondRegistrationFragment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_registrationFragment_to_registrationSecondFragment,
            bundle
        )
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }

    private fun checkIfFieldsHaveErrors(): Boolean {
        for (view in binding.linearLayout) {
            if (view !is EditText) {
                if (view.visibility == View.VISIBLE) return true
            }
        }
        return false
    }

    private fun validateAllFields() {
        validateEmailField()
        validateSurnameField()
        validateNameField()
        validatePatronymicField()
    }


    private fun validateEmailField() {
        viewModel
            .getEmailValidationResultLiveData(binding.emailEditText.text.toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.emailErrorTextView, it)
            }
    }

    private fun validateNameField() {
        viewModel
            .getNameValidationResultLiveData(binding.nameEditText.text.toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.nameErrorTextView, it)
            }
    }

    private fun validateSurnameField() {
        viewModel
            .getSurnameValidationResultLiveData(binding.surnameEditText.text.toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.surnameErrorTextView, it)
            }
    }

    private fun validatePatronymicField() {
        viewModel
            .getPatronymicValidationResultLiveData(binding.patronymicEditText.text.toString())
            .observe(this.viewLifecycleOwner) {
                showValidationError(binding.patronymicErrorTextView, it)
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
        for (view in binding.linearLayout) {
            if (view is EditText) {
                view.setEditTextsInputSpaceFilter()
            }
        }
    }

}