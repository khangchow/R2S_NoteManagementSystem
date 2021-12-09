package com.r2s.demo.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.r2s.demo.dao.PriorityDao;
import com.r2s.demo.dao.StatusDao;
import com.r2s.demo.dao.UserDao;
import com.r2s.demo.model.Priority;
import com.r2s.demo.model.Status;
import com.r2s.demo.model.User;

@Database(entities = {Priority.class, Status.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    public abstract UserDao getUserDao();
    public abstract PriorityDao getPriorityDao();
    public abstract StatusDao getStatusDao();
    /**
     * This is a singleton method to get instance of database
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (context) {
                mInstance = Room.databaseBuilder(context, AppDatabase.class, "appdb").build();
            }
        }

        return mInstance;
    }
}
