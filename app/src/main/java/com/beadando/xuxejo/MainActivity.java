package com.beadando.xuxejo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beadando.xuxejo.dao.MegvehetoAutoDB;
import com.beadando.xuxejo.Employees;
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
    Button cameraButton;
    int permReqCode = 1;
    Button startButton;
    Button pauseButton;
    Button stopButton;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Média
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

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

        //Média - camera
        cameraButton = findViewById(R.id.cameraButton);

        if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraButton.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, permReqCode);
        }

        //Média - MediaPlayer
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Toast.makeText(getApplicationContext(), "Media started!", Toast.LENGTH_SHORT).show();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()) {
                    mp.pause();
                    Toast.makeText(getApplicationContext(), "Media paused at !"+mp.getCurrentPosition(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                Toast.makeText(getApplicationContext(), "Media stopped!", Toast.LENGTH_SHORT).show();
            }
        });

        prepareMediaPlayer();
    }

    //Média - MediaPlayer
    private void prepareMediaPlayer() {
        mp = MediaPlayer.create(this, R.raw.solas);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                startButton.setEnabled(true);
                pauseButton.setEnabled(true);
                stopButton.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Media loaded!", Toast.LENGTH_SHORT).show();
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getApplicationContext(), "Media finished!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Média - camera
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == permReqCode) {
            for(int i = 0; i < permissions.length; i++) {
                if(permissions[i] == Manifest.permission.CAMERA && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission granted for Camera!", Toast.LENGTH_SHORT).show();
                    cameraButton.setEnabled(true);
                }
            }
        }
    }

    int CAMERA_IMAGE_REQUEST = 101;

    public void Camera(View view) {
        Intent cIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "image.jpg");
        //cIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(cIntent, CAMERA_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_IMAGE_REQUEST) {
            if(resultCode == RESULT_OK) {
                Bitmap img = (Bitmap) data.getExtras().get("data");
                findViewById(R.id.constraintLayout).setBackground(new BitmapDrawable(getResources(), img));
                Uri unf = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), unf);
                r.setStreamType(AudioManager.STREAM_ALARM);
                r.play();
            }
        }
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
                System.out.println("From File: "+row);
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
                System.out.println("DB: "+db.megvehetoAutoDao().getAll());
            }
        });

        startActivity(intent);
    }

    //HTTP
    public void Employees(View view) {
        Intent intent = new Intent(MainActivity.this, Employees.class);
        startActivity(intent);
    }
}