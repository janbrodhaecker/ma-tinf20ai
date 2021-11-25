package com.dhbw.tinf20ai.cryptotracker.components

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesProvider {

    companion object {
        private const val SHARED_PREFS_KEY: String = "sharedPrefs"
        private const val FAVOURITES_KEY: String = "favourites"

        val instance = SharedPreferencesProvider()
    }

    fun getFavouritesAssetIds(context: Context): Set<String> {
        // TODO: read all shared preferences under FAVOURITES_KEY
        return setOf()
    }

    fun addFavouriteAssetId(activity: Activity, assetId: String) {
        // TODO: add an favourite asset to the shared preferences under FAVOURITES_KEY
    }

    fun removeFavouriteAssetId(activity: Activity, assetId: String) {
        // TODO: remove an favourite asset to the shared preferences under FAVOURITES_KEY
    }

    private fun preferences(context: Context): SharedPreferences {
        // TODO: return an SharedPreferences object by the context and the key SHARED_PREFS_KEY
        throw IllegalAccessException("Not yet implemented")
    }


}