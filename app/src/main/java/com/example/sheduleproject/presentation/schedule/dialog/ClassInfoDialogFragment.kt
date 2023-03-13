package com.example.sheduleproject.presentation.schedule.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.CustomClassInfoDialogLayoutBinding
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.entity.EducatorEntity

class ClassInfoDialogFragment(
    private var classEntity: ClassEntity? = null
) : DialogFragment() {


    private lateinit var binding: CustomClassInfoDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view =
            requireActivity().layoutInflater.inflate(R.layout.custom_class_info_dialog_layout, null)
        binding = CustomClassInfoDialogLayoutBinding.bind(view)

        binding.classNameTextView.text = makeSubjectStringCorrect(classEntity?.subject?.name)
        binding.educatorTextView.text = makeStringCorrect(setupEducatorName(classEntity?.educator))
        binding.classTypeTextView.text = makeStringCorrect(classEntity?.classTypeName)
        binding.locationTextView.text = setupLectureHallData(classEntity!!)
        binding.clusterTextView.text = makeStringCorrect(classEntity?.clusterNumber)

        val builder = AlertDialog.Builder(requireActivity(), R.style.ProgressDialogTheme)
        return builder.setView(view).create()
    }

    fun setClassEntity(classEntity: ClassEntity) {
        this.classEntity = classEntity
    }

    private fun setupLectureHallData(classEntity: ClassEntity): String {
        val building = makeStringCorrect(classEntity.lectureHall?.hostBuilding?.name)
        val lectureHall = makeStringCorrect(classEntity.lectureHall?.name)

        val result = if (building.isEmpty()) {
            lectureHall
        } else {
            "$building: $lectureHall"
        }

        return result
    }

    private fun setupEducatorName(educatorEntity: EducatorEntity?): String {
        return makeStringCorrect(educatorEntity?.lastName) +
                " ${makeStringCorrect(educatorEntity?.firstName)}" +
                " ${makeStringCorrect(educatorEntity?.middleName)}"
    }

    private fun makeStringCorrect(string: String?): String {
        return string?.trim() ?: ""
    }

    private fun makeSubjectStringCorrect(string: String?): String {
        return if (string == null || string.isEmpty()) {
            requireContext().getString(R.string.default_class_text)
        } else {
            string
        }
    }


}