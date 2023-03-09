package com.example.sheduleproject.presentation.schedule.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.CustomDialogLayoutBinding

class CustomErrorDialogFragment : DialogFragment() {

    private lateinit var customDialogLayoutBinding: CustomDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.custom_dialog_layout, null)
        customDialogLayoutBinding = CustomDialogLayoutBinding.bind(view)

        val builder = AlertDialog.Builder(requireActivity(), R.style.ProgressDialogTheme)
        return builder.setView(view).create()
    }




}