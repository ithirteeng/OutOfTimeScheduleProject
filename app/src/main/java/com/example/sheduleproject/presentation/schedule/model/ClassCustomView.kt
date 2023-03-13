package com.example.sheduleproject.presentation.schedule.model

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.ClassCustomViewLayoutBinding
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.domain.schedule.entity.ClassType
import com.example.sheduleproject.domain.schedule.entity.EducatorEntity
import com.example.sheduleproject.domain.schedule.entity.toClassType
import com.example.sheduleproject.domain.schedule.utils.DateTimeHelper


class ClassCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val mainView: View =
        LayoutInflater.from(context).inflate(R.layout.class_custom_view_layout, this)

    private val binding = ClassCustomViewLayoutBinding.bind(mainView)

    private val dateTimeHelper = DateTimeHelper(context)

    fun setupData(classEntity: ClassEntity, timeSlotEntity: TimeSlotEntity) {
        val classType = classEntity.classTypeName.toClassType()
        with(binding) {
            timeTextView.text = setCorrectTimePeriod(timeSlotEntity)
            classNameTextView.text = makeSubjectStringCorrect(classEntity.subject?.name)
            lectureHallTextView.text = setupLectureHallData(classEntity)
            educatorNameTextView.text = makeStringCorrect(setupEducatorName(classEntity.educator))
            changeCardStyle(classType)
        }
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

    private fun setCorrectTimePeriod(timeSlotEntity: TimeSlotEntity): String {
        val startTime = dateTimeHelper.getCorrectClassesTime(timeSlotEntity.startTime)
        val endTime = dateTimeHelper.getCorrectClassesTime(timeSlotEntity.endTime)
        return "$startTime – $endTime"
    }

    private fun changeCardStyle(classType: ClassType) {
        val classColors = getCorrectColorIds(classType)
        binding.borderView.changeViewBackground(classColors.borderColorId)
        binding.mainView.changeViewBackground(classColors.mainColorId)
        binding.classNameTextView.setTextColor(classColors.textColorId)
        binding.lectureHallTextView.setTextColor(classColors.textColorId)
        binding.educatorNameTextView.setTextColor(classColors.textColorId)
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
            context.getString(R.string.default_class_text)
        } else {
            string
        }
    }


    private fun View.changeViewBackground(colorId: Int) {
        this.background.colorFilter = PorterDuffColorFilter(
            resources.getColor(colorId, context.theme),
            PorterDuff.Mode.SRC_OVER
        )
    }

    private fun getCorrectColorIds(classType: ClassType): ClassColors = when (classType) {
        ClassType.LECTURE -> ClassColors(
            borderColorId = R.color.lecture_border,
            mainColorId = R.color.lecture_main,
            textColorId = R.color.lecture_text_color
        )
        ClassType.EXAM -> ClassColors(
            borderColorId = R.color.exam_border,
            mainColorId = R.color.exam_main,
            textColorId = R.color.exam_text_color
        )
        ClassType.LABORATORY -> ClassColors(
            borderColorId = R.color.laboratory_border,
            mainColorId = R.color.laboratory_main,
            textColorId = R.color.laboratory_text_color
        )
        ClassType.PRACTICE -> ClassColors(
            borderColorId = R.color.practice_border,
            mainColorId = R.color.practice_main,
            textColorId = R.color.practice_text_color
        )
        ClassType.SEMINAR -> ClassColors(
            borderColorId = R.color.seminar_border,
            mainColorId = R.color.seminar_main,
            textColorId = R.color.seminar_text_color
        )
        else -> ClassColors(
            borderColorId = R.color.default_border,
            mainColorId = R.color.default_main,
            textColorId = R.color.default_text_color
        )
    }

}