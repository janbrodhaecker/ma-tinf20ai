package com.dhbw.tinf20ai.cryptotracker.api.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.time.Instant

class AssetPriceHistory(val priceUsd: Double, val time: Instant) {

    companion object {

        private fun fromJson(json: JSONObject): AssetPriceHistory {
            // TODO: create AssetPriceHistory from JSONObject
            return AssetPriceHistory(0.00, Instant.now())
        }

        fun fromJsonArray(jsonArray: JSONArray): Array<AssetPriceHistory> {
            // TODO create Array of AssetPriceHistory from JSONObject
            return emptyList<AssetPriceHistory>().toTypedArray()
        }
    }

}