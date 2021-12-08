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
import com.r2s.notemanagementsystem.constant.PriorityConstant;
import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.viewmodel.PriorityViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Executors;

public class PriorityDialog extends DialogFragment {
    private EditText mPriorityName;
    private Button btnAdd;
    private Button btnClose;
    private PriorityViewModel mPriorityViewModel;
    private int mPriorityId;
    private Intent intent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPriorityViewModel = new ViewModelProvider(this).get(PriorityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_priority, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPriorityName = view.findViewById(R.id.et_priority);
        btnAdd = view.findViewById(R.id.btn_add_priority);
        btnClose = view.findViewById(R.id.btn_close_priority);

        if (getArguments() != null) {
            mPriorityName.setText(getArguments().getString("priority_name"));
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDate = getCurrentLocalDateTimeStamp();
                Priority priority = new Priority(mPriorityName.getText().toString(), LocalDateTime.now().toString(), 1);

                Executors.newSingleThreadExecutor().execute(() -> {
                    if (!intent.hasExtra(PriorityConstant.UPDATE_Priority_Id)) {
                        mPriorityViewModel.insertPriority(priority);
                    } else {
                        priority.setId(mPriorityId);
                        mPriorityViewModel.updatePriority(priority);
                    }
                });

                Toast.makeText(getActivity(), mPriorityName.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });
    }

    private void populateUI(Priority priority) {
        if (priority == null) {
            return;
        }

        mPriorityName.setText(priority.getName());
    }

    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
