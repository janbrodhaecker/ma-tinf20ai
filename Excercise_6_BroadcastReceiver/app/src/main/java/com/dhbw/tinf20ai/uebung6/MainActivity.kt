package com.dhbw.tinf20ai.uebung6

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ObservableField
import com.dhbw.tinf20ai.uebung6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BatteryBroadcastReceiver.Callback {

    private lateinit var broadcastReceiver: BatteryBroadcastReceiver

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.model = BatteryViewModel(ObservableField(-1), ObservableField(R.color.black), ObservableField(false), ObservableField(false))
        setContentView(binding.root)

        broadcastReceiver = BatteryBroadcastReceiver(this)
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    override fun batteryValueChanged(value: Int) {
        binding.model?.batteryStatus?.set(value)

        val color = when {
            value <= 20 -> getColor(R.color.red)
            value >= 50 -> getColor(R.color.green)
            else -> getColor(R.color.black)
        }
        binding.model?.color?.set(color)
    }

    override fun onConnectionChanged(isConnected: Boolean) {
        binding.model?.connected?.set(isConnected)
    }

    override fun onBatteryLow(isBatteryLow: Boolean) {
        binding.model?.batteryLow?.set(isBatteryLow)
    }
}