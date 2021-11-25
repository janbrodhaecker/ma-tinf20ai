package com.dhbw.tinf20ai.cryptotracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.dhbw.tinf20ai.cryptotracker.R
import com.dhbw.tinf20ai.cryptotracker.components.ForegroundWorker
import com.dhbw.tinf20ai.cryptotracker.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private val foregroundWorkerName = "CryptoPriceTracker!"

    private lateinit var binding: FragmentHomeBinding
    private lateinit var workManager: WorkManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnStartService.setOnClickListener { startForegroundWorker() }
        binding.btnStopService.setOnClickListener { stopForegroundWorker() }

        workManager = WorkManager.getInstance(requireContext())
        return binding.root
    }

    private fun startForegroundWorker() {
        // TODO (advanced): enable the ForegroundWorker to show the prices of the favourite assets in the notification
    }

    private fun stopForegroundWorker() {
        // TODO (advanced): stop the ForegroundWorker
    }
}