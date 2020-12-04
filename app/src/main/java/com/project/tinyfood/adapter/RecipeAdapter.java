package com.project.tinyfood.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.tinyfood.R;
import com.project.tinyfood.model.Recipe;
import com.project.tinyfood.recipe.RecipeDetailActivity;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Activity activity;

    public void setListNotes(ArrayList<Recipe> mRecipes) {
        this.mRecipes.clear();
        this.mRecipes.addAll(mRecipes);
        notifyDataSetChanged();
    }

    public RecipeAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cetak_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);

        }

        public void bind(final Recipe data) {
            Log.d("projectitc", data.getIngredient());
            tvTitle.setText(data.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, RecipeDetailActivity.class);
                    intent.putExtra(RecipeDetailActivity.EXTRA_NOTE, data);
                    activity.startActivityForResult(intent, RecipeDetailActivity.REQUEST_EDIT);
                }
            });
        }
    }
}
