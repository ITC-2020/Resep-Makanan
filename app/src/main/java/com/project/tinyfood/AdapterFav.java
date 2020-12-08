package com.project.tinyfood;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.Viewholder> {
    ArrayList<Data> data;
    public AdapterFav(ArrayList<Data> data) {
        this.data = data;
    }
    private AdapterFav.OnFavItemClickListener favListener;
    public void setOnItemClickListener(AdapterFav.OnFavItemClickListener listener) {
        this.favListener = listener;
    }

    public interface OnFavItemClickListener {
        void ClickItem(int position, Data data);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cetak_menu, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.bind(data.get(position), favListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView[] textView = new TextView[2];
        int[] textID = {R.id.tv_nama, R.id.tv_categories};
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(final Data data, final OnFavItemClickListener OnFavItemClickListener) {

            final String[] dataText = {data.getTitle(), data.getCategory()};
            for (short i = 0; i < textID.length; i++) {
                textView[i] = new TextView((itemView.getContext()));
                textView[i] = itemView.findViewById(textID[i]);
                textView[i].setText(dataText[i]);


            }

            imageView = itemView.findViewById(R.id.iv_foto);
            imageView.setImageDrawable(itemView.getResources().getDrawable(data.getDataImg()));
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    OnFavItemClickListener.ClickItem(position, data);
                }
            });

        }
    }
}
