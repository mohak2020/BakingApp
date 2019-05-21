package com.example.bakingapp.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.R;

public class DetailActivity extends AppCompatActivity  {

    private static final String TAG = "DetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        DetailFragment detailFragment = new DetailFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.detail_fragment_container, detailFragment)
                .commit();


//        if (findViewById(R.id.steps_fragment_container) != null) {
//            Log.d(TAG, "onItemClick: if");
//
//
//            StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
//            stepsDetailFragment.setmInstructions("123");
//            stepsDetailFragment.setmVideoUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
//            fragmentManager.beginTransaction().add(R.id.steps_fragment_container, stepsDetailFragment)
//                    .commit();
//
//        }


    }


}











//    Baking mRecipe;
//    Ingredients ingredients;
//    ArrayList<String> ingrd;




//Baking recipe = getIntent().getParcelableExtra("recipe");

//   Bundle bundle = getIntent().getExtras().getBundle("recipe");
//        mRecipe = bundle.getParcelable("recipe");

//       ingrd = intent.getStringArrayListExtra("recipe");
//
//Log.d(TAG, "onCreate: "+ recipe.getIngredients().get(0).getIngredient());
//
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList("recipe",ingrd);


//        detailFragment.setArguments(bundle);