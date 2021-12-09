package com.r2s.demo.view.slidemenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
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
import android.widget.Toast;

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
import java.util.Objects;

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
    private String mParam1;
    private String mParam2;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Priority> priorities = mPriorityAdapter.getPriorities();
                        mDb.getPriorityDao().deletePriority(priorities.get(position));
                        retrievePriorities();
                    }
                });
            }
        }).attachToRecyclerView(binding.rvPriority);
    }

    @Override
    public void onResume() {
        super.onResume();
        retrievePriorities();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_open_priority:
                DialogFragment priorityDialog = new PriorityDialog();
                priorityDialog.show(getChildFragmentManager(), PriorityDialog.TAG);
        }
    }

    public void retrievePriorities() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPriorityViewModel.getAllPriorities().observe(getViewLifecycleOwner(), priorities -> {
                            mPriorityAdapter.setPriorities(priorities);
                        });
                    }
                });

            }
        });
    }
}