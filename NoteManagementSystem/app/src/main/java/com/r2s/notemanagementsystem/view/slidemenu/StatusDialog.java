package com.r2s.notemanagementsystem.view.slidemenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.adapter.StatusAdapter;
import com.r2s.notemanagementsystem.databinding.DialogStatusBinding;
import com.r2s.notemanagementsystem.model.Status;
import com.r2s.notemanagementsystem.viewmodel.StatusViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StatusDialog extends DialogFragment implements View.OnClickListener {

    private StatusViewModel mStatusViewModel;
    private DialogStatusBinding binding;
    private StatusAdapter mStatusAdapter;
    private List<Status> mStatuses = new ArrayList<>();

    public static String TAG = "StatusDialog";

    public static StatusDialog newInstance() {
        return new StatusDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogStatusBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStatusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);
        mStatusAdapter = new StatusAdapter(mStatuses);

        setUpViewModel();
        setOnClicks();
    }

    public void setOnClicks() {
        binding.btnAddStatus.setOnClickListener(this);
        binding.btnCloseStatus.setOnClickListener(this);
    }

    public void setUpViewModel() {
        mStatusViewModel.getAllStatuses().observe(getViewLifecycleOwner(), statuses -> {
            mStatusAdapter.setStatuses(statuses);
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_status:
                String currentDate = getCurrentLocalDateTimeStamp();
                Status status = new Status(0, binding.etStatus.getText().toString(), currentDate, 1);

                mStatusViewModel.insertStatus(status);

                Toast.makeText(getActivity(), binding.etStatus.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btn_close_status:
                dismiss();
                break;
        }
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
