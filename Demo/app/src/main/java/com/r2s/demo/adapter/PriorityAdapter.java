package com.r2s.demo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.demo.R;
import com.r2s.demo.databinding.RowPriorityListBinding;
import com.r2s.demo.model.Priority;
import com.r2s.demo.view.PriorityDialog;
import com.r2s.demo.view.slidemenu.PriorityFragment;

import java.util.List;
import java.lang.String;
import java.util.Objects;

public class PriorityAdapter extends RecyclerView.Adapter<PriorityAdapter.PriorityViewHolder> {

    private List<Priority> mPriorities;
    private Context mContext;

    public PriorityAdapter(List<Priority> mPriorities, Context context) {
        this.mPriorities = mPriorities;
        this.mContext = context;
    }

    /**
     * Create new views
     * @param parent
     * @param viewType
     * @return new view
     */
    @NonNull
    @Override
    public PriorityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Return a new holder instance
        return new PriorityViewHolder(RowPriorityListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Replace the contents of a view
     * @param holder the viewHolder
     * @param position the view position in the viewHolder list
     */
    @Override
    public void onBindViewHolder(@NonNull PriorityViewHolder holder, int position) {
        holder.bind(mPriorities.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("priority_id", mPriorities.get(holder.getAdapterPosition()).getId());
                bundle.putString("priority_name", mPriorities.get(holder.getAdapterPosition()).getName());

                final PriorityDialog priorityDialog = new PriorityDialog();
                priorityDialog.setArguments(bundle);

                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                priorityDialog.show(fm, "PriorityDialog");
            }
        });
    }

    /**
     * Return the size of your dataset
     * @return size of your dataset
     */
    @Override
    public int getItemCount() {
        return mPriorities.size();
    }

    public void setPriorities(List<Priority> mPriorities) {
        this.mPriorities = mPriorities;
        notifyDataSetChanged();
    }

    public List<Priority> getPriorities() {
        return mPriorities;
    }

    public class PriorityViewHolder extends RecyclerView.ViewHolder {
        private final RowPriorityListBinding binding;

        public PriorityViewHolder(@NonNull RowPriorityListBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }

        public void bind(Priority priority) {
            String priorityName = "Name: " + priority.getName();
            String priorityCreatedDate = "Created Date: " + priority.getCreatedDate();
            String priorityAuthorId = "Author Id: " + String.valueOf(priority.getAuthorId());

            binding.tvPriorityName.setText(priorityName);
            binding.tvPriorityCreatedDate.setText(priorityCreatedDate);
            binding.tvPriorityAuthorId.setText(priorityAuthorId);
        }
    }
}
