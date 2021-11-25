package com.dhbw.tinf20ai.cryptotracker.components.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhbw.tinf20ai.cryptotracker.R
import com.dhbw.tinf20ai.cryptotracker.api.model.AssetPrice
import com.dhbw.tinf20ai.cryptotracker.utils.ExtensionMethods
import com.dhbw.tinf20ai.cryptotracker.utils.ExtensionMethods.formatUsdPrice

class FavouritesListRecyclerView(private val assetPrices: List<AssetPrice>):
    RecyclerView.Adapter<FavouritesListRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): FavouritesListRecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.favourite_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // TODO: create a ViewHolder for the given view and initialize all the fields from favourite_row_item.xml
    }

    override fun onBindViewHolder(holder: FavouritesListRecyclerView.ViewHolder, position: Int) {
        // TODO: initialize all fields from the ViewHolder
    }

    override fun getItemCount() = assetPrices.size
}

