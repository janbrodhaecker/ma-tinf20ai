package com.dhbw.tinf20ai.cryptotracker.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhbw.tinf20ai.cryptotracker.R
import com.dhbw.tinf20ai.cryptotracker.api.model.Asset
import com.dhbw.tinf20ai.cryptotracker.components.ui.SearchListRecyclerView
import com.dhbw.tinf20ai.cryptotracker.components.ui.OnListRecyclerViewClickListener
import com.dhbw.tinf20ai.cryptotracker.databinding.FragmentMasterBinding
import com.dhbw.tinf20ai.cryptotracker.model.AssetSharedViewModel

class MasterFragment : Fragment(), OnListRecyclerViewClickListener {

    private val model: AssetSharedViewModel by activityViewModels()

    private lateinit var binding: FragmentMasterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMasterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.data.observe(viewLifecycleOwner, {
            requireActivity().runOnUiThread {
                val adapter = SearchListRecyclerView(requireActivity(), it, this)

                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.progressBar.visibility = View.GONE

                binding.etFilter.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        adapter.filter.filter(s.toString());
                    }
                })
            }
        })
    }

    override fun onItemClick(view: View, asset: Asset) {
        model.selectedAsset.postValue(asset)
        findNavController().navigate(R.id.action_master_to_detail)
    }
}