package com.example.sheduleproject.data.common.mapper

import com.example.sheduleproject.data.common.mapper.model.TokenModel
import com.example.sheduleproject.domain.common.entity.TokenEntity

fun TokenModel.toEntity(): TokenEntity =
    TokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken
    )

fun TokenEntity.toModel(): TokenModel =
    TokenModel(
        accessToken = accessToken,
        refreshToken = refreshToken
    )