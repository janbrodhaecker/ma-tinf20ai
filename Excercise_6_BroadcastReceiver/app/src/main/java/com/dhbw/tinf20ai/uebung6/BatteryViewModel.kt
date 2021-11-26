package com.dhbw.tinf20ai.uebung6

import androidx.databinding.ObservableField

data class BatteryViewModel(val batteryStatus: ObservableField<Int>, val color: ObservableField<Int>, val connected: ObservableField<Boolean>, val batteryLow: ObservableField<Boolean>)
