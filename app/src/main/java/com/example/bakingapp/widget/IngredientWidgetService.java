package com.example.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.model.ingredients.Ingredients;
import com.example.bakingapp.network.GetBakingDataService;
import com.example.bakingapp.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientWidgetService extends RemoteViewsService {

    static ArrayList<Ingredients> mIngredient;


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientWidgetItemFactory(getApplicationContext());
    }


    public static class IngredientWidgetItemFactory implements RemoteViewsFactory {

        private static final String TAG = "IngredientWidgetItemFac";

        Context mContext;
        int appWidgetId;
        ArrayList<Ingredients>mIngredient;
        ArrayList<Baking> mRecipes = new ArrayList<>();
        ArrayList<String> ingrd = new ArrayList<>();

        private String[] exampleData = {"one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten"};

        public IngredientWidgetItemFactory(Context context){

            mContext = context;
//            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                    AppWidgetManager.INVALID_APPWIDGET_ID);
           if(IngredientWidgetProvider.mRecipe!=null) {
               mIngredient = IngredientWidgetProvider.mRecipe.getIngredients();
           }

        }

        @Override
        public void onCreate() {



        }

        @Override
        public void onDataSetChanged() {

            if(IngredientWidgetProvider.mRecipe.getIngredients()!=null) {

                mIngredient = IngredientWidgetProvider.mRecipe.getIngredients();
            }else {
                mIngredient =null;
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if(mIngredient == null) return 0;
              return mIngredient.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            if(mIngredient == null || mIngredient.size() == 0) return null;
           // Bundle bundle = new Bundle();

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_widget_item);
            views.setTextViewText(R.id.ingredient_item, mIngredient.get(position).getIngredient());
            //SystemClock.sleep(500);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
