package com.example.sheduleproject.data.token.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.sheduleproject.data.common.mapper.model.TokenModel

class TokenStorage(context: Context) {
    companion object {
        const val TOKEN_STORAGE_NAME = "token storage name"
        const val ACCESS_TOKEN_KEY = "access token"
        const val REFRESH_TOKEN_KEY = "access token"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        TOKEN_STORAGE_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokens(tokenModel: TokenModel) {
        sharedPreferences.edit()
            .putString(ACCESS_TOKEN_KEY, tokenModel.accessToken)
            .putString(REFRESH_TOKEN_KEY, tokenModel.refreshToken)
            .apply()
    }

    fun getTokens(): TokenModel {
        return TokenModel(
            accessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null),
            refreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
        )
    }

    fun clearTokens() {
        sharedPreferences.edit()
            .remove(ACCESS_TOKEN_KEY)
            .remove(REFRESH_TOKEN_KEY)
            .apply()
    }
}