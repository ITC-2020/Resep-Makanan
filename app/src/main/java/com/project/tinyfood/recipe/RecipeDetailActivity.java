package com.project.tinyfood.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.tinyfood.R;
import com.project.tinyfood.database.RecipeDao;
import com.project.tinyfood.database.RecipeDatabase;
import com.project.tinyfood.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE = "extra_note";
    public static final int REQUEST_EDIT = 200;
    private TextView tvTitle, tvIngredient, tvInstruction, tvNote;
    private Recipe recipe;
    private RecipeDao recipeDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvIngredient = findViewById(R.id.ingredient);
        tvInstruction = findViewById(R.id.instructiont);
        tvNote = findViewById(R.id.note);

        recipeDao = RecipeDatabase.getInstance(this).recipeDao();

        recipe = getIntent().getParcelableExtra(EXTRA_NOTE);

        tvTitle.setText(recipe.getTitle());
        tvIngredient.setText(recipe.getIngredient());
        tvInstruction.setText(recipe.getInstruction());
        tvNote.setText(recipe.getNote());


    }
}