package com.dhbw.tinf20ai.cryptotracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.dhbw.tinf20ai.cryptotracker.components.ForegroundWorker
import com.dhbw.tinf20ai.cryptotracker.components.ForegroundWorker.Companion.ARG_PROGRESS
import com.dhbw.tinf20ai.cryptotracker.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val navController = findNavController(R.id.nav_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        setContentView(binding.root)
    }
}