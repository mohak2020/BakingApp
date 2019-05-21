package com.example.bakingapp.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.bakingapp.model.steps.Steps;
import com.example.bakingapp.ui.StepsDetailFragment;

import java.util.ArrayList;

public class DetailStepsAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "StepsDetailFragment";

    ArrayList<Steps>mSteps;
    Steps mStepItem;
    StepsAdapter stepsAdapter;
    int mIndex;

    public DetailStepsAdapter(FragmentManager fm, ArrayList<Steps>steps, Steps step, int index) {
        super(fm);
        mSteps = steps;
        mStepItem = step;
        mIndex = index;

    }

    @Override
    public Fragment getItem(int i) {
        //int index = i+2;

        Bundle bundle = new Bundle();
         //index = bundle.getInt("index2",0);
        //i=i+mIndex;


        StepsDetailFragment stepDetailFragment = new StepsDetailFragment();


            bundle.putParcelable("step-detail", mSteps.get(i));
            stepDetailFragment.setArguments(bundle);
            mIndex = 0;

        //Log.d(TAG, "getItem: index="+ mIndex);
        Log.d(TAG, "getItem: i="+ i);


        return stepDetailFragment;
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }


}
