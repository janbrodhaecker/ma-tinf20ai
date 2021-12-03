package com.dhbw.tinf20ai.cryptotracker.components.ui

import android.view.View
import com.dhbw.tinf20ai.cryptotracker.api.model.Asset

interface OnListRecyclerViewClickListener {

    fun onItemClick(view: View, asset: Asset)
}