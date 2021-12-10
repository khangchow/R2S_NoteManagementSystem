package com.r2s.notemanagementsystem.view.slidemenu;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.adapter.PriorityAdapter;
import com.r2s.notemanagementsystem.constant.PriorityConstant;
import com.r2s.notemanagementsystem.databinding.DialogPriorityBinding;
import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.viewmodel.PriorityViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

public class PriorityDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "PriorityDialog";
    private PriorityViewModel mPriorityViewModel;
    private DialogPriorityBinding binding;
    private PriorityAdapter mPriorityAdapter;
    private List<Priority> mPriorities = new ArrayList<>();
    private Bundle bundle = new Bundle();

    // Replace with SharedPreferences user id
    private int userId = 1;

    /**
     * This method is called when a view is being created
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogPriorityBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    /**
     * This method is called after the onCreateView() method
     * @param view View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPriorityViewModel = new ViewModelProvider(this).get(PriorityViewModel.class);
        mPriorityAdapter = new PriorityAdapter(mPriorities, this.getContext());

        setUpViewModel();
        setOnClicks();

        bundle = getArguments();
        if (bundle != null) {
            binding.btnAddPriority.setText("Update");
            binding.etPriority.setText(bundle.getString("priority_name" ));
        }
    }

    /**
     * This method sets on-click listener for views
     */
    public void setOnClicks() {
        binding.btnAddPriority.setOnClickListener(this);
        binding.btnClosePriority.setOnClickListener(this);
    }

    /**
     * This method sets up the ViewModel
     */
    private void setUpViewModel() {
        mPriorityViewModel.getAllPrioritiesByUserId(userId).observe(getViewLifecycleOwner(), priorities -> {
            mPriorityAdapter.setPriorities(priorities);
        });
    }

    /**
     * This method sets on-click actions for views
     * @param view current view of the activity/fragment
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_priority:
                if (binding.btnAddPriority.getText().toString().equalsIgnoreCase("add")) {
                    String currentDate = getCurrentLocalDateTimeStamp();
                    Priority priority = new Priority(0, binding.etPriority.getText().toString(), currentDate, userId);

                    mPriorityViewModel.insertPriority(priority);

                    Toast.makeText(getActivity(), binding.etPriority.getText().toString(), Toast.LENGTH_SHORT).show();
                    dismiss();
                }

                if (binding.btnAddPriority.getText().toString().equalsIgnoreCase("update")) {
                    int updateId = bundle.getInt("priority_id");

                    String currentDate = getCurrentLocalDateTimeStamp();
                    Priority priority = new Priority(updateId, binding.etPriority.getText().toString(), currentDate, userId);

                    mPriorityViewModel.updatePriority(priority);

                    Toast.makeText(getActivity(), binding.etPriority.getText().toString(), Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                break;
            case R.id.btn_close_priority:
                dismiss();
                break;
        }
    }

    /**
     * This method returns the current date with custom format
     * @return String
     */
    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * This method is called when the view is destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}