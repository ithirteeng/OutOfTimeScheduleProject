package com.example.sheduleproject.data.common.storage

import android.content.Context

class UserStorage(context: Context) {

    companion object {
        const val USER_STORAGE_NAME = "user storage name"
        const val USER_AUTHORIZATION_FLAG_KEY = "have user been authorized"
    }

    private val sharedPreferences =
        context.getSharedPreferences(USER_STORAGE_NAME, Context.MODE_PRIVATE)

    fun setUserAuthorizationFlag(ifUserPassedRegistration: Boolean) {
        sharedPreferences.edit()
            .putBoolean(USER_AUTHORIZATION_FLAG_KEY, ifUserPassedRegistration)
            .apply()
    }

    fun checkIfUserWasAuthorized(): Boolean {
        return sharedPreferences.getBoolean(USER_AUTHORIZATION_FLAG_KEY, false)
    }
}