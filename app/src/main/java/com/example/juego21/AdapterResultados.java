package com.example.juego21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterResultados extends RecyclerView.Adapter<AdapterResultados.ResultadosHolder> {
    private List<ListResultados> Resultados;

    public AdapterResultados (List<ListResultados> resultados){
        Resultados = resultados;
    }

    @NonNull
    @Override
    public AdapterResultados.ResultadosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_resultados, parent, false);

        return new ResultadosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResultados.ResultadosHolder holder, int position) {
        holder.addData(Resultados.get(position));
    }

    @Override
    public int getItemCount() {
        return Resultados.size();
    }

    public class ResultadosHolder extends RecyclerView.ViewHolder {
        private TextView tvnombre, tvnumero;
        public ResultadosHolder(@NonNull View itemView) {
            super(itemView);
            tvnombre = itemView.findViewById(R.id.nombre);
            tvnumero = itemView.findViewById(R.id.numero);
        }

        public void addData(ListResultados resultado) {
            tvnumero.setText(String.valueOf(resultado.getNumero()));
            tvnombre.setText(resultado.getNombre());
        }
    }
}
