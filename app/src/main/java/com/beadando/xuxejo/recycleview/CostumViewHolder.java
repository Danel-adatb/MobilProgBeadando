package com.beadando.xuxejo.recycleview;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beadando.xuxejo.R;

public class CostumViewHolder extends RecyclerView.ViewHolder {
    public TextView textName, textColor, textHp;

    public CostumViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        textColor = itemView.findViewById(R.id.textColor);
        textHp = itemView.findViewById(R.id.textHP);
    }
}
