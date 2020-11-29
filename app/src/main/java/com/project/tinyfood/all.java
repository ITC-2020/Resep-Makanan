package com.project.tinyfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link all#newInstance} factory method to
 * create an instance of this fragment.
 */
public class all extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<Data> data;
    Adapter adapter;
    private Context context;

    public all() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment all.
     */
    // TODO: Rename and change types and number of parameters
    public static all newInstance(String param1, String param2) {
        all fragment = new all();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        LinearLayoutManager linermanager = new LinearLayoutManager(context);
        this.context = view.getContext();
        recyclerView = view.findViewById(R.id.rv_all);
        datadummy datadummy2 = new datadummy();
        data = datadummy2.getAlldatadummy();
        this.adapter = new Adapter(data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linermanager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void ClickItem(int position, Data data) {
                    clickItem(data);
            }


        });



    }

    private void clickItem(Data data) {

        Intent intent = new Intent(context,Detail.class);
        intent.putExtra("nama", data.getTitle());
        intent.putExtra("category",data.getCategory());
        intent.putExtra("resep",data.getResep());
        intent.putExtra("image",data.getDataImg());
        startActivity(intent);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }
}