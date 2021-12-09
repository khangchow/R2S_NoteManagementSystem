package com.r2s.notemanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.model.Category;
import com.r2s.notemanagementsystem.view.dialog.AddNewCategoryDialog;
import com.r2s.notemanagementsystem.view.dialog.EditCategoryDialog;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    Context context;
    ArrayList<Category> categoryArrayList;

    public CategoryAdapter(ArrayList<Category> categoryArrayList, Context context) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    public CategoryAdapter(Context context) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_category, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryArrayList.get(position);

        holder.tvNameCate.setText("Name: " + category.getNameCate());
        holder.tvCreatedDate.setText("Created Date: " + category.getCreatedDate());
    }

    @Override
    public int getItemCount() {
        if (categoryArrayList == null) {
            return 0;
        }
        return categoryArrayList.size();
    }

    public void setTasks(List<Category> information) {
        categoryArrayList = (ArrayList<Category>) information;
        notifyDataSetChanged();
    }

    public ArrayList<Category> getTasks() {
        return categoryArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItemCate;
        TextView tvNameCate, tvCreatedDate;
        AppCompatButton btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCate = itemView.findViewById(R.id.tvNameCategory);
            tvCreatedDate = itemView.findViewById(R.id.tvCreatedDate);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentActivity activity = (FragmentActivity)(context);
                    FragmentManager fm = activity.getSupportFragmentManager();
                    EditCategoryDialog alertDialog = new EditCategoryDialog();
                    alertDialog.show(fm, "fragment_alert");
                }
            });
        }
    }

}
