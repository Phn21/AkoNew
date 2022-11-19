package com.example.akonew.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Db {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name, surname;

    public Db(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
