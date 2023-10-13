package com.example.dropnote.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dropnote.R;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {

    Context context;
    ArrayList<Items> itemsArrayList;

    public ProjectAdapter(Context context, ArrayList<Items> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.project_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Items items = itemsArrayList.get(position);
        holder.projectName.setText(items.name);
        holder.projectDate.setText(items.date);
        holder.projectTime.setText(items.time);
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView projectName, projectDate, projectTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.project_name);
            projectDate = itemView.findViewById(R.id.project_date);
            projectTime = itemView.findViewById(R.id.project_time);
        }
    }
}
