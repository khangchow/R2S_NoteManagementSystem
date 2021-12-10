package com.r2s.notemanagementsystem.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.constant.Constants;
import com.r2s.notemanagementsystem.dao.CategoryDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Category;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;

import java.util.List;
import java.util.concurrent.Executors;

public class CategoryRepository {
    private AppDatabase mDb;
    private CategoryDao mCateDao;
    private LiveData<List<Category>> mCates;
    private User mUser;

    /**
     * This method is used as constructor for CategoryRepository class
     * @param application
     */
    public CategoryRepository(Application application) {
        this.mDb = AppDatabase.getInstance(application);

        mUser = new Gson().fromJson(AppPrefsUtils.getString(Constants.KEY_USER_DATA), User.class);

        mCateDao = mDb.getCateDao();

        mCates = mCateDao.loadCateById(mUser.getUid());
    }

    /**
     * his method returns all category
     * @return
     */
    public LiveData<List<Category>> getAllCate() {
        return mCates;
    }

    /**
     * This method inserts a new category
     * @param category
     */
    public void insert (Category category) {
        Executors.newSingleThreadExecutor().execute(() ->{
            mCateDao.insertCate(category);
        });
    }

    /**
     * This method update category
     * @param category
     */
    public void update (Category category) {
        Executors.newSingleThreadExecutor().execute(() ->{
            mCateDao.updateCate(category);
        });
    }

    /**
     * This method delete category
     * @param category
     */
    public void delete (Category category) {
        Executors.newSingleThreadExecutor().execute(() ->{
            mCateDao.deleteCate(category);
        });
    }

}
