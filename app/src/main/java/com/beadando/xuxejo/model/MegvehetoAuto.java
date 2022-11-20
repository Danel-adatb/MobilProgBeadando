package com.beadando.xuxejo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Komponensek közötti kommunikáció - AB
@Entity(tableName = "forSale")
public class MegvehetoAuto implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "car_name")
    private String car;
    private String color;
    private String hp;

    public MegvehetoAuto() {

    }

    public MegvehetoAuto(String car, String color, String hp) {
        this.car = car;
        this.color = color;
        this.hp = hp;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "MegvehetoAuto{" +
                "car='" + car + '\'' +
                ", color='" + color + '\'' +
                ", hp=" + hp +
                '}';
    }
}
