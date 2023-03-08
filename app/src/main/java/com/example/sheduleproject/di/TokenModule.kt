package com.example.sheduleproject.di

import com.example.sheduleproject.data.token.datasource.LocalTokenDatasource
import com.example.sheduleproject.data.token.datasource.LocalTokenDatasourceImpl
import com.example.sheduleproject.data.token.datasource.TokenDatasource
import com.example.sheduleproject.data.token.datasource.TokenDatasourceImpl
import com.example.sheduleproject.data.token.repository.TokenRepositoryImpl
import com.example.sheduleproject.data.token.storage.TokenStorage
import com.example.sheduleproject.domain.token.repository.TokenRepository
import com.example.sheduleproject.domain.token.usecase.GetTokenUseCase
import com.example.sheduleproject.domain.token.usecase.RefreshTokenUseCase
import com.example.sheduleproject.domain.token.usecase.SaveTokenUseCase
import org.koin.dsl.module

val tokenModule = module {
    factory { TokenStorage(context = get()) }

    factory<LocalTokenDatasource> { LocalTokenDatasourceImpl(tokenStorage = get()) }
    factory<TokenDatasource> { TokenDatasourceImpl(api = get()) }

    factory<TokenRepository> {
        TokenRepositoryImpl(
            localTokenDatasource = get(),
            remoteTokenDatasource = get()
        )
    }

    factory { GetTokenUseCase(repository = get()) }
    factory { SaveTokenUseCase(repository = get()) }
    factory { RefreshTokenUseCase(repository = get()) }

}