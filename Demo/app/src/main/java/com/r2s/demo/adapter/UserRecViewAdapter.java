package com.r2s.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.demo.R;
import com.r2s.demo.databinding.ListUserBinding;
import com.r2s.demo.model.User;

import java.util.List;

public class UserRecViewAdapter extends RecyclerView.Adapter<UserRecViewAdapter.ViewHolder>{

    private List<User> mUsers;

    public UserRecViewAdapter(List<User> mUsers) {
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setmUsers(List<User> mUsers) {
        this.mUsers = mUsers;

        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ListUserBinding binding;

        public ViewHolder(@NonNull ListUserBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }

        public void bind(User user) {
            binding.name.setText(user.getName());
        }
    }
}
