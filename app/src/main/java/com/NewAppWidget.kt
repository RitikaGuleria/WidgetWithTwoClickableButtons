package com

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast
import com.example.practice.R

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider()
{

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId)


            val views = RemoteViews(context.packageName, R.layout.new_app_widget)

            // Set up click listener for button 1
            val button1Intent=Intent(context, NewAppWidget::class.java)
            button1Intent.action="Button1_Clicked"
            val button1PendingIntent=PendingIntent.getBroadcast(context,0,button1Intent,PendingIntent.FLAG_UPDATE_CURRENT)
            views.setOnClickPendingIntent(R.id.btn1,button1PendingIntent)

            // Set up click listener for button 2
            val button2Intent = Intent(context, NewAppWidget::class.java)
            button2Intent.action = "BUTTON2_CLICKED"
            val button2PendingIntent = PendingIntent.getBroadcast(context, 0, button2Intent, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setOnClickPendingIntent(R.id.btn2, button2PendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)

        //Handle button 1 click
        if(intent?.action == "Button1_Clicked"){
            Toast.makeText(context,"Button 1 is clicked",Toast.LENGTH_LONG).show()
        }
        //Handle button 2 click
        if(intent?.action == "Button2_Clicked"){
            Toast.makeText(context,"Button 2 is clicked",Toast.LENGTH_LONG).show()
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {
//    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
//    views.setTextViewText(R.id.appwidget_text, widgetText)


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

