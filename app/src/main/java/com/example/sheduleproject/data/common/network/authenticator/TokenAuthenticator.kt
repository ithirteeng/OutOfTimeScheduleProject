package com.example.sheduleproject.data.common.network.authenticator

import com.example.sheduleproject.data.common.model.TokenModel
import com.example.sheduleproject.data.token.mapper.toEntity
import com.example.sheduleproject.domain.token.usecase.GetTokenUseCase
import com.example.sheduleproject.domain.token.usecase.RefreshTokenUseCase
import com.example.sheduleproject.domain.token.usecase.SaveTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val getTokenUseCase: GetTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val updatedToken = getUpdatedToken()
            response.request.newBuilder()
                .header("Authorization", "Bearer ${updatedToken.accessToken}")
                .build()
        }
    }

    private fun getUpdatedToken(): TokenModel {
        val token = getTokenUseCase()

        val newToken = refreshTokenUseCase(token)

        saveTokenUseCase(newToken.body()!!.toEntity())
        return newToken.body()!!
    }

}