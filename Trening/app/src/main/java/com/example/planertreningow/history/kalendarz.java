package com.example.planertreningow.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.planertreningow.MainActivity;
import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.TrainingsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class kalendarz extends AppCompatActivity {

    private ArrayList<Training>trainings;
    private ArrayList<Event> events;
    private CalendarView calendarView;
    private Calendar currentlyOn = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView=findViewById(R.id.calendarView);
        checkIfExtras();
        initEvents();


        onItemClick();
    }

    public void AddEvent(View view){
        Event event = new Event(sdf.format(currentlyOn.getTime()));

        startActivity(new Intent(this, AddEvent.class).
                putExtra("trainings", trainings).
                putExtra("events", events).
                putExtra("event", event));
    }
    public void StartExercise(View view){
        // TODO: 12.06.2020 add exercise view
    }
    public void Back(View view){
        startActivity(new Intent(this, MainActivity.class).
                putExtra("events", events));
    }

    public void onItemClick(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date;
                if(month+1>=10){
                    date = dayOfMonth+"."+(month+1)+"."+year;
                }else{
                    date = dayOfMonth+"."+0+(month+1)+"."+year;
                }

                if(date.compareTo(sdf.format(currentlyOn.getTime()))==0){
                    Toast.makeText(getApplicationContext(), "ten sam", Toast.LENGTH_SHORT).show();
                }else{
                    currentlyOn.set(year, month, dayOfMonth);
                    Button btn = findViewById(R.id.event);
                    btn.setVisibility(View.INVISIBLE);
                    for(Event ev : events){
                        if(sdf.format(currentlyOn.getTime()).compareTo(ev.getDate())==0){

                            btn.setVisibility(View.VISIBLE);
                            btn.setText(ev.getTraining().getName());

                            final Training training = ev.getTraining();
                            btn.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getApplicationContext(), EventsExercises.class).
                                            putExtra("trainings", trainings).
                                            putExtra("events", events).
                                            putExtra("training", training));
                                }
                            });
                        }
                    }
                }
            }
        });
    }
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }// should be always in every activity to get the list of trainings
            if(extras.getSerializable("events")!=null){
                events = (ArrayList<Event>)extras.getSerializable("events");
            }else{
                events = new ArrayList<>();
            }
        }
    } // getting the extras if exist

    public void initEvents(){
        events.add(new Event("20.06.2020", trainings.get(0)));
    }



}
