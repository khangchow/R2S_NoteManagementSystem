package com.r2s.demo.view;

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

import com.r2s.demo.R;
import com.r2s.demo.constant.PriorityConstant;
import com.r2s.demo.model.Priority;
import com.r2s.demo.viewmodel.PriorityViewModel;

import java.text.SimpleDateFormat;
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

    public static PriorityDialog newInstance(String title) {
        PriorityDialog priorityDialog = new PriorityDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        priorityDialog.setArguments(args);
        return priorityDialog;
    }

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
        btnAdd = view.findViewById(R.id.btn_add);
        btnClose = view.findViewById(R.id.btn_close);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDate = getCurrentLocalDateTimeStamp();
                Priority priority = new Priority(mPriorityName.getText().toString(), currentDate, 1);

                Executors.newSingleThreadExecutor().execute(() -> {
                    mPriorityViewModel.insertPriority(priority);
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

    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
