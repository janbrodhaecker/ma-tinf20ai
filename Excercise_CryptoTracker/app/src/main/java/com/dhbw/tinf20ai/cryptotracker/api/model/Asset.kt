package com.dhbw.tinf20ai.cryptotracker.api.model

import org.json.JSONArray
import org.json.JSONObject

class   Asset(
    var id: String,
    var symbol: String,
    var name: String,
    var supply: Double,
    var markedCapUsd: Double,
    var volumeUsd24Hr: Double,
    var priceUsd: Double,
    var changePercent24HR: Double,
    var vwap24Hr: Double) {


    companion object {
        private fun fromJson(json: JSONObject): Asset {
            return Asset(
                json.optString("id"),
                json.optString("symbol"),
                json.optString("name"),
                json.optDouble("supply"),
                json.optDouble("markedCapUsd"),
                json.optDouble("volumeUsd24Hr"),
                json.optDouble("priceUsd"),
                json.optDouble("changePercent24HR"),
                json.optDouble("vwap24Hr"),
            )
        }

        fun fromJsonArray(jsonArray: JSONArray): Array<Asset> {
            val result = ArrayList<Asset>()
            for (idx in 0 until jsonArray.length()) {
                val tempJSONObject = jsonArray.optJSONObject(idx)
                result.add(fromJson(tempJSONObject))
            }
            return result.toTypedArray()
        }
    }

}