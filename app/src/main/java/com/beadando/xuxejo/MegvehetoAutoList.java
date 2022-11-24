package com.beadando.xuxejo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.beadando.xuxejo.ui.main.MegvehetoAutoListFragment;

public class MegvehetoAutoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_megveheto_auto_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MegvehetoAutoListFragment.newInstance())
                    .commitNow();
        }
    }
}