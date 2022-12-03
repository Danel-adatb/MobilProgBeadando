package com.beadando.xuxejo.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "carsForSale")
public class Car implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "hp")
    private String hp;

    public Car(String name, String color, String hp) {
        this.name = name;
        this.color = color;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String car) {
        this.name = name;
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
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", hp=" + hp +
                '}';
    }
}
