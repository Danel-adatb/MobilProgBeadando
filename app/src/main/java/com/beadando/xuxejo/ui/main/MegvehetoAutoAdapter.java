package com.beadando.xuxejo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.beadando.xuxejo.R;
import com.beadando.xuxejo.model.MegvehetoAuto;

import java.util.List;

public class MegvehetoAutoAdapter extends RecyclerView.Adapter<MegvehetoAutoViewHolder> {
    private List<MegvehetoAuto> dataSet;

    public MegvehetoAutoAdapter(List<MegvehetoAuto> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MegvehetoAutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.MegvehetoAutoRow, parent, false);
        MegvehetoAutoViewHolder vh = new MegvehetoAutoViewHolder(layout);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MegvehetoAutoViewHolder holder, int position) {
        holder.car.setText(dataSet.get(position).getCar());
        holder.color.setText(dataSet.get(position).getColor());
        holder.hp.setText(dataSet.get(position).getHp());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
