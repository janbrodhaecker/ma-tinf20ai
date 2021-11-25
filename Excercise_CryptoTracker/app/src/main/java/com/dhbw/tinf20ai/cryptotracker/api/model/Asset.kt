package com.dhbw.tinf20ai.cryptotracker.api.model

import org.json.JSONArray
import org.json.JSONObject

class Asset(
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
            // TODO: create Asset from JSONOObject
            return Asset("id", "symbol", "name", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00)
        }

        fun fromJsonArray(jsonArray: JSONArray): Array<Asset> {
            // TODO: create Array of Assets from JSONArray
            return ArrayList<Asset>().toTypedArray()
        }
    }

}