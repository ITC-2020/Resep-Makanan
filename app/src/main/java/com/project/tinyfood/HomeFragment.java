package com.project.tinyfood;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment implements View.OnClickListener {


    Context context;
    ImageView all1, meat1, pasta1, seafood1, dessert1;
    public static int stat = 0;

    public HomeFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        all1 = view.findViewById(R.id.all_food);
        meat1 = view.findViewById(R.id.meat_food);
        pasta1 = view.findViewById(R.id.pasta_food);
        seafood1 = view.findViewById(R.id.sea_food);
        dessert1 = view.findViewById(R.id.dessert_food);


        this.context = view.getContext();

        all1.setOnClickListener(this);
        meat1.setOnClickListener(this);
        pasta1.setOnClickListener(this);
        seafood1.setOnClickListener(this);
        dessert1.setOnClickListener(this);


        FragmentManager command = getFragmentManager();
        CategoryFragment CategoryFragment = new CategoryFragment();
        if (command != null)
            command.beginTransaction().replace(R.id.fragment_container, CategoryFragment, CategoryFragment.class.getSimpleName()).addToBackStack(null).commit();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        FragmentManager command = getFragmentManager();
        CategoryFragment CategoryFragment = new CategoryFragment();
        switch (view.getId()) {
            case R.id.all_food:
                stat = 0;
                break;
            case R.id.dessert_food:
                stat = 1;
                break;
            case R.id.meat_food:
                stat = 2;
                break;
            case R.id.pasta_food:
                stat = 3;
                break;
            case R.id.sea_food:
                stat = 4;
                break;
        }
        if (command != null)
            command.beginTransaction().replace(R.id.fragment_container, CategoryFragment, CategoryFragment.class.getSimpleName()).addToBackStack(null).commit();
    }
}