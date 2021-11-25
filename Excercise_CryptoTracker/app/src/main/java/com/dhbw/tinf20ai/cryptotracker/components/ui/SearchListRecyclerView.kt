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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.search_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // TODO: create a ViewHolder for the given view and initialize all the fields from search_row_item.xml
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // TODO: initialize all fields from the ViewHolder
        // TODO: when the user clicks on the item, it should invoke the call back itemClickListener
        // TODO: when the user clicks on the star, the asset should be added as a favourite
        // TODO: a favourite should be marked with a yellow star - non favourites should be marked with a grey star
    }

    override fun getItemCount() = assets.size

    override fun getFilter(): Filter = object: Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            // TODO: filter the given originalAssets by the name, with the given constraint
            return FilterResults()
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            assets = (results?.values as ArrayList<Asset>).toTypedArray()
            notifyDataSetChanged()
        }
    }

}