package com.beadando.xuxejo;

import android.content.Intent;
import android.os.Bundle;

import com.beadando.xuxejo.model.MegvehetoAuto;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.beadando.xuxejo.databinding.ActivityDatasBinding;

public class Datas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datas);
        //Közös komponensek közötti kommunikáció
        Intent intent = getIntent();
        MegvehetoAuto megvehetoAuto = (MegvehetoAuto) intent.getSerializableExtra("megvehetoAuto");

        TextView text = findViewById(R.id.textView);
        text.setText("Car Added: "+ megvehetoAuto.getCar());
    }
}