package com.example.sheduleproject.domain.entrance.registration.usecase.second

import com.example.sheduleproject.domain.entrance.utils.ValidationResult
import com.example.sheduleproject.domain.entrance.utils.validator.ClusterValidator

class ValidateClusterUseCase(
    private val validator: ClusterValidator
) {
    operator fun invoke(clusterNumber: String): ValidationResult =
        validator.validate(clusterNumber)
}