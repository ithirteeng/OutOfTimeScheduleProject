package com.example.sheduleproject.presentation.common.model

import android.os.Bundle

object BundleHelper {
    const val CLUSTER_KEY = "cluster bundle key"
    const val EDUCATOR_KEY = "educator bundle key"
    const val LECTURE_HALL_KEY = "lecture hall bundle key"

    fun setupBundle(scheduleType: ScheduleType, parameter: String?): Bundle {
        val bundle = Bundle()

        when (scheduleType) {
            ScheduleType.CLUSTER -> {
                bundle.putString(CLUSTER_KEY, parameter)
                bundle.putString(LECTURE_HALL_KEY, null)
                bundle.putString(EDUCATOR_KEY, null)
            }
            ScheduleType.EDUCATOR -> {
                bundle.putString(CLUSTER_KEY, null)
                bundle.putString(LECTURE_HALL_KEY, null)
                bundle.putString(EDUCATOR_KEY, parameter)
            }
            ScheduleType.LECTURE_HALL -> {
                bundle.putString(CLUSTER_KEY, null)
                bundle.putString(LECTURE_HALL_KEY, parameter)
                bundle.putString(EDUCATOR_KEY, null)
            }
        }

        return bundle
    }


}