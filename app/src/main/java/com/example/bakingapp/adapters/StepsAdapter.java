package com.example.bakingapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Baking;
import com.example.bakingapp.model.steps.Steps;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    ArrayList<Steps>mSteps;
    Context mContex;
    StepsAdapterOnclickHandler mHandler;

    public interface StepsAdapterOnclickHandler {
        void onItemClick(int index);

    }

    public StepsAdapter(Context context, ArrayList<Steps> steps, StepsAdapterOnclickHandler handler) {
        this.mContex = context;
        this.mSteps = steps;
        this.mHandler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.steps_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view, mHandler);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.stepsTextView.setText(mSteps.get(i).getShortDescription());

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stepsTextView;

        public ViewHolder(@NonNull View itemView, StepsAdapterOnclickHandler handler) {
            super(itemView);
            stepsTextView = itemView.findViewById(R.id.steps);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            Steps step = mSteps.get(index);
//            Bundle bundle = new Bundle();
//            bundle.putInt("index",index);
            mHandler.onItemClick(index);

        }
    }
}
