package com.dhbw.tinf20ai.cryptotracker.api.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.time.Instant

class AssetPriceHistory(val priceUsd: Double, val time: Instant) {

    companion object {

        @SuppressLint("NewApi")
        private fun fromJson(json: JSONObject): AssetPriceHistory {
            val instantString = json.optLong("time")
            return AssetPriceHistory(json.optDouble("priceUsd"), Instant.ofEpochMilli(instantString))
        }

        fun fromJsonArray(jsonArray: JSONArray): Array<AssetPriceHistory> {
            val prices = ArrayList<AssetPriceHistory>()
            for (i in 0 until jsonArray.length()) {
                prices.add(fromJson(jsonArray.getJSONObject(i)))
            }
            return prices.toTypedArray()
        }
    }

}