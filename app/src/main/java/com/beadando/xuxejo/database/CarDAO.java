package com.beadando.xuxejo.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDAO {

    @Query("SELECT * FROM carsForSale")
    List<Car> getAllCars();

    @Insert
    void insertCar(Car... car);

    @Delete
    void delete(Car car);
}
