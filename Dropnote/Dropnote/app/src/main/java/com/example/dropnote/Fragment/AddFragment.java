package com.example.dropnote.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dropnote.Database.DatabaseHelper;
import com.example.dropnote.R;
import com.example.dropnote.RegisterActivity;
import com.example.dropnote.model.event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddFragment extends Fragment {
    private EditText edActivityName,edLocation,eddescription;
    private Button btnDate, BtnTime, btnAddActivity;

    private SharedPreferences preferences;

    private String date, time;
    private int Year,Month,Day,Hours,Minutes;

    // Temp
    private long id_event;
    private long id_user;
    private String Activity_name, Activity_date, Activity_time,Activity_location, Activity_desc, Activity_status;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        InitiateViews(view);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopDatePicker();
            }
        });

        BtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopTimePicker();
            }
        });

        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEvent();
            }
        });
        return view;
    }

    private void InitiateViews(View view){
        edActivityName = view.findViewById(R.id.activity_name);
        edLocation = view.findViewById(R.id.activity_location);
        eddescription = view.findViewById(R.id.activity_description);
        BtnTime = view.findViewById(R.id.activity_time);
        btnDate = view.findViewById(R.id.activity_date);
        btnAddActivity = view.findViewById(R.id.add_btn);

        preferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        id_user = preferences.getLong("id", -1);
    }

    private void PopDatePicker(){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = dateFormat.format(calendar.getTime());
                btnDate.setText(date);
                calendar.set(Calendar.YEAR, Year);
                calendar.set(Calendar.MONTH, Month);
                calendar.set(Calendar.DAY_OF_MONTH, Day);
            }
        };
        int Style = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),Style,onDateSetListener,Year,Month,Day);
        datePickerDialog.getDatePicker().setMinDate((System.currentTimeMillis()));
        datePickerDialog.setTitle("Pick Date");
        datePickerDialog.show();
    }

    private void PopTimePicker(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Hours = hourOfDay;
                Minutes = minute;
                time = String.format(Locale.getDefault(), "%02d:%02d", Hours,Minutes);
                BtnTime.setText(time);
            }
        };
        int Style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),Style,onTimeSetListener,Hours,Minutes,true);
        timePickerDialog.setTitle("Pick Time");
        timePickerDialog.show();
    }

    private void AddEvent(){
        Activity_name = edActivityName.getText().toString();
        Activity_date = btnDate.getText().toString();
        Activity_time = BtnTime.getText().toString();
        Activity_location = edLocation.getText().toString();
        Activity_desc = eddescription.getText().toString();

        if (Activity_name.isEmpty() || Activity_location.isEmpty() || Activity_desc.isEmpty()
        || date.equals("dd/mm/yyyy") || time.equals("hh:mm")){
            Toast.makeText(getActivity(), "Fill In The Blank", Toast.LENGTH_SHORT).show();
        } else {
            event event = new event(-1,id_user,Activity_name,Activity_date,Activity_time,Activity_location,Activity_desc,"OnGoing");
            DatabaseHelper helper = new DatabaseHelper(getActivity().getApplicationContext());
            id_event = helper.InsertEvent(event);
            Log.d("MSG", id_event + "");
            Toast.makeText(getActivity(),"Success Add Event",Toast.LENGTH_LONG).show();
            edActivityName.setText("");
            btnDate.setText("dd/mm/yyyy");
            BtnTime.setText("hh:mm");
            edLocation.setText("");
            eddescription.setText("");
        }
    }
}