package com.dhbw.tinf20ai.uebung6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.BatteryManager
import android.util.Log

class BatteryBroadcastReceiver(val callback: Callback?) : BroadcastReceiver() {

    interface Callback {
        fun batteryValueChanged(value: Int)
        fun onConnectionChanged(isConnected: Boolean)
        fun onBatteryLow(isBatteryLow: Boolean)
    }


    override fun onReceive(context: Context, intent: Intent) {
        Log.d(BatteryBroadcastReceiver::class.simpleName, "Received Broadcast!")
        when {
            ACTION_BATTERY_CHANGED == intent.action -> {
                callback?.batteryValueChanged(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1))
            }

            ACTION_POWER_CONNECTED == intent.action -> {
                callback?.onConnectionChanged(true)
            }

            ACTION_POWER_DISCONNECTED == intent.action -> {
                callback?.onConnectionChanged(false)
            }

            ACTION_BATTERY_LOW == intent.action -> {
                callback?.onBatteryLow(true)
            }

            ACTION_BATTERY_OKAY == intent.action -> {
                callback?.onBatteryLow(false)
            }
        }
    }
}