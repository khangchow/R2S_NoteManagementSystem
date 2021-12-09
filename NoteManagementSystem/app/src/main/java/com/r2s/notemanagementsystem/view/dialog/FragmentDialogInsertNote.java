package com.r2s.notemanagementsystem.view.dialog;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.adapter.NoteAdapter;
import com.r2s.notemanagementsystem.constant.NoteConstant;
import com.r2s.notemanagementsystem.databinding.DialogFragmentInsertNoteBinding;
import com.r2s.notemanagementsystem.model.Note;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;
import com.r2s.notemanagementsystem.viewmodel.NoteViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentDialogInsertNote extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "NoteDiaLog";
    private NoteViewModel mNoteViewModel;
    private DialogFragmentInsertNoteBinding binding;
    private NoteAdapter mNoteAdapter;
    private List<Note> mNotes = new ArrayList<>();
    private Bundle bundle = new Bundle();

    // Replace with SharedPreferences user id
    private int userId = 1;

    private static final String[] itemsCategory = {"Relax", "Working",};
    private static final String[] itemsPriority = {"Slow", "High",};
    private static final String[] itemsStatus = {"Done", "Processing",};

    private ArrayAdapter<String> adapterItemCategory, adapterItemPriority, adapterItemStatus;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    // text of note
    String strNoteName, strCategoryName, strPriorityName, strStatusName, strPlanDate, strCreatedDate;

    /**
     * This method is called when a view is being created
     *
     * @param inflater           LayoutInflater
     * @param container          ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFragmentInsertNoteBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    /**
     * This method is called after the onCreateView() method
     *
     * @param view               View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteAdapter = new NoteAdapter(mNotes, this.getContext());

        initView(view);
        setUpViewModel();
        setOnClicks();
        eventItemClick();

        bundle = getArguments();
        if (bundle != null) {
            binding.dialogInsert.setText("Update");
            binding.edtNoteName.setText(bundle.getString("note_name"));
        }
    }

    /**
     * This method sets on-click listener for views
     */
    public void setOnClicks() {
        binding.dialogInsert.setOnClickListener(this);
        binding.dialogClose.setOnClickListener(this);
        binding.showDatePicker.setOnClickListener(this);
    }

    /**
     * This method sets up the ViewModel
     */
    private void setUpViewModel() {
        mNoteViewModel.getAllNotesByUserId(userId).observe(getViewLifecycleOwner(), notes -> {
            mNoteAdapter.setNotes(notes);
        });
    }

    /**
     * This method sets on-click actions for views
     *
     * @param view current view of the activity/fragment
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_insert:
                if (binding.dialogInsert.getText().toString().equalsIgnoreCase("add")) {
                    String currentDate = getCurrentLocalDateTimeStamp();
                    Note note = new Note(0,binding.edtNoteName.getText().toString(), strCategoryName
                            , strPriorityName, strStatusName, strPlanDate, currentDate, userId);

                    mNoteViewModel.insertNote(note);

                    Toast.makeText(getActivity(), "Add successful!", Toast.LENGTH_SHORT).show();
                    dismiss();
                }

                if (binding.dialogInsert.getText().toString().equalsIgnoreCase("update")) {
                    int updateId = bundle.getInt("note_id");

                    String currentDate = getCurrentLocalDateTimeStamp();
                    Note note = new Note(updateId, binding.edtNoteName.getText().toString(), strCategoryName
                            , strPriorityName, strStatusName, strPlanDate, currentDate, userId);

                    mNoteViewModel.updateNote(note);

                    Toast.makeText(getActivity(), "Update successful", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                break;
            case R.id.dialog_close:
                dismiss();
                break;
            case R.id.show_date_picker:
                setUpDatePicker();
                break;
        }
    }

    public void initView(View view) {

        // auto complete for category
        adapterItemCategory = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, itemsCategory);
        binding.autoCompleteCategory.setAdapter(adapterItemCategory);

        // auto complete for priority
        adapterItemPriority = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, itemsPriority);
        binding.autoCompletePriority.setAdapter(adapterItemPriority);

        // auto complete for status
        adapterItemStatus = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, itemsStatus);
        binding.autoCompleteStatus.setAdapter(adapterItemStatus);

        // Show date when choose date inside date picker
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                binding.tvDatePlan.setText(date);
                strPlanDate = date;
            }
        };

    }

    /**
     * this method with get text when click on item autocomplete
     */
    public void eventItemClick() {
        binding.autoCompleteCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strCategoryName = parent.getItemAtPosition(position).toString();
            }
        });

        binding.autoCompletePriority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strPriorityName = parent.getItemAtPosition(position).toString();
            }
        });

        binding.autoCompleteStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strStatusName = parent.getItemAtPosition(position).toString();
            }
        });
    }

    public static FragmentDialogInsertNote showInsertNoteDialog() {
        return new FragmentDialogInsertNote();
    }

    /**
     * this method will set up for date picker
     */
    public void setUpDatePicker() {
        Calendar kal = Calendar.getInstance();
        int year = kal.get(Calendar.YEAR);
        int month = kal.get(Calendar.MONTH);
        int day = kal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                dateSetListener, year, month, day);

        dialog.show();
    }

    /**
     * This method returns the current date with custom format
     *
     * @return String
     */
    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
