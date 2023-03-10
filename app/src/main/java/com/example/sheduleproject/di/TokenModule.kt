package com.example.sheduleproject.di

import com.example.sheduleproject.data.token.datasource.LocalTokenDatasource
import com.example.sheduleproject.data.token.datasource.LocalTokenDatasourceImpl
import com.example.sheduleproject.data.token.datasource.RemoteTokenDatasource
import com.example.sheduleproject.data.token.datasource.RemoteTokenDatasourceImpl
import com.example.sheduleproject.data.token.repository.TokenRepositoryImpl
import com.example.sheduleproject.data.token.storage.TokenStorage
import com.example.sheduleproject.domain.token.repository.TokenRepository
import com.example.sheduleproject.domain.token.usecase.GetTokenFromLocalStorageUseCase
import com.example.sheduleproject.domain.token.usecase.RefreshTokenUseCase
import com.example.sheduleproject.domain.token.usecase.SaveTokenToLocalStorageUseCase
import org.koin.dsl.module

val tokenModule = module {
    factory { TokenStorage(context = get()) }
    factory<LocalTokenDatasource> { LocalTokenDatasourceImpl(storage = get()) }
    factory<RemoteTokenDatasource> { RemoteTokenDatasourceImpl(api = get()) }

    factory<TokenRepository> {
        TokenRepositoryImpl(
            localTokenDatasource = get(),
            remoteTokenDatasource = get()
        )
    }

    factory { GetTokenFromLocalStorageUseCase(repository = get()) }
    factory { SaveTokenToLocalStorageUseCase(repository = get()) }
    factory { RefreshTokenUseCase(repository = get()) }
}