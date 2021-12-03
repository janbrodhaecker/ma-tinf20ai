package com.dhbw.tinf20ai.cryptotracker.components.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.dhbw.tinf20ai.cryptotracker.R
import com.dhbw.tinf20ai.cryptotracker.api.model.Asset
import com.dhbw.tinf20ai.cryptotracker.components.SharedPreferencesProvider

class SearchListRecyclerView(
    private val activity: Activity,
    private var assets: Array<Asset>,
    private val itemClickListener: OnListRecyclerViewClickListener?) :
    RecyclerView.Adapter<SearchListRecyclerView.ViewHolder>(), Filterable {

    private val originalAssets: Array<Asset>

    init {
        originalAssets = assets
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_name)
        val favouritesBtn: ImageView = view.findViewById(R.id.iv_favourites)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.search_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = assets[position].name
        viewHolder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(it, assets[position])
        }

        val assetId = assets[position].id
        setFavouritesButtonEnabled(containsAssetId(assetId), viewHolder)

        viewHolder.favouritesBtn.setOnClickListener {
            val isEnabled = containsAssetId(assetId)
            if (!isEnabled) {
                SharedPreferencesProvider.instance.addFavouriteAssetId(activity, assetId)
            } else {
                SharedPreferencesProvider.instance.removeFavouriteAssetId(activity, assetId)
            }
            setFavouritesButtonEnabled(!isEnabled, viewHolder)
        }
    }

    override fun getItemCount() = assets.size

    override fun getFilter(): Filter = object: Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()

            val filteredAssets = if (constraint.isNullOrEmpty())
                originalAssets.toList()
            else
                originalAssets.filter { it.name.contains(constraint, true) }

            filterResults.count = filteredAssets.size
            filterResults.values = filteredAssets
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            assets = (results?.values as ArrayList<Asset>).toTypedArray()
            notifyDataSetChanged()
        }
    }

    private fun containsAssetId(assetId: String): Boolean {
        return SharedPreferencesProvider.instance.getFavouritesAssetIds(activity).contains(assetId);
    }

    private fun setFavouritesButtonEnabled(enabled: Boolean, viewHolder: ViewHolder) {
        val color = if (enabled)
            getColor(activity, R.color.yellow) else getColor(activity, R.color.grey);
        viewHolder.favouritesBtn.setColorFilter(color)
    }

}