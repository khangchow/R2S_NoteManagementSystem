package com.r2s.notemanagementsystem.view.slidemenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.constant.CategoryConstant;
import com.r2s.notemanagementsystem.model.Status;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    PieChart pieChart;
    List<PieEntry> pieEntryList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pieChart = view.findViewById(R.id.pieChart);

        initView();

        return view;
    }

    private void initView() {
        pieEntryList.clear();
        pieChart.setUsePercentValues(true);

        pieEntryList.add(new PieEntry(6, "Done"));
        pieEntryList.add(new PieEntry(4, "Process"));
        pieEntryList.add(new PieEntry(4, "Pending"));

        PieDataSet pieDataSet = new PieDataSet(pieEntryList,"Status");
        pieChart.setData(new PieData(pieDataSet));

        pieDataSet.setValueTextSize(16f);
        pieDataSet.setColors(CategoryConstant.COLOR_RGB);
        pieChart.setDrawHoleEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.invalidate();
    }
}