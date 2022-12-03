package com.beadando.xuxejo.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beadando.xuxejo.R;
import com.beadando.xuxejo.database.Car;

import java.util.List;

public class CostumAdapter extends RecyclerView.Adapter<CostumViewHolder> {
    private Context context;
    private List<Car> list;

    public CostumAdapter(Context context) {
        this.context = context;
    }

    public void setCarList(List<Car> carList) {
        this.list = carList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CostumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CostumViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_single_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CostumViewHolder holder, int position) {
        holder.textName.setText(this.list.get(position).getName());
        holder.textColor.setText(this.list.get(position).getColor());
        holder.textHp.setText(this.list.get(position).getHp());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}