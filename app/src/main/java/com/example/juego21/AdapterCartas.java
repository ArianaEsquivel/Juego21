package com.example.juego21;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterCartas extends RecyclerView.Adapter<AdapterCartas.CartasHolder> {
    private  ArrayList<ImageView> Cartas;
    public AdapterCartas (ArrayList<ImageView> cartas){
        Cartas = cartas;
    }
    @NonNull
    @Override
    public AdapterCartas.CartasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cartas, parent, false);
        return new CartasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCartas.CartasHolder holder, int position) {
        holder.addData(Cartas.get(position));
    }

    @Override
    public int getItemCount() {
        return Cartas.size();
    }

    public class CartasHolder extends RecyclerView.ViewHolder {
        private ImageView cartaa;
        public CartasHolder(@NonNull View itemView) {
            super(itemView);
            cartaa = itemView.findViewById(R.id.carta);
        }
        public void addData(ImageView nCarta){
            cartaa.setImageDrawable(nCarta.getDrawable());
        }
    }
}
