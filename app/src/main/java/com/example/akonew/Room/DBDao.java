package com.example.akonew.Room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DBDao {

    @Insert
    void insert(Db db);

    @Update
    void update(Db db);

    @Delete
    void delete(Db db);

    @Query("DELETE FROM Db")
    void deleteAll();

    @Query("SELECT * FROM Db")
    LiveData<List<Db>> getAll();


}
