package com.project.tinyfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.project.tinyfood.adapter.RecipeAdapter;
import com.project.tinyfood.database.RecipeDao;
import com.project.tinyfood.database.RecipeDatabase;
import com.project.tinyfood.model.Recipe;
import com.project.tinyfood.recipe.RecipeAddActivity;
import com.project.tinyfood.recipe.RecipeDetailActivity;
import com.project.tinyfood.recipe.RecipeEditActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RecipesFragment extends Fragment implements View.OnClickListener{

    public RecipesFragment() {
        // Required empty public constructor
    }

    Button Create;
    private RecyclerView rvRecipes;
    RelativeLayout rL;
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private RecipeAdapter recipeAdapter;
    private Context context;
    private RecipeDao recipeDao;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rL = view.findViewById(R.id.rL);
        recipeDao = RecipeDatabase.getInstance(getContext()).recipeDao();

        recipeAdapter = new RecipeAdapter((Activity) getContext());
        rvRecipes = view.findViewById(R.id.rv_recipe);
        rvRecipes.setHasFixedSize(true);
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecipes.setAdapter(recipeAdapter);
        loadData();


        Create = view.findViewById(R.id.btn_create);

        Create.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), RecipeAddActivity.class);
        startActivityForResult(intent, RecipeAddActivity.REQUEST_ADD);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RecipeAddActivity.REQUEST_ADD) {
            if (resultCode == RecipeAddActivity.RESULT_ADD) {
                loadData();
                rL.setVisibility(View.GONE);
                showSnackbar("Data Added Successfully");
            }
        }
        else if (requestCode == RecipeDetailActivity.RESULT_DELETE) {
            if (resultCode == RecipeDetailActivity.RESULT_DELETE) {
                loadData();
                showSnackbar("Data Deleted Successfully");
            }
        }
        else if (requestCode == RecipeEditActivity.REQUEST_EDIT) {
            if (resultCode == RecipeEditActivity.RESULT_EDIT) {
                loadData();
                showSnackbar("Data Edited Successfully");
            }
        }
    }

    void loadData() {
        List<Recipe> data = recipeDao.getAllData();
        mRecipes.clear();
        mRecipes.addAll(data);
        recipeAdapter.setListNotes(mRecipes);
        if (data.size() == 0) {
            rL.setVisibility(View.VISIBLE);
            showSnackbar("No Data");
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(rvRecipes, message, Snackbar.LENGTH_LONG).show();
    }
}