package com.r2s.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.demo.R;
import com.r2s.demo.model.Priority;

import java.util.List;

public class PriorityAdapter extends RecyclerView.Adapter<PriorityAdapter.PriorityViewHolder> {
    private Context context;
    private List<Priority> mPriorityList;

    public PriorityAdapter(Context context) {
        this.context = context;
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
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = layoutInflater.inflate(R.layout.row_priority_list, parent, false);

        // Return a new holder instance
        return new PriorityViewHolder(view);
    }

    /**
     * Replace the contents of a view
     * @param holder the viewHolder
     * @param position the view position in the viewHolder list
     */
    @Override
    public void onBindViewHolder(@NonNull PriorityViewHolder holder, int position) {
        Priority priority = mPriorityList.get(position);

        holder.tvPriorityName.setText(priority.getName());
        holder.tvPriorityCreatedDate.setText(priority.getCreatedDate());
        holder.tvAuthorId.setText(String.valueOf(priority.getAuthorId()));
    }

    /**
     * Return the size of your dataset
     * @return size of your dataset
     */
    @Override
    public int getItemCount() {
        return mPriorityList.size();
    }

    public void setTasks(List<Priority> priorityList) {
        mPriorityList = priorityList;
        notifyDataSetChanged();
    }

    public List<Priority> getTasks() {
        return mPriorityList;
    }

    public class PriorityViewHolder extends RecyclerView.ViewHolder {
        TextView tvPriorityName;
        TextView tvPriorityCreatedDate;
        TextView tvAuthorId;

        public PriorityViewHolder(@NonNull View viewItem) {
            super(viewItem);

            tvPriorityName = viewItem.findViewById(R.id.tv_priority_name);
            tvPriorityCreatedDate = viewItem.findViewById(R.id.tv_priority_created_date);
            tvAuthorId = viewItem.findViewById(R.id.tv_priority_author_id);
        }
    }
}
