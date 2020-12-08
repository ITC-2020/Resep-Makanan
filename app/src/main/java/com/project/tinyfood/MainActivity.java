package com.project.tinyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        context = getApplicationContext();
        loadFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_home:
                loadFragment(new HomeFragment());
                break;

            case R.id.menu_favorite:
                if(DataFav.data.get(0).size() == 0)
                    loadFragment(new FavoritesFragment());
                else
                    loadFragment(new FavoritesFragment2());
                break;

            case R.id.menu_recipes:
                loadFragment(new RecipesFragment());
                break;
        }
        return true;
    }

    private void loadFragment(Fragment selected){
        if(selected != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame,selected)
                    .show(selected)
                    .commit();
        }
    }
}