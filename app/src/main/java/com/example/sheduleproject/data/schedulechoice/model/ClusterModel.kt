package com.example.sheduleproject.data.schedulechoice.model

import com.google.gson.annotations.SerializedName

data class ClusterModel(
    @SerializedName("number")
    val number: String,
    @SerializedName("superClusterNumber")
    var superClusterNumber: String?
)
