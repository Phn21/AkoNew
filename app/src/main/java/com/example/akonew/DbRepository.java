package com.example.akonew;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.akonew.Room.DBDao;
import com.example.akonew.Room.DBDatabase;
import com.example.akonew.Room.Db;

import java.util.List;

public class DbRepository {

    private DBDao dbDao;
    private LiveData<List<Db>> allInfo;

    public DbRepository(Application application) {
        DBDatabase dbDatabase = DBDatabase.getInstance(application);
        this.dbDao = dbDatabase.dbDao();
        this.allInfo = dbDao.getAll();
    }

    public void insert(Db db){
        new Insert(dbDao).execute(db);
    }

    public void update(Db db){
        new Update(dbDao).execute(db);
    }

    public void delete(Db db){
        new Delete(dbDao).execute(db);
    }

    public void deleteAll(){
        new DeleteAll(dbDao).execute();
    }

    public LiveData<List<Db>> getAllInfo(){
        return allInfo;
    }


    private static class Insert extends AsyncTask<Db,Void,Void>{

        private DBDao dbDao;
        public Insert(DBDao dbDao){
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(Db... dbs) {
            dbDao.insert(dbs[0]);
            return null;
        }
    }

    private static class Update extends AsyncTask<Db,Void,Void>{

        private DBDao dbDao;
        public Update(DBDao dbDao){
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(Db... dbs) {
            dbDao.update(dbs[0]);
            return null;
        }
    }

    private static class Delete extends AsyncTask<Db,Void,Void>{

        private DBDao dbDao;
        public Delete(DBDao dbDao){
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(Db... dbs) {
            dbDao.delete(dbs[0]);
            return null;
        }
    }

    private static class DeleteAll extends AsyncTask<Void,Void,Void>{

        private DBDao dbDao;

        public DeleteAll(DBDao dbDao){
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbDao.deleteAll();
            return null;
        }
    }
}
