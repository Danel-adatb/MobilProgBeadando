package com.beadando.xuxejo.ui.main;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.beadando.xuxejo.dao.MegvehetoAutoDB;
import com.beadando.xuxejo.model.MegvehetoAuto;

import java.util.List;

public class MegvehetoAutoViewModel extends AndroidViewModel {

    private MutableLiveData<List<MegvehetoAuto>> megvehetoAutok;
    MegvehetoAutoDB appDatabase;

    public MegvehetoAutoViewModel(@NonNull Application app) {
        super(app);

        this.appDatabase = Room.databaseBuilder(app.getApplicationContext(), MegvehetoAutoDB.class, "forSale database").build();
    }

    public MutableLiveData<List<MegvehetoAuto>> getMegvehetoAutok() {
        if(megvehetoAutok == null) {
            megvehetoAutok = new MutableLiveData<>();
            loadMegvehetoAutok();
        }

        return megvehetoAutok;
    }

    public void loadMegvehetoAutok() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MegvehetoAuto mva = new MegvehetoAuto();
                mva.setCar("Ferrari");
                mva.setColor("Red");
                mva.setHp("1200HP");
                appDatabase.megvehetoAutoDao().insertAll(mva);
                callbackForAsync(appDatabase.megvehetoAutoDao().getAll());
            }
        });
    }

    private void callbackForAsync(List megvehetoAutok) {
        this.megvehetoAutok.postValue(megvehetoAutok);
    }
}