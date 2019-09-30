package com.audit.health.ssc.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {LoginTable.class, UploadTable.class, QuestionListTable.class, BatchTable.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "SDM";
    public static MyDatabase myDatabase;

    public static MyDatabase dataBase(Context mContext) {
        if (myDatabase == null) {
            myDatabase = Room.databaseBuilder(mContext, MyDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }

        return myDatabase;

    }

    public abstract LoginDAO loginDAO();
    public abstract BatchDao batchDao();
    public abstract UploadDao uploadDao();

    public abstract QiestionListDao questionListDao();


}