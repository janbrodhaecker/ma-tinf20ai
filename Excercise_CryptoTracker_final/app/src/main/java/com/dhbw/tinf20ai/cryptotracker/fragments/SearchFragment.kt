package com.dhbw.tinf20ai.cryptotracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dhbw.tinf20ai.cryptotracker.api.CoinCapApi
import com.dhbw.tinf20ai.cryptotracker.databinding.FragmentSearchBinding
import com.dhbw.tinf20ai.cryptotracker.model.AssetSharedViewModel

class SearchFragment : Fragment() {

    private val model: AssetSharedViewModel by activityViewModels()

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoinCapApi.instance.getAssets({
            model.data.postValue(it)
        }, {})
    }
}