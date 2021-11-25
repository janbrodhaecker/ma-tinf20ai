package com.dhbw.tinf20ai.cryptotracker.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhbw.tinf20ai.cryptotracker.api.model.Asset

class AssetSharedViewModel: ViewModel() {
    val data = MutableLiveData<Array<Asset>>()
    val selectedAsset = MutableLiveData<Asset>()
}