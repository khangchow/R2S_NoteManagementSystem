package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.model.Category;
import com.r2s.notemanagementsystem.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private CategoryRepository mCateRepo;
    private LiveData<List<Category>> mCates;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        this.mCateRepo = new CategoryRepository(application);
        this.mCates = mCateRepo.getAllCate();
    }

    public LiveData<List<Category>> loadAllCate(int uId) {
        return mCates;
    }

    public void insertCate(Category category) {
        mCateRepo.insert(category);
    }

    public void updateCate(Category category) {
        mCateRepo.update(category);
    }

    public void deleteCate(Category category) {
        mCateRepo.delete(category);
    }

    public Category loadCateById(int id) {
        return mCateRepo.getCateById(id);
    }
}
