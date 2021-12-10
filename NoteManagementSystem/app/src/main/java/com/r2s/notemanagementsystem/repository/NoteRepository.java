package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.constant.Constants;
import com.r2s.notemanagementsystem.dao.NoteDAO;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Note;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;

import java.util.List;
import java.util.concurrent.Executors;

public class NoteRepository {
    private AppDatabase mDb;
    private NoteDAO mNoteDao;
    private LiveData<List<Note>> mNotes;
    private User user;

    /**
     * This method is constructor for NoteRepository class
     *
     * @param context
     */
    public NoteRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);
        user = new Gson().fromJson(AppPrefsUtils.getString(Constants.KEY_USER_DATA), User.class);

        this.mNoteDao = mDb.getNoteDAO();

        this.mNotes = mNoteDao.getAllByUserId(user.getUid());
    }

    /**
     * This method returns all notes by current logged in user account
     *
     * @return LiveData List
     */
    public LiveData<List<Note>> getAllNotesByUserId() {
        return mNotes;
    }

    /**
     * This method inserts a new note
     *
     * @param note Note
     */
    public void insertNote(Note note) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mNoteDao.insertNote(note);
        });
    }

    /**
     * This method updates note by id
     *
     * @param note Note
     */
    public void updateNotes(Note note) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mNoteDao.updateNote(note);
        });
    }

    /**
     * This method deletes a note
     *
     * @param note Note
     */
    public void deleteNote(Note note) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mNoteDao.deleteNote(note);
        });
    }
}
