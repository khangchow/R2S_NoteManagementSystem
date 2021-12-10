package com.r2s.notemanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category_table ORDER BY cateId ASC")
    LiveData<List<Category>> getAll();

    @Query("SELECT * FROM category_table WHERE cateId IN(:cateId)")
    LiveData<List<Category>> loadCateById(int cateId);

    @Insert
    void insertCate(Category category);

    @Update
    void updateCate(Category category);

    @Delete
    void deleteCate(Category category);
}
