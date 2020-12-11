package com.project.tinyfood.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.tinyfood.R;
import com.project.tinyfood.RecipesFragment;
import com.project.tinyfood.database.RecipeDao;
import com.project.tinyfood.database.RecipeDatabase;
import com.project.tinyfood.model.Recipe;

public class RecipeEditActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE = "extra_note";
    public static final int REQUEST_EDIT = 200;
    public static final int RESULT_EDIT = 210;

    private EditText Title, Ingredient, Instruction, Note;
    private Button btnSave;
    private Recipe recipe;
    private RecipeDao recipeDao;
    private ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_edit);

        recipeDao = RecipeDatabase.getInstance(this).recipeDao();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recipe = getIntent().getParcelableExtra(EXTRA_NOTE);

        Title = findViewById(R.id.ed_title);
        Ingredient = findViewById(R.id.ed_ingredient);
        Instruction = findViewById(R.id.ed_instruction);
        Note =  findViewById(R.id.ed_note);
        btnSave = findViewById(R.id.btnsave);
        arrow = findViewById(R.id.iv_backarrow);

        Title.setText(recipe.getTitle());
        Ingredient.setText(recipe.getIngredient());
        Instruction.setText(recipe.getInstruction());
        Note.setText(recipe.getNote());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = Title.getText().toString();
                String ingredient = Ingredient.getText().toString();
                String instruction = Instruction.getText().toString();
                String note = Note.getText().toString();

                Recipe updateRecipe = new Recipe(recipe.getId(), title, ingredient, instruction, note);

                recipeDao.updateData(updateRecipe);

                setResult(RESULT_EDIT);
                finish();

                Intent intent = new Intent(RecipeEditActivity.this, RecipesFragment.class);
                startActivity(intent);
            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeEditActivity.this, RecipeDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            showDialogMessage();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showDialogMessage();
    }

    private void showDialogMessage() {
        String message;
        String title;

        title = "Cancel";
        message = "Do you want to cancel editing recipes?";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }
}