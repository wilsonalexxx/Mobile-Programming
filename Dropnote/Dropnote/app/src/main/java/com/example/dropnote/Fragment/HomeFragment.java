package com.example.dropnote.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.dropnote.Adapter.AssignmentAdapter;
import com.example.dropnote.Adapter.Items;
import com.example.dropnote.Adapter.EventAdapter;
import com.example.dropnote.Adapter.ProjectAdapter;
import com.example.dropnote.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    private TextClock clock;
    private ArrayList<Items> itemsArrayList;
    private String[] itemsNameList;
    private String[] itemsDateList;
    private String[] itemsTimeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        clock = v.findViewById(R.id.time);
        clock.setFormat12Hour("dd MMMM yyyy kk:mm:ss");

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataEvent();
        RecyclerView eventList = view.findViewById(R.id.list_event);
        eventList.setLayoutManager(new LinearLayoutManager(getContext()));
        eventList.setHasFixedSize(true);
        EventAdapter eventAdapter = new EventAdapter(getContext(), itemsArrayList);
        eventList.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();

        dataProject();
        RecyclerView projectList = view.findViewById(R.id.list_project);
        projectList.setLayoutManager(new LinearLayoutManager(getContext()));
        projectList.setHasFixedSize(true);
        ProjectAdapter projectAdapter = new ProjectAdapter(getContext(), itemsArrayList);
        projectList.setAdapter(projectAdapter);
        projectAdapter.notifyDataSetChanged();

        dataAssignment();
        RecyclerView assignmentList = view.findViewById(R.id.list_assignment);
        assignmentList.setLayoutManager(new LinearLayoutManager(getContext()));
        assignmentList.setHasFixedSize(true);
        AssignmentAdapter assignmentAdapter = new AssignmentAdapter(getContext(), itemsArrayList);
        assignmentList.setAdapter(assignmentAdapter);
        assignmentAdapter.notifyDataSetChanged();
    }

    private void dataEvent() {
        itemsArrayList = new ArrayList<>();

        itemsNameList = new String[]{
                "Biology Test",
                "Physics Presentation",
                "Database Quiz",
                "Calculus Test",
                "Algorithm Quiz",
                "Chemistry Presentation"
        };
        
        itemsDateList = new String[]{
                "21 June 2023",
                "25 June 2023",
                "28 June 2023",
                "04 July 2023",
                "07 July 2023",
                "10 July 2023"
        };

        itemsTimeList = new String[]{
                "13:00",
                "14:30",
                "10:00",
                "12:00",
                "09:00",
                "15:00"
        };

        for (int i = 0; i < itemsNameList.length; i++){
            Items items = new Items(itemsNameList[i], itemsDateList[i], itemsTimeList[i]);
            itemsArrayList.add(items);
        }
    }

    private void dataProject() {
        itemsArrayList = new ArrayList<>();

        itemsNameList = new String[]{
                "Mobile Programming",
                "Software Engineering",
                "Database Technology",
        };

        itemsDateList = new String[]{
                "22 June 2023",
                "24 June 2023",
                "28 June 2023"
        };

        itemsTimeList = new String[]{
                "15:00",
                "17:30",
                "12:00"
        };

        for (int i = 0; i < itemsNameList.length; i++){
            Items items = new Items(itemsNameList[i], itemsDateList[i], itemsTimeList[i]);
            itemsArrayList.add(items);
        }
    }

    private void dataAssignment() {
        itemsArrayList = new ArrayList<>();

        itemsNameList = new String[]{
                "Calculus",
                "Embedded System",
                "Linear Algebra",
                "Basic Statistic"
        };

        itemsDateList = new String[]{
                "27 June 2023",
                "30 June 2023",
                "05 July 2023",
                "09 July 2023"
        };

        itemsTimeList = new String[]{
                "11:00",
                "12:30",
                "17:00",
                "15:00"
        };

        for (int i = 0; i < itemsNameList.length; i++){
            Items items = new Items(itemsNameList[i], itemsDateList[i], itemsTimeList[i]);
            itemsArrayList.add(items);
        }
    }
}