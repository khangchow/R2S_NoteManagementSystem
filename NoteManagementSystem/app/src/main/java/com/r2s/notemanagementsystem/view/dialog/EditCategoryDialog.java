package com.r2s.notemanagementsystem.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.adapter.CategoryAdapter;
import com.r2s.notemanagementsystem.databinding.DialogEditCategoryBinding;
import com.r2s.notemanagementsystem.model.Category;
import com.r2s.notemanagementsystem.viewmodel.CategoryViewModel;

import java.util.ArrayList;

public class EditCategoryDialog extends DialogFragment implements View.OnClickListener {
    private CategoryViewModel mCateViewModel;
    private DialogEditCategoryBinding binding;
    private CategoryAdapter mCateAdapter;
    private ArrayList<Category> categoryArrayList = new ArrayList<>();
    private int cateId = 1;
    private int userId = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DialogEditCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCateViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        mCateAdapter = new CategoryAdapter(categoryArrayList, this.getContext());

        mCateViewModel.loadAllCate(userId).observe(getViewLifecycleOwner(), categories -> {
            mCateAdapter.setTasks(categories);
        });

        binding.btnUpdate.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);

        setCancelable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:
                Category category = new Category(cateId, binding.etEditCate.getText().toString());

                mCateViewModel.updateCate(category);
                dismiss();

            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
