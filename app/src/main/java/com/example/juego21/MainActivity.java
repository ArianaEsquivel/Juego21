package com.example.juego21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RvListaCartas;
    private List<ImageView> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RvListaCartas = findViewById(R.id.rvcartas);
        RvListaCartas.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RvListaCartas.setLayoutManager(linearLayoutManager);
        ImageView jeje = new ImageView(this);
        jeje.setImageDrawable(getDrawable(R.drawable.c2));
        //lista.add(jeje);
        AdapterCartas adapterCartas = new AdapterCartas(lista);
        //RvListaCartas.setAdapter(adapterCartas);
    }

    public void snumero(View view) {
    }

    public void enumero(View view) {
    }

    public void resultados(View view) {
    }
}