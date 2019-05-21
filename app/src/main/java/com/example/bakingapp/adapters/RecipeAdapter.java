package com.example.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.network.GetBakingDataService;
import com.example.bakingapp.network.RetrofitInstance;
import com.example.bakingapp.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private static final String TAG = "MainActivity";

    ArrayList<Baking> mRecipes;
    Context mContex;
    RecipeAdapterOnclickHandler mHandler;

    public interface RecipeAdapterOnclickHandler {
        void onItemClick(Baking recipe);

    }


    public RecipeAdapter(Context context, ArrayList<Baking> recipes, RecipeAdapterOnclickHandler handler) {
        mContex = context;
        mRecipes = recipes;
        mHandler = handler;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view, mHandler);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.recipeName.setText(mRecipes.get(i).getName());


        if (mRecipes.get(i).getImage().isEmpty()) {
            viewHolder.recipeView.setImageResource(R.drawable.cakes);
        } else {
            Picasso.with(mContex).load(mRecipes.get(i).getImage()).into(viewHolder.recipeView);
        }

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView recipeView;
        TextView recipeName;

        public ViewHolder(@NonNull View itemView, RecipeAdapterOnclickHandler handler) {
            super(itemView);
            recipeView = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            Baking recipe = mRecipes.get(index);
            mHandler.onItemClick(recipe);

        }
    }
}













//        if(mBakingImages.get(i).isEmpty()){
//            viewHolder.recipeView.setImageResource(R.drawable.cakes);
//        }else {
//            Picasso.with(mContex).load(mBakingImages.get(i)).into(viewHolder.recipeView);
//        }


// public RecipeAdapter(Context context,ArrayList<String> bakingNames, ArrayList<String>bakingImages ) {
//        mContex = context;
//        mBakingNames = bakingNames;
//        mBakingImages= bakingImages;
//    }





//Picasso.with(mContex).load("https://www.gstatic.com/webp/gallery/1.jpg").into(viewHolder.recipeView);

//viewHolder.recipeName.setText(mBakingNames.get(i));


//    ArrayList<String> mBakingNames;
//    ArrayList<String> mBakingImages;