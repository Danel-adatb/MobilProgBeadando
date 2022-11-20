package com.beadando.xuxejo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.beadando.xuxejo.model.MegvehetoAuto;

import java.util.List;

@Dao
public interface MegvehetoAutoDao {
    @Query("SELECT * FROM forSale")
    List<MegvehetoAuto> getAll();

    @Insert
    void insertAll(MegvehetoAuto... megvehetoAuto);
}
