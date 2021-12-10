package com.r2s.notemanagementsystem.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM note_table WHERE userId IN (:user_id)")
    LiveData<List<Note>> getAllByUserId(int user_id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
