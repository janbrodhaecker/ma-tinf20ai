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

    // Hint: for all methods, the method performGetRequest can be reused!

    fun getAssets(
        successCallback: (Array<Asset>) -> Unit,
        errorCallback: (errorMessage: String?) -> Unit
    ) {
        // TODO: call https://api.coincap.io/v2/assets to get all Crypto-Assets, invoke the successCallback with the serialized values
        performGetRequest("assets",
            {
                val jsonResponse = JSONObject(it.string())
                val dataJsonArray = jsonResponse.getJSONArray("data")
                val assetsArray = Asset.fromJsonArray(dataJsonArray)
                successCallback.invoke(assetsArray)
            }, {
                // Optional: handle the error case
                errorCallback.invoke(it)
            })
    }

    fun getAssetHistory(
        assetId: String,
        successCallback: (Array<AssetPriceHistory>) -> Unit,
        errorCallback: (errorMessage: String?) -> Unit
    ) {
        // TODO: call https://api.coincap.io/v2/assets/{{assetId}}/history to get all the price histories, invoke the successCallback with the serialized values
        // Optional: handle the error case
    }

    fun getAssetPrice(
        assetId: String,
        successCallback: (AssetPrice) -> Unit,
        errorCallback: (errorMessage: String?) -> Unit
    ) {
        // TODO: call https://api.coincap.io/v2/assets/{{assetId}} to get information about the asset, invoke the successCallback with the serialized values
        // Optional: handle the error case
    }

    suspend fun getAllFavouritePrices(
        context: Context,
        favouriteAssetIds: Set<String>,
        successCallback: (ArrayList<AssetPrice>) -> Unit,
        errorCallback: (errorMessage: String?) -> Unit
    ) {
        // TODO (Advanced): call https://api.coincap.io/v2/assets/{{assetId}} for all assetIds (multiple/parallel api calls) and invoke the success callback
        // Hint: the method getAssetPriceHistory can be reused with a so called flow
        // Optional: handle the error
    }

    private fun performGetRequest(
        apiPath: String,
        successCallback: (responseBody: ResponseBody) -> Unit,
        errorCallback: (errorMessage: String?) -> Unit
    ) {

        val request: Request = Request.Builder()
            .url(ENDPOINT + apiPath)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorCallback.invoke(e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let {
                        successCallback(it)
                    } ?:run {
                        errorCallback("Response Body was empty!")
                    }
                } else {
                    errorCallback.invoke("Response was not successful!")
                }
            }
        })
    }

    private suspend fun getAssetPriceHistory(assetId: String): AssetPrice =
        suspendCoroutine { cont ->
            getAssetPrice(assetId, { cont.resume(it) }, {/* TODO */ })
        }
}