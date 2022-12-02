package com.beadando.xuxejo.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beadando.xuxejo.R;
import com.beadando.xuxejo.model.MegvehetoAuto;

import java.util.List;

public class CostumAdapter extends RecyclerView.Adapter<CostumViewHolder> {
    private Context context;
    private List<MegvehetoAuto> list;

    public CostumAdapter(Context context, List<MegvehetoAuto> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CostumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CostumViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_single_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CostumViewHolder holder, int position) {
        holder.textName.setText(list.get(position).getCar());
        holder.textColor.setText(list.get(position).getColor());
        holder.textHp.setText(list.get(position).getHp());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
