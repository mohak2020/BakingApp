package com.example.bakingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.ingredients.Ingredients;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    ArrayList<Ingredients> mIngredient;

    public IngredientsAdapter(ArrayList<Ingredients> ingredient) {
        this.mIngredient = ingredient;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.ingredientView.setText(mIngredient.get(i).getIngredient());

    }

    @Override
    public int getItemCount() {
        return mIngredient.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientView = itemView.findViewById(R.id.ingredient_view);
        }
    }

}
