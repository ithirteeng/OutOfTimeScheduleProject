package com.example.sheduleproject.domain.entrance.login.usecase
import com.example.sheduleproject.domain.entrance.login.entity.LoginEntity
import com.example.sheduleproject.domain.entrance.login.repository.LoginRepository
import com.example.sheduleproject.domain.token.entity.TokenEntity

class PostLoginDataUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginEntity: LoginEntity): Result<TokenEntity> =
        repository.postLoginData(loginEntity)
}