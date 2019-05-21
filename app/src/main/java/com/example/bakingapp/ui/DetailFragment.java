package com.example.bakingapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.IngredientsAdapter;
import com.example.bakingapp.adapters.StepsAdapter;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.model.ingredients.Ingredients;
import com.example.bakingapp.model.steps.Steps;
import com.example.bakingapp.widget.WidgetUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements StepsAdapter.StepsAdapterOnclickHandler {

    private static final String TAG = "DetailFragment1";

    Baking mRecipe;
    ArrayList<Ingredients> mIngredient;
    ArrayList<Steps> mSteps;

    IngredientsAdapter mIngredientsAdapter;
    RecyclerView mIngredientRecyclerView;

    StepsAdapter mStepsAdapter;
    RecyclerView mStepsRecyclerView;

    Parcelable mIngredientsSavedRecyclerLayoutState;
    Parcelable mStepsSavedRecyclerLayoutState;

    private boolean mTwoPane;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Log.d(TAG, "onCreateView: asdh");


        Intent intent = getActivity().getIntent();

        mRecipe = intent.getParcelableExtra("recipe");

        mIngredient = mRecipe.getIngredients();


        WidgetUtils.updateWidgetsData(getActivity(),mRecipe);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ingredient",mIngredient);
        //IngredientWidgetService.IngredientWidgetItemFactory widgetItemFactory = new IngredientWidgetService.IngredientWidgetItemFactory(getContext(),mIngredient);

        mIngredientRecyclerView = view.findViewById(R.id.rv_ingredient);

        initIngredientRecycleView();

        mStepsRecyclerView = view.findViewById(R.id.rv_steps);

        mSteps = mRecipe.getSteps();

        initStepsRecycleView();


        return view;
    }


    private void initIngredientRecycleView() {

        mIngredientsAdapter = new IngredientsAdapter(mIngredient);
        mIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mIngredientRecyclerView.setAdapter(mIngredientsAdapter);


    }

    private void initStepsRecycleView() {
        mStepsAdapter = new StepsAdapter(getContext(), mSteps, this);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStepsRecyclerView.setAdapter(mStepsAdapter);

    }



    @Override
    public void onItemClick(int index) {
        Log.d(TAG, "onItemClick: ");
        Bundle bundle = new Bundle();

        if (getActivity().findViewById(R.id.steps_fragment_container) != null) {
            Log.d(TAG, "onItemClick: if");
            mTwoPane = true;

            bundle.putBoolean("123",mTwoPane);

            StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
            stepsDetailFragment.setArguments(bundle);
            stepsDetailFragment.setmInstructions(mSteps.get(index).getDescription());
            stepsDetailFragment.setmVideoUrl(mSteps.get(index).getVideoURL());
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.steps_fragment_container, stepsDetailFragment)
                    .commit();

        }

        else {

            Log.d(TAG, "onItemClick: else");
            mTwoPane = false;


            //bundle = new Bundle();
            //bundle.putBoolean("123",mTwoPane);
            bundle.putInt("step", index);
            bundle.putParcelableArrayList("all-steps", mSteps);

            bundle.putParcelable("one-step", mSteps.get(index));
            //bundle.putBoolean("two-pane",mTwoPane);

            Intent intent = new Intent(getActivity(), StepDetailActivity.class);
            intent.putExtras(bundle);

            startActivity(intent);
        }

        //Log.d(TAG, "onItemClick: flag " + mTwoPane);

        //Bundle bundle = new Bundle();
        //bundle.putBoolean("two-pane",mTwoPane);



    }




}


//   Bundle bundle = this.getArguments();
////
//        if (bundle != null) {
//            ingrd = bundle.getStringArrayList("recipe");
//            Log.w(TAG, "onCreateView: error" + ingrd.get(1));
//        }
//
//        else {
//            //Log.w(TAG, "onCreateView: error" + ingrd.get(0));
//        }


//
//
//       mRecipe = bundle.getParcelable("recipe");


//
// mSteps = new ArrayList<>();
//
//        for(int i=0; i<mRecipe.getSteps().size(); i++) {
//        mSteps.add(mRecipe.getSteps().get(i).getShortDescription());
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
//        R.layout.steps_item,R.id.steps, mSteps);
//        ListView listView = view.findViewById(R.id.steps_view);
//        listView.setAdapter(adapter);