package com.beadando.xuxejo;

import android.content.Intent;
import android.os.Bundle;

import com.beadando.xuxejo.dao.MegvehetoAutoDB;
import com.beadando.xuxejo.model.MegvehetoAuto;
import com.beadando.xuxejo.recycleview.CostumAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Datas extends AppCompatActivity {
    RecyclerView recyclerView;
    List<MegvehetoAuto> modelList;
    CostumAdapter costumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datas);
        //Közös komponensek közötti kommunikáció
        Intent intent = getIntent();
        MegvehetoAuto megvehetoAuto = (MegvehetoAuto) intent.getSerializableExtra("megvehetoAuto");

        //TextView
        TextView text = findViewById(R.id.textView);
        text.setText("Car Added: "+ megvehetoAuto.getCar());

        //RecycleView
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        modelList = new ArrayList<>();
        modelList.add(new MegvehetoAuto(megvehetoAuto.getCar(), megvehetoAuto.getColor(), megvehetoAuto.getHp()));
        costumAdapter = new CostumAdapter(this, modelList);
        recyclerView.setAdapter(costumAdapter);
    }
}