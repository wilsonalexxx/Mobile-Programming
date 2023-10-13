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

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Items> itemsArrayList;

    public AssignmentAdapter(Context context, ArrayList<Items> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.assignment_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Items items = itemsArrayList.get(position);
        holder.assignmentName.setText(items.name);
        holder.assignmentDate.setText(items.date);
        holder.assignmentTime.setText(items.time);
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView assignmentName, assignmentDate, assignmentTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentName = itemView.findViewById(R.id.assignment_name);
            assignmentDate = itemView.findViewById(R.id.assignment_date);
            assignmentTime = itemView.findViewById(R.id.assignment_time);
        }
    }
}
