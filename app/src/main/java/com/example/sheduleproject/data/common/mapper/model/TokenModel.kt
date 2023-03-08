package com.example.sheduleproject.data.common.mapper.model

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("accessToken")
    var accessToken: String?,
    @SerializedName("refreshToken")
    var refreshToken: String?
)