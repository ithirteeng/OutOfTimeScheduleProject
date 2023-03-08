package com.example.sheduleproject.data.token.mapper

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.domain.token.entity.TokenEntity

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