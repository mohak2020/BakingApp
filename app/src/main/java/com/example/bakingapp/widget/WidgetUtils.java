package com.example.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Baking;

public class WidgetUtils {

    public static void updateWidgetsData(Context context, Baking recipe) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, IngredientWidgetProvider.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_widget);

        IngredientWidgetProvider.updateAppWidget(context, appWidgetManager, recipe, appWidgetIds);



    }
}
