package com.r2s.notemanagementsystem.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.r2s.notemanagementsystem.dao.UserDao;
import com.r2s.notemanagementsystem.model.User;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    /**
     * This is a singleton method to get instance of database
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (context) {
                mInstance = Room.databaseBuilder(context, AppDatabase.class, "appdb").fallbackToDestructiveMigration().build();
            }
        }

        return mInstance;
    }

    public abstract UserDao userDao();
}
