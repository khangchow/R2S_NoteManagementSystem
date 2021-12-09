package com.r2s.notemanagementsystem.local;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.r2s.notemanagementsystem.Dao.CategoryDao;
import com.r2s.notemanagementsystem.model.Category;

@Database(entities = {Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    public abstract CategoryDao getCateDao();
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
