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
        return preferences(context)
            .getStringSet(FAVOURITES_KEY, mutableSetOf()) as Set<String>
    }

    fun addFavouriteAssetId(activity: Activity, assetId: String) {
        val currentFavouriteAssets = getFavouritesAssetIds(activity).toMutableSet()
        currentFavouriteAssets.add(assetId)
        with (preferences(activity).edit()) {
            putStringSet(FAVOURITES_KEY, currentFavouriteAssets)
            apply()
        }
    }

    private fun preferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
    }

    fun removeFavouriteAssetId(activity: Activity, assetId: String) {
        val currentFavouriteAssets = getFavouritesAssetIds(activity).toMutableSet()
        currentFavouriteAssets.remove(assetId)
        with (preferences(activity).edit()) {
            putStringSet(FAVOURITES_KEY, currentFavouriteAssets)
            apply()
        }
    }
}