package com.r2s.demo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.r2s.demo.R;
import com.r2s.demo.adapter.PriorityAdapter;
import com.r2s.demo.databinding.DialogPriorityBinding;
import com.r2s.demo.model.Priority;
import com.r2s.demo.viewmodel.PriorityViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PriorityDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "PriorityDialog";
    private PriorityViewModel mPriorityViewModel;
    private DialogPriorityBinding binding;
    private PriorityAdapter mPriorityAdapter;
    private List<Priority> mPriorities = new ArrayList<>();
    private Bundle bundle = new Bundle();

    private static PriorityDialog newInstance() {
        return new PriorityDialog();
    }

    // Replace with SharedPreferences user id
    private int userId = 1;

    /**
     * Method called when a view is being created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogPriorityBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    /**
     * Method called after the onCreateView() method
     * @param view
     * @param savedInstanceState
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

    private void setUpViewModel() {
        mPriorityViewModel.getAllPrioritiesByUserId(userId).observe(getViewLifecycleOwner(), priorities -> {
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