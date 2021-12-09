package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.dao.NoteDAO;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Note;

import java.util.List;
import java.util.concurrent.Executors;

public class NoteRepository {
    private AppDatabase mDb;
    private NoteDAO mNoteDao;
    private LiveData<List<Note>> mNotes;

    // Replace with SharedPreferences user id
    private int userId = 1;

    /**
     * This method is used as constructor for NoteRepository class
     * @param context
     */
    public NoteRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);

        this.mNoteDao = mDb.getNoteDAO();

        this.mNotes = mNoteDao.getAllByUserId(userId);
    }

    /**
     * This method returns all notes by current logged in user
     * @param userId int
     * @return LiveData List
     */
    public LiveData<List<Note>> getAllNotesByUserId(int userId) {
        return mNotes;
    }

    /**
     * This method inserts a new note
     * @param note Note
     */
    public void insertNote(Note note) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mNoteDao.insertNote(note);
        });
    }

    /**
     * This method updates a note by id
     * @param note Note
     */
    public void updateNotes(Note note) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mNoteDao.updateNote(note);
        });
    }

    /**
     * This method deletes a note
     * @param note Note
     */
    public void deleteNote(Note note) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mNoteDao.deleteNote(note);
        });
    }
}
