package com.example.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidgetProvider extends AppWidgetProvider {

    public static Baking mRecipe;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, Baking recipe,
                                int[] appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        //views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_provider);
        views.setOnClickPendingIntent(R.id.btn, pendingIntent);

        Intent serviceIntent = new Intent(context, IngredientWidgetService.class);
        //mRecipe = recipe;
        if (recipe == null) {
            // If there is a previous recipe stored in memory
            if (mRecipe != null) {
                views.setRemoteAdapter(R.id.lv_widget, serviceIntent);
                views.setEmptyView(R.id.lv_widget, R.id.empty_view);

                // Else, if there is no previous recipe selected
            } else {
                views.setEmptyView(R.id.lv_widget, R.id.empty_view);
            }
            // Else, if it's an update to an existing widget
        } else {
            mRecipe = recipe;
            views.setRemoteAdapter(R.id.lv_widget, serviceIntent);
            views.setEmptyView(R.id.lv_widget, R.id.empty_view);
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_provider);

            Intent intent = new Intent(context, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.btn, pendingIntent);


            Intent serviceIntent = new Intent(context, IngredientWidgetService.class);

            views.setRemoteAdapter(R.id.lv_widget, serviceIntent);
            views.setEmptyView(R.id.lv_widget, R.id.empty_view);



            appWidgetManager.updateAppWidget(appWidgetId, views);

        }

        updateAppWidget(context, appWidgetManager, null, appWidgetIds);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

