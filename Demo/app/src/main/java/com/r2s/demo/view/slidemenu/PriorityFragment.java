package com.r2s.demo.view.slidemenu;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.r2s.demo.R;
import com.r2s.demo.adapter.PriorityAdapter;
import com.r2s.demo.local.AppDatabase;
import com.r2s.demo.local.AppExecutors;
import com.r2s.demo.local.AppPrefs;
import com.r2s.demo.model.Priority;
import com.r2s.demo.view.PriorityDialog;
import com.r2s.demo.viewmodel.PriorityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriorityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriorityFragment extends Fragment {
    private FloatingActionButton fabPriorityOpen;
    private RecyclerView mPriorityRecyclerView;
    private PriorityAdapter mPriorityAdapter;
    private PriorityViewModel mPriorityViewModel;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPriorityName = getArguments().getString("priority_name");
            mPriorityCreatedDate = getArguments().getString("priority_created_date");
        }
        mPriorityViewModel = new ViewModelProvider(requireActivity()).get(PriorityViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_priority, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fabPriorityOpen = view.findViewById(R.id.fab_open_priority);

        fabPriorityOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_priorityFragment_to_priorityDialog);
            }
        });

        mPriorityRecyclerView = view.findViewById(R.id.rv_priority);

        mPriorityRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Initialize the Adapter and attach it to the RecyclerView
        mPriorityAdapter = new PriorityAdapter(this.getContext());
        mPriorityRecyclerView.setAdapter(mPriorityAdapter);

        mPriorityViewModel = new ViewModelProvider(requireActivity()).get(PriorityViewModel.class);
        mPriorityViewModel.getAllPriorities().observe(getViewLifecycleOwner(), priorities -> {
            mPriorityAdapter.setTasks(priorities);
        });

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
                        List<Priority> tasks = mPriorityAdapter.getTasks();
                        mDb.getPriorityDao().deletePriority(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }
        }).attachToRecyclerView(mPriorityRecyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }

    public void retrieveTasks() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Priority> priorities = mDb.getPriorityDao().getAll();
                runOnUiThread(new Runnable() {
                    
                })
            }
        });
    }
}