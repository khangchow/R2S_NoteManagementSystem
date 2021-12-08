package com.r2s.notemanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.databinding.RowStatusListBinding;
import com.r2s.notemanagementsystem.model.Status;

import java.util.List;
import java.lang.String;

public class StatusAdapter extends  RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {

    private List<Status> mStatuses;

    public StatusAdapter(List<Status> mStatuses) {
        this.mStatuses = mStatuses;
    }

    /**
     * Create new views
     * @param parent
     * @param viewType
     * @return new view
     */
    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Return a new holder instance
        return new StatusViewHolder(RowStatusListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Replace the contents of a view
     * @param holder the viewHolder
     * @param position the view position in the viewHolder list
     */
    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        holder.bind(mStatuses.get(position));
    }

    /**
     * Return the size of your dataset
     * @return size of your dataset
     */
    @Override
    public int getItemCount() {
        return mStatuses.size();
    }

    public void setStatuses(List<Status> statusList) {
        mStatuses = statusList;

        notifyDataSetChanged();
    }

    public List<Status> getStatuses() {
        return mStatuses;
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder {
        private RowStatusListBinding binding;

        public StatusViewHolder(@NonNull RowStatusListBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }

        public void bind(Status status) {
            binding.tvStatusName.setText(status.getName());
            binding.tvStatusCreatedDate.setText(status.getCreatedDate());
            binding.tvStatusAuthorId.setText(String.valueOf(status.getAuthorId()));
        }
    }
}
