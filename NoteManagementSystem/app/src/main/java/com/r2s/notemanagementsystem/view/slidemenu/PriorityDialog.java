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

    private PriorityViewModel mPriorityViewModel;
    private DialogPriorityBinding binding;
    private PriorityAdapter mPriorityAdapter;
    private List<Priority> mPriorities = new ArrayList<>();

    public static String TAG = "PriorityDialog";

    public static PriorityDialog newInstance() {
        return new PriorityDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogPriorityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPriorityViewModel = new ViewModelProvider(this).get(PriorityViewModel.class);
        mPriorityAdapter = new PriorityAdapter(mPriorities);

        setUpViewModel();
        setOnClicks();
    }

    public void setOnClicks() {
        binding.btnAddPriority.setOnClickListener(this);
        binding.btnClosePriority.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_priority:
                String currentDate = getCurrentLocalDateTimeStamp();
                Priority priority = new Priority(0, binding.etPriority.getText().toString(), currentDate, 1);

                mPriorityViewModel.insertPriority(priority);

                Toast.makeText(getActivity(), binding.etPriority.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btn_close_priority:
                dismiss();
                break;
        }
    }

    private void setUpViewModel() {
        mPriorityViewModel.getAllPriorities().observe(getViewLifecycleOwner(), priorities -> {
            mPriorityAdapter.setPriorities(priorities);
        });
    }

    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
