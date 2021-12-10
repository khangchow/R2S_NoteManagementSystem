package com.r2s.notemanagementsystem.view.dialog;

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

    public static final String TAG = "StatusDialog";
    private StatusViewModel mStatusViewModel;
    private DialogStatusBinding binding;
    private StatusAdapter mStatusAdapter;
    private List<Status> mStatuses = new ArrayList<>();
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
        binding = DialogStatusBinding.inflate(inflater, container, false);
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

        mStatusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);
        mStatusAdapter = new StatusAdapter(mStatuses, this.getContext());

        setUpViewModel();
        setOnClicks();
    }

    /**
     * This method sets on-click listener for views
     */
    public void setOnClicks() {
        binding.btnAddStatus.setOnClickListener(this);
        binding.btnCloseStatus.setOnClickListener(this);
    }

    /**
     * This method sets up the ViewModel
     */
    public void setUpViewModel() {
        mStatusViewModel.getAllStatuses().observe(getViewLifecycleOwner(), statuses -> {
            mStatusAdapter.setStatuses(statuses);
        });
    }

    /**
     * This method sets on-click actions for views
     * @param view current view of the activity/fragment
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_status:
                if (binding.btnAddStatus.getText().toString().equalsIgnoreCase("add")) {
                    String currentDate = getCurrentLocalDateTimeStamp();
                    Status status = new Status(0, binding.etStatus.getText().toString(), currentDate, 1);

                    mStatusViewModel.insertStatus(status);

                    Toast.makeText(getActivity(), binding.etStatus.getText().toString(), Toast.LENGTH_SHORT).show();
                    dismiss();
                }

                if (binding.btnAddStatus.getText().toString().equalsIgnoreCase("update")) {
                    int updateId = bundle.getInt("status_id");

                    String currentDate = getCurrentLocalDateTimeStamp();
                    Status status = new Status(updateId, binding.etStatus.getText().toString(), currentDate, userId);

                    mStatusViewModel.updateStatus(status);

                    Toast.makeText(getActivity(), binding.etStatus.getText().toString(), Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                break;
            case R.id.btn_close_status:
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
