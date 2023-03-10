package com.example.sheduleproject.presentation.schedulechoice.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sheduleproject.R
import com.example.sheduleproject.databinding.ScheduleChoiceItemBinding
import com.example.sheduleproject.domain.schedulechoice.entity.ClusterEntity
import com.example.sheduleproject.domain.schedulechoice.entity.EducatorEntity
import com.example.sheduleproject.domain.schedulechoice.entity.LectureHallEntity

class ScheduleChoicesAdapter(
    private val onClick: (data: Any) -> Unit
) : RecyclerView.Adapter<ScheduleChoicesAdapter.ScheduleChoicesViewHolder>() {

    class ScheduleChoicesViewHolder(view: View) : ViewHolder(view) {
        private val binding = ScheduleChoiceItemBinding.bind(view)

        fun onItemClick(onClick: () -> Unit) {
            binding.button.setOnClickListener {
                onClick()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindData(itemData: Any) {
            when (itemData) {
                is ClusterEntity -> {
                    binding.button.text = itemData.number
                }
                is EducatorEntity -> {
                    val fullName = makeStringCorrect(itemData.lastName) +
                            " ${makeStringCorrect(itemData.firstName)}" +
                            " ${makeStringCorrect(itemData.middleName)}"
                    binding.button.text = fullName
                }
                is LectureHallEntity -> {
                    binding.button.text = "${itemData.name}: ${itemData.hostBuilding.name}"
                }
            }
        }

        private fun makeStringCorrect(string: String?): String = string ?: ""

    }

    private var list = arrayListOf<Any>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(itemsList: List<Any>) {
        list = ArrayList(itemsList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleChoicesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.schedule_choice_item, parent, false)
        return ScheduleChoicesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleChoicesViewHolder, position: Int) {
        val itemData = list[position]
        holder.bindData(itemData)
        holder.onItemClick { onClick(itemData) }
    }

    override fun getItemCount(): Int = list.size
}