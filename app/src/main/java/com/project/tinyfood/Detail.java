package com.project.tinyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Detail extends AppCompatActivity {

    TextView[] text = new TextView[3];
    int[] textID = {R.id.tv_nama, R.id.tv_categories ,R.id.tv_step};
    ImageView imageView;
    int imageid;
    ImageButton imageButton;
    String[] data = new String[3];
    boolean isFav = false;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageButton = findViewById(R.id.iv_addfav);

        imageid = getIntent().getIntExtra("image", 0);
        imageView = findViewById(R.id.iv_foto);
        imageView.setImageDrawable(getResources().getDrawable(imageid));

        data[0] = getIntent().getStringExtra("nama");
        data[1] = getIntent().getStringExtra("category");
        data[2] = getIntent().getStringExtra("resep");

        for (char i = 0; i < text.length; i++) {
            text[i] = new TextView(this);
            text[i] = findViewById(textID[i]);
            text[i].setText(data[i]);
        }
        for(short i = 0; i < DataFav.data.get(0).size(); i++)
        {
            if(DataFav.data.get(0).get(i).equals(data[0])){
                imageButton.setImageResource(R.drawable.favorite);
                isFav = true;
            }
        }
        imageButton.setOnClickListener(v -> {
            DataFav dataFav = new DataFav();
            if (!isFav) {
                imageButton.setImageResource(R.drawable.favorite);
                Toast.makeText(getApplicationContext(), "Recipe added to Favorite", Toast.LENGTH_SHORT).show();
                dataFav.getData(data, imageid);
                isFav = true;
            } else {
                imageButton.setImageResource(R.drawable.favorite_border);
                Toast.makeText(getApplicationContext(), "Recipe removed from Favorite", Toast.LENGTH_SHORT).show();
                dataFav.removeData(data[0]);
                isFav = false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}