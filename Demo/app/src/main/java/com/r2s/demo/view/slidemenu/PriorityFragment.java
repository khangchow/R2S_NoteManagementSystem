package com.r2s.demo.view.slidemenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r2s.demo.R;
import com.r2s.demo.adapter.PriorityAdapter;
import com.r2s.demo.databinding.FragmentPriorityBinding;
import com.r2s.demo.local.AppDatabase;
import com.r2s.demo.local.AppExecutors;
import com.r2s.demo.model.Priority;
import com.r2s.demo.view.PriorityDialog;
import com.r2s.demo.viewmodel.PriorityViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriorityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriorityFragment extends Fragment implements View.OnClickListener {
    private FragmentPriorityBinding binding;
    private PriorityViewModel mPriorityViewModel;
    private PriorityAdapter mPriorityAdapter;
    private List<Priority> mPriorities = new ArrayList<>();

    private AppDatabase mDb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mPriorityName;
    private String mPriorityCreatedDate;

    public PriorityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PriorityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PriorityFragment newInstance(String param1, String param2) {
        PriorityFragment fragment = new PriorityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPriorityBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPriorityViewModel = new ViewModelProvider(this).get(PriorityViewModel.class);

        setUpRecyclerView();

        setUpViewModel();

        setOnClicks();

        mDb = Room.databaseBuilder(requireContext(), AppDatabase.class, "appdb").build();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Implement swipe to delete
                int position = viewHolder.getAdapterPosition();
                List<Priority> tasks = mPriorityAdapter.getPriorities();
                mDb.getPriorityDao().deletePriority(tasks.get(position));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setOnClicks() {
        binding.fabOpenPriority.setOnClickListener(this);
    }

    /**
     * Method to initialize the Adapter and attach it to the RecyclerView
     */
    private void setUpRecyclerView() {
        mPriorityAdapter = new PriorityAdapter(mPriorities);

        binding.rvPriority.setAdapter(mPriorityAdapter);

        binding.rvPriority.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpViewModel() {
        mPriorityViewModel.getAllPriorities().observe(getViewLifecycleOwner(), priorities -> {
            mPriorityAdapter.setPriorities(priorities);
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_open_priority:
                new PriorityDialog().show(getChildFragmentManager(), PriorityDialog.TAG);
        }
    }
}