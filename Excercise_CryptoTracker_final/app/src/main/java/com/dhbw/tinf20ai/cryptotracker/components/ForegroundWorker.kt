package com.dhbw.tinf20ai.cryptotracker.components

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.dhbw.tinf20ai.cryptotracker.R
import com.dhbw.tinf20ai.cryptotracker.api.CoinCapApi
import com.dhbw.tinf20ai.cryptotracker.utils.ExtensionMethods.formatUsdPrice
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForegroundWorker(
    var context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @SuppressLint("NewApi")
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    companion object {

        const val TAG = "ForegroundWorker"
        const val NOTIFICATION_ID = 42
        const val CHANNEL_ID = "CryptoTrackerChannel"
        const val ARG_PROGRESS = "PriceTracker"
        private const val DELAY_DURATION = 1000 * 60L // 1min
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "Start job")

        createNotificationChannel()
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Important background job")
            .build()

        val foregroundInfo = ForegroundInfo(NOTIFICATION_ID, notification)
        setForeground(foregroundInfo)

        while (true) {
            updateNotification()
            delay(DELAY_DURATION)
        }

        return Result.success()
    }

    private fun updateNotification() {
        GlobalScope.launch {
            CoinCapApi.instance.getAllFavouritePrices(context) { assetPrices ->
                val text = assetPrices.joinToString { "${it.name}\t\t${it.price.formatUsdPrice()}\n" }

                val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                    .setSmallIcon(R.drawable.bitcoin_icon)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setStyle(NotificationCompat.BigTextStyle().bigText("\n$text"))
                    .build()
                notificationManager?.notify(NOTIFICATION_ID, notification)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = notificationManager?.getNotificationChannel(CHANNEL_ID)
            if (notificationChannel == null) {
                notificationChannel = NotificationChannel(
                    CHANNEL_ID, TAG, NotificationManager.IMPORTANCE_LOW
                )
                notificationManager?.createNotificationChannel(notificationChannel)
            }
        }
    }
}