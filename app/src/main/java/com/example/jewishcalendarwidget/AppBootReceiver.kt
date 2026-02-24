package com.example.jewishcalendarwidget

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent

class AppBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_PACKAGE_REPLACED,
            "android.intent.action.QUICKBOOT_POWERON" -> {
                // Trigger widget update on boot (no service needed - TIME_TICK handles ongoing updates)
                val appWidgetManager = AppWidgetManager.getInstance(context)
                val componentName = ComponentName(context, JewishCalendarWidget::class.java)
                val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

                if (appWidgetIds.isNotEmpty()) {
                    val updateIntent = Intent(context, JewishCalendarWidget::class.java)
                    updateIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
                    context.sendBroadcast(updateIntent)
                }
            }
        }
    }
}
