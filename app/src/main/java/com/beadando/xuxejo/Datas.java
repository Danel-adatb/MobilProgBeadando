package com.beadando.xuxejo;

import android.content.Intent;
import android.os.Bundle;

import com.beadando.xuxejo.database.AppDatabase;
import com.beadando.xuxejo.database.Car;
import com.beadando.xuxejo.recycleview.CostumAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Datas extends AppCompatActivity {
    private CostumAdapter costumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datas);

        //TextView
        TextView text = findViewById(R.id.textView);
        text.setText("New Row Added Succesfully! ");

        //DB
        initRecyclerView();
        loadCarList();

    }

    private void loadCarList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Car> carlist = db.carDAO().getAllCars();
        costumAdapter.setCarList(carlist);

        System.out.println(db.carDAO().getAllCars());
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        costumAdapter = new CostumAdapter(this);
        recyclerView.setAdapter(costumAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadCarList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}