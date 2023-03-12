package com.example.sheduleproject.data.schedulechoice.mapper

import com.example.sheduleproject.data.schedulechoice.model.CampusBuildingModel
import com.example.sheduleproject.data.schedulechoice.model.ClusterModel
import com.example.sheduleproject.data.schedulechoice.model.EducatorModel
import com.example.sheduleproject.data.schedulechoice.model.LectureHallModel
import com.example.sheduleproject.domain.schedulechoice.entity.CampusBuildingEntity
import com.example.sheduleproject.domain.schedulechoice.entity.ClusterEntity
import com.example.sheduleproject.domain.schedulechoice.entity.EducatorEntity
import com.example.sheduleproject.domain.schedulechoice.entity.LectureHallEntity


fun List<LectureHallModel>.toEntitiesList(): List<LectureHallEntity> =
    map { it.toEntity() }

@JvmName("toEntitiesListClusterModel")
fun List<ClusterModel>.toEntitiesList(): List<ClusterEntity> =
    map { it.toEntity() }


@JvmName("toEntitiesListEducatorModel")
fun List<EducatorModel>.toEntitiesList(): List<EducatorEntity> =
    map { it.toEntity() }


private fun ClusterModel.toEntity(): ClusterEntity =
    ClusterEntity(
        number = number,
        superClusterNumber = superClusterNumber
    )


private fun CampusBuildingModel.toEntity(): CampusBuildingEntity =
    CampusBuildingEntity(
        id = id,
        address = address,
        name = name,
        lectureHallIds = lectureHallIds
    )


private fun EducatorModel.toEntity(): EducatorEntity =
    EducatorEntity(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName
    )


private fun LectureHallModel.toEntity(): LectureHallEntity =
    LectureHallEntity(
        id = id,
        name = name,
        hostBuilding = hostBuilding.toEntity(),
        capacity = capacity
    )
