package com.example.quizprojectapp.onBoarding.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Preferences @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        "pr",
        Context.MODE_PRIVATE
    )

    fun isBoardingShow(): Boolean {
        return sharedPref.getBoolean("boarding_show", false)
    }

    fun setBoardingShow(isShown: Boolean) {
        sharedPref.edit().putBoolean("boarding_show", isShown).apply()
    }
}