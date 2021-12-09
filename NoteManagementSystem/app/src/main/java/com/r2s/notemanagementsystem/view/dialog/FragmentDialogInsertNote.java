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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.r2s.notemanagementsystem.R;
import java.util.Calendar;

public class FragmentDialogInsertNote extends DialogFragment implements View.OnClickListener {

    AutoCompleteTextView autoCompleteTextViewCategory, autoCompleteTextViewPriority,
            autoCompleteTextViewStatus;
    private static final String[] itemsCategory = {"Relax", "Working",};
    private static final String[] itemsPriority = {"Slow", "High",};
    private static final String[] itemsStatus = {"Done", "Processing",};

    private ArrayAdapter<String> adapterItemCategory, adapterItemPriority, adapterItemStatus;

    private Button btnShowDate, btnInsert, btnClose;

    private TextView tvDatePlan;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_insert_note, container, false);

        initView(view);
        eventItemClick();

        return view;
    }

    public void initView(View view) {

        // auto complete for category
        autoCompleteTextViewCategory = view.findViewById(R.id.auto_complete_category);
        adapterItemCategory = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, itemsCategory);
        autoCompleteTextViewCategory.setAdapter(adapterItemCategory);

        // auto complete for priority
        autoCompleteTextViewPriority = view.findViewById(R.id.auto_complete_priority);
        adapterItemPriority = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, itemsPriority);
        autoCompleteTextViewPriority.setAdapter(adapterItemPriority);

        // auto complete for status
        autoCompleteTextViewStatus = view.findViewById(R.id.auto_complete_status);
        adapterItemStatus = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, itemsStatus);
        autoCompleteTextViewStatus.setAdapter(adapterItemStatus);

        // button
        btnShowDate = view.findViewById(R.id.show_date_picker);
        btnShowDate.setOnClickListener(this);

        btnInsert = view.findViewById(R.id.dialog_insert);
        btnInsert.setOnClickListener(this);

        btnClose = view.findViewById(R.id.dialog_close);
        btnClose.setOnClickListener(this);

        // Text view
        tvDatePlan = view.findViewById(R.id.tv_date_plan);

        // Show date when choose date inside date picker
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                tvDatePlan.setText(date);
            }
        };

    }

    public void eventItemClick() {
        autoCompleteTextViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), item, Toast.LENGTH_LONG).show();
            }
        });

        autoCompleteTextViewPriority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), item, Toast.LENGTH_LONG).show();
            }
        });

        autoCompleteTextViewStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), item, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static FragmentDialogInsertNote showInsertNoteDialog() {
        return new FragmentDialogInsertNote();
    }

    /**
     *
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_date_picker:
                setUpDatePicker();
                break;
            case R.id.dialog_close:
                dismiss();
                break;

        }
    }


}
