package com.r2s.notemanagementsystem.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.dao.CategoryDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Category;

import java.util.List;
import java.util.concurrent.Executors;

public class CategoryRepository {
    private AppDatabase mDb;
    private CategoryDao mCateDao;
    private LiveData<List<Category>> mCates;

    public CategoryRepository(Application application) {
        this.mDb = AppDatabase.getInstance(application);

        mCateDao = mDb.getCateDao();
        mCates = mCateDao.getAll();
    }

    public LiveData<List<Category>> getAllCate() {
        return mCates;
    }

    public void insert (Category category) {
        Executors.newSingleThreadExecutor().execute(() ->{
            mCateDao.insertCate(category);
        });
    }

    public void update (Category category) {
        Executors.newSingleThreadExecutor().execute(() ->{
            mCateDao.updateCate(category);
        });
    }

    public void delete (Category category) {
        Executors.newSingleThreadExecutor().execute(() ->{
            mCateDao.deleteCate(category);
        });
    }

    public Category getCateById(int id) {
        return mCateDao.loadCateById(id);
    }
}
