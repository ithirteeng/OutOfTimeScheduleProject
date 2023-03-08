package com.example.sheduleproject.data.entrance.login.mapper

import com.example.sheduleproject.data.entrance.login.model.LoginModel
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity

fun LoginEntity.toModel(): LoginModel =
    LoginModel(
        email = email,
        password = password
    )