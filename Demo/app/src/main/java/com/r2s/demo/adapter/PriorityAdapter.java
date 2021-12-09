package com.r2s.demo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.demo.R;
import com.r2s.demo.databinding.RowPriorityListBinding;
import com.r2s.demo.model.Priority;

import java.util.List;
import java.lang.String;

public class PriorityAdapter extends RecyclerView.Adapter<PriorityAdapter.PriorityViewHolder> {

    private List<Priority> mPriorities;

    /**
     * Initialize the dataset of the Adapter.
     * @param mPriorities List<Priority> containing the data to populate views to be used
     * by RecyclerView.
     */
    public PriorityAdapter(List<Priority> mPriorities) {
        this.mPriorities = mPriorities;
    }

    /**
     * Create new views (invoked by the layout manager)
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

        
    }

    /**
     * Return the size of your dataset
     * @return size of your dataset
     */
    @Override
    public int getItemCount() {
        return mPriorities.size();
    }

    /**
     * Update and notify the data changes
     * @param mPriorities
     */
    public void setPriorities(List<Priority> mPriorities) {
        this.mPriorities = mPriorities;

        notifyDataSetChanged();
    }

    public List<Priority> getPriorities() {
        return mPriorities;
    }

    /**
     * Provide a constructor that accepts the entire item row
     * and does the view lookups to find each subview
     */
    protected class PriorityViewHolder extends RecyclerView.ViewHolder {
        private RowPriorityListBinding binding;

        public PriorityViewHolder(@NonNull RowPriorityListBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }

        public void bind(Priority priority) {
            binding.tvPriorityName.setText(priority.getName());
            binding.tvPriorityCreatedDate.setText(priority.getCreatedDate());
            binding.tvPriorityAuthorId.setText(String.valueOf(priority.getAuthorId()));
        }
    }
}
