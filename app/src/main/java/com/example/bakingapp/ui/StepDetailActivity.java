package com.example.bakingapp.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.DetailStepsAdapter;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.model.steps.Steps;

import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity {

    private static final String TAG = "StepsDetailFragment";

    ViewPager viewPager;
    DetailStepsAdapter adapter;
    Baking mRecipe;

    ArrayList<Steps> mSteps;
    Steps mStepItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);


        Intent intent = getIntent();
        mSteps = intent.getParcelableArrayListExtra("all-steps");
        //mStepItem = intent.getParcelableExtra("step");
        int index =  intent.getIntExtra("step",0);
        Bundle bundle = new Bundle();
        bundle.putInt("index2",index);
        boolean flag = intent.getBooleanExtra("two-pane",false);
        Log.d(TAG, "onCreate: " + index);
        Log.d(TAG, "onCreate: flag " + flag);
        bundle.putBoolean("flag",flag);

//        StepsDetailFragment stepsDetailFragment1 = new StepsDetailFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        fragmentManager.beginTransaction().add(R.id.steps_fragment_container, stepsDetailFragment1)
//                .commit();

        viewPager = findViewById(R.id.pager);

        adapter = new DetailStepsAdapter(getSupportFragmentManager(),mSteps,mStepItem, index);


        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(index);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//                //StepsDetailFragment.stopPlayer();
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//
//            }
//        });





        //mRecipe =



        Button nextBtn = (Button)findViewById(R.id.next_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(getItem(+1), true);
            }
        });

        Button prevBtn = (Button)findViewById(R.id.previous_btn);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(getItem(-1), true);
            }
        });



    }

    private int getItem(int i) {
        return viewPager.getCurrentItem()+i ;
    }
}
