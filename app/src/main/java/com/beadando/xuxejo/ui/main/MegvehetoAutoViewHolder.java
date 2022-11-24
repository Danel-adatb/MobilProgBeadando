package com.beadando.xuxejo.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beadando.xuxejo.R;

public class MegvehetoAutoViewHolder extends RecyclerView.ViewHolder {
    public TextView car;
    public TextView color;
    public TextView hp;

    public MegvehetoAutoViewHolder(@NonNull View itemView) {
        super(itemView);
        car = itemView.findViewById(R.id.rowCar);
        color = itemView.findViewById(R.id.rowColor);
        hp = itemView.findViewById(R.id.rowHP);
    }
}
