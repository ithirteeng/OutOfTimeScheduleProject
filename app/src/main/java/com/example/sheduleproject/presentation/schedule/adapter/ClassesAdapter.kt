package com.example.sheduleproject.presentation.schedule.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduleproject.databinding.ClassCustomViewLayoutBinding
import com.example.sheduleproject.domain.common.entity.TimeSlotEntity
import com.example.sheduleproject.domain.schedule.entity.ClassEntity
import com.example.sheduleproject.presentation.schedule.model.ClassCustomView

class ClassesAdapter(private val onCardClick: (classEntity: ClassEntity) -> Unit) :
    RecyclerView.Adapter<ClassesAdapter.ClassesViewHolder>() {

    inner class ClassesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ClassCustomViewLayoutBinding.bind(view)

        init {
            binding.mainView.setOnClickListener {
                onCardClick(classesList[bindingAdapterPosition])
            }
        }

        fun setupData(classEntity: ClassEntity, timeSlotEntity: TimeSlotEntity) {
            (binding.root as ClassCustomView).setupData(classEntity, timeSlotEntity)
        }
    }

    private var classesList = arrayListOf<ClassEntity>()

    private var timeSlotsList = listOf<TimeSlotEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val customView = ClassCustomView(parent.context)
        customView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ClassesViewHolder(customView)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        val classEntity = classesList[position]
        val timeSlotEntity = timeSlotsList[classEntity.timeSlotNumber - 1]
        holder.setupData(classEntity, timeSlotEntity)
    }

    private fun sortClassesList() {
        classesList.sortBy { it.timeSlotNumber }
    }

    override fun getItemCount(): Int = classesList.size

    fun setClassesList(list: ArrayList<ClassEntity>) {
        classesList = list
        sortClassesList()
    }

    fun setTimeSlotsList(list: List<TimeSlotEntity>) {
        timeSlotsList = list
    }
}