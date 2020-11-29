package com.project.tinyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    TextView[] text = new TextView[3];
    int[] textID = {R.id.tv_nama, R.id.tv_categories ,R.id.tv_step};
    ImageView imageView;
    int imageid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageid = getIntent().getIntExtra("image", 0);
        imageView = findViewById(R.id.iv_foto);
        imageView.setImageDrawable(getResources().getDrawable(imageid));
        String[] data = new String[3];
        data[0] = getIntent().getStringExtra("nama");
        data[1] = getIntent().getStringExtra("category");
        data[2] = getIntent().getStringExtra("resep");

        for (char i = 0; i < text.length; i++) {
            text[i] = new TextView(this);
            text[i] = findViewById(textID[i]);
            text[i].setText(data[i]);
        }
    }
}