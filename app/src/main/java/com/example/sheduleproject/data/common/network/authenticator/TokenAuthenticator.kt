package com.example.sheduleproject.data.common.network.authenticator

import com.example.sheduleproject.data.common.network.AUTHORIZATION_HEADER
import com.example.sheduleproject.data.common.network.BEARER
import com.example.sheduleproject.domain.token.usecase.GetTokenFromLocalStorageUseCase
import com.example.sheduleproject.domain.token.usecase.RefreshTokenUseCase
import com.example.sheduleproject.domain.token.usecase.SaveTokenToLocalStorageUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

@Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_AGAINST_NOT_NOTHING_EXPECTED_TYPE")
class TokenAuthenticator(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            getTokenFromLocalStorageUseCase()?.let { localToken ->
                refreshTokenUseCase(localToken).let { newToken ->
                    saveTokenToLocalStorageUseCase(newToken)
                    response.request.newBuilder()
                        .authorizationHeader(newToken.accessToken!!)
                        .build()
                }
            } ?: kotlin.run {
                null
            }
        }
    }

    private fun Request.Builder.authorizationHeader(accessToken: String): Request.Builder {
        return header(
            AUTHORIZATION_HEADER,
            "$BEARER $accessToken"
        )
    }


}