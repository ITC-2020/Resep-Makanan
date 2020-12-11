package com.project.tinyfood.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.tinyfood.R;
import com.project.tinyfood.RecipesFragment;
import com.project.tinyfood.database.RecipeDao;
import com.project.tinyfood.database.RecipeDatabase;
import com.project.tinyfood.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE = "extra_note";
    public static final int REQUEST_DELETE = 200;
    public static final int RESULT_EDIT = 210;
    public static final int RESULT_DELETE = 220;
    public static final int ALERT_DIALOG_CLOSE = 10;
    public static final int ALERT_DIALOG_DELETE = 20;
    private TextView tvTitle, tvIngredient, tvInstruction, tvNote;
    private Recipe recipe;
    private RecipeDao recipeDao;
    private ImageView delete, edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvIngredient = findViewById(R.id.ingredient);
        tvInstruction = findViewById(R.id.instructiont);
        tvNote = findViewById(R.id.note);
        delete = findViewById(R.id.delete);
        edit = findViewById(R.id.edit);

        recipeDao = RecipeDatabase.getInstance(this).recipeDao();

        recipe = getIntent().getParcelableExtra(EXTRA_NOTE);

        tvTitle.setText(recipe.getTitle());
        tvIngredient.setText(recipe.getIngredient());
        tvInstruction.setText(recipe.getInstruction());
        tvNote.setText(recipe.getNote());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMessage(ALERT_DIALOG_DELETE);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeDetailActivity.this, RecipeEditActivity.class);
                intent.putExtra(RecipeEditActivity.EXTRA_NOTE,recipe);
                startActivityForResult(intent, RecipeEditActivity.REQUEST_EDIT);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            showDialogMessage(ALERT_DIALOG_DELETE);
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialogMessage(int type) {
        final boolean isDialogClose = (type == ALERT_DIALOG_CLOSE);
        String message;
        String title;

        if (isDialogClose) {
            title = "Back";
            message = "Do you want to go back?";
        } else {
            title = "Delete";
            message = "Are you sure you want to delete notes?";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            recipeDao.deleteData(recipe);
                            setResult(RESULT_DELETE);
                            finish();
                        }
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

    @Override
    public void onBackPressed() {
        showDialogMessage(ALERT_DIALOG_CLOSE);
    }
}