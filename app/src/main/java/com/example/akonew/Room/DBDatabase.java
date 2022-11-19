package com.example.akonew.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Db.class, version = 1)
public abstract class DBDatabase extends RoomDatabase{

    private static DBDatabase instance;

    public abstract DBDao dbDao();

    public static synchronized DBDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DBDatabase.class, "NEWDb")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
//
//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            new PopulateDbAsyncTask(instance).execute();
//        }
//    };
//
//    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
//
//        private DBDao dbDao;
//
//        public PopulateDbAsyncTask(DBDatabase db) {
//            this.dbDao = db.dbDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
////            dbDao.insert(new Db("Ako", "Soselia"));
////            dbDao.insert(new Db("Eka", "Soselia"));
////            dbDao.insert(new Db("Gia", "Soselia"));
////            dbDao.insert(new Db("De", "Soselia"));
//            return null;
//        }
//    }

}
