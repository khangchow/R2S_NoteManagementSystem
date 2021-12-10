package com.r2s.notemanagementsystem.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.r2s.notemanagementsystem.dao.CategoryDao;
import com.r2s.notemanagementsystem.dao.PriorityDao;
import com.r2s.notemanagementsystem.dao.StatusDao;
import com.r2s.notemanagementsystem.dao.NoteDAO;
import com.r2s.notemanagementsystem.model.Category;
import com.r2s.notemanagementsystem.model.Note;
import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.model.Status;
import com.r2s.notemanagementsystem.model.Note;

@Database(entities = {User.class, Category.class, Priority.class, Status.class, Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;

    public abstract UserDao userDao();
    public abstract NoteDAO getNoteDAO();

    public abstract CategoryDao getCateDao();
    public abstract PriorityDao getPriorityDao();
    public abstract StatusDao getStatusDao();
    /**
     * This is a singleton method to get instance of database
     *
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

