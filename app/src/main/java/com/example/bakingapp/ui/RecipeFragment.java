package com.example.bakingapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.adapters.RecipeAdapter;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.network.GetBakingDataService;
import com.example.bakingapp.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment implements RecipeAdapter.RecipeAdapterOnclickHandler {

    private static final String TAG = "RecipeFragment";

    ArrayList<Baking> mRecipes;
    RecipeAdapter mRecipeAdapter;
    RecyclerView mRecyclerView;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_recipe, container, false);


        mRecipes = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);


        GetBakingDataService service = RetrofitInstance.getRetrofitInstance()
               .create(GetBakingDataService.class);

        Call<List<Baking>> call = service.getBakingData();

        call.enqueue(new Callback<List<Baking>>() {
            @Override
            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {

                for(int i=0;i<response.body().size();i++){

                    mRecipes.add(response.body().get(i));
                }
                initRecycleView();

            }

            @Override
            public void onFailure(Call<List<Baking>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });

        return view;

    }

    private void initRecycleView() {

        mRecipeAdapter = new RecipeAdapter(getContext(),mRecipes, this);
        mRecyclerView.setAdapter(mRecipeAdapter);
    }

    @Override
    public void onItemClick(Baking recipe) {




        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", recipe);

        Intent intent = new Intent(getActivity(),DetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);



    }
}





//    ArrayList<String> mBakingNames;
//    ArrayList<String> mBakingImages;
//mRecipeAdapter = new RecipeAdapter(getContext(),mBakingNames,mBakingImages);


//                    mBakingNames.add(response.body().get(i).getName());
//                    mBakingImages.add(response.body().get(i).getImage());

//
//        mBakingNames = new ArrayList<>();
//        mBakingImages = new ArrayList<>();




//        ArrayList<String> ingredients = new ArrayList<>();
//       for(int i=0;i<recipe.getIngredients().size();i++) {
//           ingredients.add(recipe.getIngredients().get(i).getIngredient());
//       }
//
//        //bundle.putStringArray("recipe", ingredients);
////


// Toast.makeText(getContext(),"this is " +ingredients.get(0),Toast.LENGTH_SHORT).show();
//Log.d(TAG, "onItemClick: " + mRecipes.get(0).getIngredients().get(0).getIngredient());


//        intent.putStringArrayListExtra("recipe", ingredients);

