package com.r2s.notemanagementsystem.view.slidemenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.view.dialog.FragmentDialogInsertNote;

public class FragmentNote extends Fragment {

    private FloatingActionButton fabInsert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        fabInsert = view.findViewById(R.id.fab);

        fabInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = FragmentDialogInsertNote.showInsertNoteDialog();
                dialogFragment.show(getParentFragmentManager(), "Insert Note");
            }
        });
        return view;
    }
}
