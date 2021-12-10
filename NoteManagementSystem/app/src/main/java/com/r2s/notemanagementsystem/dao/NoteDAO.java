package com.r2s.notemanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.r2s.notemanagementsystem.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM note_table")
    List<Note> getAllNotes();
}
