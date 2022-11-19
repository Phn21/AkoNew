package com.example.akonew;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.akonew.Room.Db;

import java.util.List;

public class DbViewModel extends AndroidViewModel {

    private DbRepository dbRepository;
    private LiveData<List<Db>> allInfo;

    public DbViewModel(@NonNull Application application) {
        super(application);
        dbRepository = new DbRepository(application);
        allInfo = dbRepository.getAllInfo();
    }

    public void insert(Db db) {dbRepository.insert(db);}
    public void update(Db db) {dbRepository.update(db);}
    public void delete(Db db) {dbRepository.delete(db);}
    public void deleteAll() {dbRepository.deleteAll();}
    public LiveData<List<Db>> getAllInfo(){return allInfo;}

}
