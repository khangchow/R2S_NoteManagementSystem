package com.r2s.notemanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.model.Status;

import java.util.List;

public class StatusAdapter extends  RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    private Context context;
    private List<Status> mStatusList;

    public StatusAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_status_list, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        Status status = mStatusList.get(position);

        holder.tvStatusName.setText(status.getName());
        holder.tvStatusCreatedDate.setText(status.getCreatedDate());
        holder.tvStatusAuthorId.setText(status.getAuthorId());
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    public void setTaks(List<Status> statusList) {
        mStatusList = statusList;
        notifyDataSetChanged();
    }

    public List<Status> getTasks() {
        return mStatusList;
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatusName;
        TextView tvStatusCreatedDate;
        TextView tvStatusAuthorId;

        public StatusViewHolder(@NonNull View viewItem) {
            super(viewItem);

            tvStatusName = viewItem.findViewById(R.id.tv_status_name);
            tvStatusCreatedDate = viewItem.findViewById(R.id.tv_status_created_date);
            tvStatusAuthorId = viewItem.findViewById(R.id.tv_status_author_id);
        }
    }
}
