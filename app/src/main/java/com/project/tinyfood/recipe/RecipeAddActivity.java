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

public class RecipeAddActivity extends AppCompatActivity {
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 110;
    public static final String EXTRA_ADD = "extra_add";
    private EditText etTitle, etIngridient, etInstruction, etNote;
    private Button btnSave;
    private ImageView arrow;
    private RecipeDao recipeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Save");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recipeDao = RecipeDatabase.getInstance(this).recipeDao();

        etTitle = findViewById(R.id.et_title);
        etIngridient = findViewById(R.id.et_ingredient);
        etInstruction = findViewById(R.id.et_instruction);
        etNote = findViewById(R.id.et_note);
        btnSave = findViewById(R.id.btn_save);
        arrow = findViewById(R.id.iv_backarrow);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String ingridient = etIngridient.getText().toString();
                String instruction = etInstruction.getText().toString();
                String note = etNote.getText().toString();


                Recipe recipe = new Recipe(title, ingridient, instruction, note);
                recipeDao.insertData(recipe);

                setResult(RESULT_ADD);
                finish();
            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeAddActivity.this, RecipesFragment.class);
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
        message = "Do you want to cancel adding recipes?";

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