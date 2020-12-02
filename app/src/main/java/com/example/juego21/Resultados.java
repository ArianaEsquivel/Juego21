package com.example.juego21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class Resultados extends AppCompatActivity {
    private RecyclerView RvListaResultados;
    private RequestQueue cartero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        cartero = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        obtenerResultados();

    }

    public void obtenerResultados(){
        RvListaResultados = findViewById(R.id.rvresultados);
        RvListaResultados.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RvListaResultados.setLayoutManager(linearLayoutManager);
        Gson convertidor = new Gson();

        String urlr = "https://ramiro174.com/api/obtener/numero";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlr, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray lista_resultados = response.getJSONArray("resultados");
                    Type tipoListaResultado = new TypeToken<List<ListResultados>>(){}.getType();
                    List<ListResultados> resultados = convertidor.fromJson(lista_resultados.toString(), tipoListaResultado);
                    AdapterResultados mAdapter = new AdapterResultados(resultados);
                    RvListaResultados.setAdapter(mAdapter);


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
}