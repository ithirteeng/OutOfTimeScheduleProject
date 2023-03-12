package com.example.sheduleproject.presentation.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.CustomErrorDialogLayoutBinding

class CustomErrorDialogFragment : DialogFragment() {

    private lateinit var customDialogLayoutBinding: CustomErrorDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view =
            requireActivity().layoutInflater.inflate(R.layout.custom_error_dialog_layout, null)
        customDialogLayoutBinding = CustomErrorDialogLayoutBinding.bind(view)

        val builder = AlertDialog.Builder(requireActivity(), R.style.ProgressDialogTheme)
        return builder.setView(view).create()
    }


}