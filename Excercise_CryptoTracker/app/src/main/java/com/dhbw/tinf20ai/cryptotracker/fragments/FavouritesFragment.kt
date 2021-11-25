package com.dhbw.tinf20ai.cryptotracker.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhbw.tinf20ai.cryptotracker.api.CoinCapApi
import com.dhbw.tinf20ai.cryptotracker.api.model.AssetPrice
import com.dhbw.tinf20ai.cryptotracker.components.SharedPreferencesProvider
import com.dhbw.tinf20ai.cryptotracker.components.ui.FavouritesListRecyclerView
import com.dhbw.tinf20ai.cryptotracker.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.GONE

        // TODO: Load all the favourites, stored in SharedPreferences (use SharedPreferencesProvider) and display them in the FavouritesListRecyclerView
    }
}