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

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class PriorityAdapter extends RecyclerView.Adapter<PriorityAdapter.PriorityViewHolder> {
    private Context context;
    private List<Priority> mPriorityList = new ArrayList<>();;

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

        String priorityName = context.getString(R.string.string_priority_name, priority.getName());
        String priorityCreatedDate = context.getString(R.string.string_priority_created_date,priority.getCreatedDate());
        String priorityAuthorId = context.getString(R.string.string_priority_author_id, String.valueOf(priority.getAuthorId()));

        holder.tvPriorityName.setText(priorityName);
        holder.tvPriorityCreatedDate.setText(priorityCreatedDate);
        holder.tvAuthorId.setText(priorityAuthorId);
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
