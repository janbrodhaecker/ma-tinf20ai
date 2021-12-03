package com.dhbw.tinf20ai.cryptotracker.utils

import java.text.DecimalFormat

object ExtensionMethods {

    fun Double.formatUsdPrice(): String = "${DecimalFormat("#.##").format(this)}$"

}