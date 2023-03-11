package com.example.sheduleproject.data.entrance.login.mapper

import com.example.sheduleproject.data.entrance.login.model.EducatorModel
import com.example.sheduleproject.data.entrance.login.model.LoginModel
import com.example.sheduleproject.data.entrance.login.model.UserModel
import com.example.sheduleproject.domain.entrance.login.entity.EducatorEntity
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.entity.UserEntity

fun LoginEntity.toModel(): LoginModel =
    LoginModel(
        email = email,
        password = password
    )

fun EducatorModel.toEntity(): EducatorEntity =
    EducatorEntity(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName
    )

fun UserModel.toEntity(): UserEntity =
    UserEntity(
        id = id,
        email = email,
        password = password,
        accountType = accountType,
        claimedRoles = claimedRoles,
        verifiedRoles = verifiedRoles,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        gradeBookNumber = gradeBookNumber,
        clusterNumber = clusterNumber,
        scheduleSelf = scheduleSelf?.toEntity()
    )