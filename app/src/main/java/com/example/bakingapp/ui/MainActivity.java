package com.example.bakingapp.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.RecipeAdapter;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.network.GetBakingDataService;
import com.example.bakingapp.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Baking mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");


    }
}














//    GetBakingDataService service = RetrofitInstance.getRetrofitInstance()
//                .create(GetBakingDataService.class);
//
//        Call<List<Baking>> call = service.getBakingData();
//
//        call.enqueue(new Callback<List<Baking>>() {
//            @Override
//            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
//                Log.d(TAG, "onResponse: "+ response.body().toString());
//                String bakings = response.body().get(0).getName();
//                String cake = response.body().get(3).getIngredients().get(1).getIngredient();
//                Log.d(TAG, "onResponse: "+ bakings + " "+ cake);
//                if(response.body().get(0).getImage().isEmpty()){
//                    Log.d(TAG, "onResponse: null");
//                    Picasso.with(MainActivity.this).load("https://www.gstatic.com/webp/gallery/1.jpg").into(imageView);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Baking>> call, Throwable t) {
//
//                Log.d(TAG, "onFailure: "+ t.getMessage());
//
//            }
//        });





//recipe fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        RecipeFragment recipeFragment = new RecipeFragment();
//
//        fragmentManager.beginTransaction().add(R.id.recipe_fragment_container,recipeFragment)
//                .commit();