package com.beadando.xuxejo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beadando.xuxejo.dao.MegvehetoAutoDB;
import com.beadando.xuxejo.model.MegvehetoAuto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    MegvehetoAutoDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Komponensek közötti kommunikáció
        EditText newField = findViewById(R.id.car);
        db = Room.databaseBuilder(this, MegvehetoAutoDB.class, "forSale database").build();
        newField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Button nextButton = findViewById(R.id.add);
                if(editable.toString().isEmpty()) {
                    nextButton.setEnabled(false);
                } else if(!nextButton.isEnabled()) {
                    nextButton.setEnabled(true);
                }
            }
        });

    }

    //Komponensek közötti kommunikáció - másik adattárolás
    public void NewScreen(View view) {
        EditText car = findViewById(R.id.car);
        EditText color = findViewById(R.id.color);
        EditText hp = findViewById(R.id.hp);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString("Car", car.getText().toString());
        editor.apply();

        String oldname = sharedPreferences.getString("Car", "Not given yet!");
        System.out.println("The given name:"+ oldname);

        Intent intent = new Intent(MainActivity.this, Datas.class);

        MegvehetoAuto megvehetoAuto = new MegvehetoAuto(car.getText().toString(), color.getText().toString(), hp.getText().toString());

        intent.putExtra("megvehetoAuto", megvehetoAuto);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        //Másik adattárolás
        String fileName = "data.txt";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(car.getText().toString());
        stringBuilder.append(", ");
        stringBuilder.append(color.getText().toString());
        stringBuilder.append(", ");
        stringBuilder.append(hp.getText().toString());

        System.out.println(Environment.getExternalStorageDirectory());
        File extFile = new File(getExternalFilesDir("")+"/myDatas.txt");

        if(!extFile.exists()) {
            System.out.println("Non Existing File!");
        }

        System.out.println(getFilesDir().getPath());
        try {
            FileWriter writerExt = new FileWriter(extFile, true);
            writerExt.write(stringBuilder.toString());
            writerExt.append("\n");
            writerExt.flush();
            writerExt.close();


            FileOutputStream out = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(stringBuilder.toString());
            writer.write("\n");
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Kiiratás
        try {
            FileInputStream in = openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String row = "";

            while((row = reader.readLine()) != null) {
                System.out.println(row);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //DB
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                EditText car = findViewById(R.id.car);
                EditText color = findViewById(R.id.color);
                EditText hp = findViewById(R.id.hp);

                MegvehetoAuto megvehetoAuto = new MegvehetoAuto(car.getText().toString(), color.getText().toString(), hp.getText().toString());

                db.megvehetoAutoDao().insertAll(megvehetoAuto);
                System.out.println(db.megvehetoAutoDao().getAll());
            }
        });

        startActivity(intent);
    }
}