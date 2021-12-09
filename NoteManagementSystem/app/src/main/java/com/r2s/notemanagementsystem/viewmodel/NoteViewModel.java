package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.model.Note;
import com.r2s.notemanagementsystem.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mNoteRepository;
    private LiveData<List<Note>> mNotes;

    // Replace with SharedPreferences user id
    private int userId = 1;

    /**
     * Constructor with 1 parameter
     * @param application Application
     */
    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.mNoteRepository = new NoteRepository(application);

        this.mNotes = mNoteRepository.getAllNotesByUserId(userId);
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
        mNoteRepository.insertNote(note);
    }

    /**
     * This method updates a note by id
     * @param note note
     */
    public void updateNote(Note note) {
        mNoteRepository.updateNotes(note);
    }

    /**
     * This method deletes a note
     * @param note Note
     */
    public void deleteNote(Note note) {
        mNoteRepository.deleteNote(note);
    }
}
