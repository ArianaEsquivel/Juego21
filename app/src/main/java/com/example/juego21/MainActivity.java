package com.example.juego21;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class MainActivity extends AppCompatActivity {

    private RecyclerView RvListaCartas;
    private ArrayList<ImageView>lista = new ArrayList<>();
    private RequestQueue cartero;
    private TextView num;
    private ImageView ncarta;
    private int total;
    private boolean enviado = false;
    private Intent resultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RvListaCartas = findViewById(R.id.rvcartas);
        RvListaCartas.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RvListaCartas.setLayoutManager(linearLayoutManager);
        cartero = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        num = findViewById(R.id.puntuacion);
        resultados = new Intent(this, Resultados.class);
    }


    public void snumero(View view) {
        if (num.getText().toString().contains("Perdiste") || num.getText().toString().contains("Obtuviste") ){
            restart();
        }
        String urln = "https://ramiro174.com/api/numero";
        ncarta = new ImageView(this);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urln, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int nnum = response.getInt("numero");
                    int img;
                    switch (nnum){
                        case 1:
                            img = R.drawable.c1;
                            break;
                        case 2:
                            img = R.drawable.c2;
                            break;
                        case 3:
                            img = R.drawable.c3;
                            break;
                        case 4:
                            img = R.drawable.c4;
                            break;
                        case 5:
                            img = R.drawable.c5;
                            break;
                        case 6:
                            img = R.drawable.c6;
                            break;
                        case 7:
                            img = R.drawable.c7;
                            break;
                        case 8:
                            img = R.drawable.c8;
                            break;
                        case 9:
                            img = R.drawable.c9;
                            break;
                        case 10:
                            img = R.drawable.c10;
                            break;
                        case 11:
                            img = R.drawable.c11;
                            break;
                        case 12:
                            img = R.drawable.c12;
                            break;
                        case 13:
                            img = R.drawable.c13;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + nnum);
                    }
                    total += nnum;
                    ncarta.setImageDrawable(getDrawable(img));
                    num.setText(String.valueOf(total));
                    lista.add(ncarta);
                    AdapterCartas adapterCartas = new AdapterCartas(lista);
                    RvListaCartas.setAdapter(adapterCartas);

                    if (total > 21){
                        num.setText("Perdiste: " + total);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        cartero.add(request);
    }

    public void enumero(View view) {
        String urle = "https://ramiro174.com/api/enviar/numero";
        if (total > 21)
        {
            restart();
            Toast.makeText(MainActivity.this, "Lo sentimos, perdiste.", Toast.LENGTH_SHORT).show();
        }
        else if (total == 0){
            Toast.makeText(MainActivity.this, "Solicita un número.", Toast.LENGTH_SHORT).show();
        }
        else if (enviado == true){
            restart();
            Toast.makeText(MainActivity.this, "Ya has enviado tu resultado.", Toast.LENGTH_SHORT).show();
        }
        else {
            JSONObject datos = new JSONObject();
            try {
                datos.put("nombre", "Ari");
                datos.put("numero", total);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urle, datos, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    num.setText("Obtuviste: " + String.valueOf(total));
                    enviado = true;
                    //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    //Log.i("onResponse: ", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            cartero.add(request);
        }
    }

    public void resultados(View view) {
        startActivity(resultados);
        restart();
    }

    public void restart(){
        lista.clear();
        total = 0;
        num.setText("Bienvenido🤠");
        AdapterCartas adapterCartas = new AdapterCartas(lista);
        RvListaCartas.setAdapter(adapterCartas);
        enviado = false;
    }

    public void restaurar(View view) {
        restart();
    }
}