package com.dhbw.tinf20ai.cryptotracker.api

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.dhbw.tinf20ai.cryptotracker.api.model.Asset
import com.dhbw.tinf20ai.cryptotracker.api.model.AssetPrice
import com.dhbw.tinf20ai.cryptotracker.api.model.AssetPriceHistory
import com.dhbw.tinf20ai.cryptotracker.components.SharedPreferencesProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CoinCapApi {

    companion object {
        private const val ENDPOINT = "https://api.coincap.io/v2/"

        val instance = CoinCapApi()
    }

    private val okHttpClient = OkHttpClient()

    fun getAssets(successCallback: (Array<Asset>) -> Unit, errorCallback: (errorMessage: String?) -> Unit) {
        performGetRequest("assets", {
            try {
                val responseJson = JSONObject(it.string())
                val dataJson = responseJson.getJSONArray("data")
                val assets = Asset.fromJsonArray(dataJson)
                successCallback.invoke(assets)
            } catch (ex: Exception) {
                errorCallback.invoke(ex.toString())
            }
        }, { errorCallback.invoke(it) })
    }

    fun getAssetHistory(assetId: String, successCallback: (Array<AssetPriceHistory>) -> Unit, errorCallback: (errorMessage: String?) -> Unit) {
        performGetRequest("assets/$assetId/history?interval=d1", {
            val responseJson = JSONObject(it.string())
            val dataJson = responseJson.getJSONArray("data")
            val prices = AssetPriceHistory.fromJsonArray(dataJson)
            successCallback.invoke(prices)
        }, { errorCallback.invoke(it) })
    }

    fun getAssetPrice(assetId: String, successCallback: (AssetPrice) -> Unit, errorCallback: (errorMessage: String?) -> Unit) {
        performGetRequest("assets/$assetId", {
            val responseJson = JSONObject(it.string())
            val dataJson = responseJson.getJSONObject("data")
            val assetPrice = AssetPrice.fromJson(dataJson)
            successCallback.invoke(assetPrice)
        }, {
            errorCallback.invoke(it)
        })
    }

    private fun performGetRequest(
        apiPath: String,
        successCallback: (responseBody: ResponseBody) -> Unit,
        errorCallback: (errorMessage: String?) -> Unit) {

        val request: Request = Request.Builder()
            .url(ENDPOINT + apiPath)
            .build()

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorCallback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let {
                        successCallback(it)
                    } ?: run {
                        errorCallback("Respone seems to have no body ...")
                    }
                } else {
                    errorCallback("Request was not successful: " + response.body?.string())
                }
            }
        })
    }

    private suspend fun getAssetPriceHistory(assetId: String): AssetPrice = suspendCoroutine { cont ->
        getAssetPrice(assetId, { cont.resume(it) }, {/* TODO */})
    }

    suspend fun getAllFavouritePrices(context: Context, callback: (ArrayList<AssetPrice>) -> Unit) {
        val favouriteAssetIds =
            SharedPreferencesProvider.instance.getFavouritesAssetIds(context)

        val result = ArrayList<AssetPrice>()
        flow {
            favouriteAssetIds
                .map { getAssetPriceHistory(it) }
                .forEach { emit(it) }
        }.toList(result)
        callback.invoke(result)
    }
}