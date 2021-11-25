package com.dhbw.tinf20ai.cryptotracker.api.model

import org.json.JSONObject

class AssetPrice(val id: String, val name: String, val price: Double) {

    companion object {
        fun fromJson(jsonObject: JSONObject): AssetPrice {
            // TODO: create AssetPrice from JSONObject
            return AssetPrice("id", "name", 0.00)
        }
    }
}