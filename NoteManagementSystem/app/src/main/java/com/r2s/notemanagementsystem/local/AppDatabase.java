package com.r2s.notemanagementsystem.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.r2s.notemanagementsystem.Dao.CategoryDao;
import com.r2s.notemanagementsystem.Dao.PriorityDao;
import com.r2s.notemanagementsystem.Dao.StatusDao;
import com.r2s.notemanagementsystem.model.Category;
import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.model.Status;

@Database(entities = {Category.class, Priority.class, Status.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    public abstract CategoryDao getCateDao();
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
