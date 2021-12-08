package com.r2s.demo.view;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.r2s.demo.R;

import java.util.Objects;

public class PriorityDialog extends DialogFragment {

    private EditText mEditText;
    private Button btnAdd;
    private Button btnClose;

    public static PriorityDialog newInstance(String title) {
        PriorityDialog priorityDialog = new PriorityDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        priorityDialog.setArguments(args);
        return priorityDialog;
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface PriorityDialogListener {
        void onFinishEditDialog(String inputText);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_priority, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.et_priority);
        btnAdd = view.findViewById(R.id.btn_add);
        btnClose = view.findViewById(R.id.btn_close);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("amount", "Welcome back to Android");
                Navigation.findNavController(view).navigate(R.id.action_priorityDialog_to_priorityFragment, bundle);

                Toast.makeText(getActivity(), mEditText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });
    }
}
