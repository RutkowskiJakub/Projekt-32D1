package com.example.planertreningow.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Training;

import java.util.ArrayList;

public class Exing extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;

    private ArrayList<Training>trainings;
    private ArrayList<Event>events;
    private Exercise exercise;
    private Training training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exing);

        chronometer = findViewById(R.id.chronometer);
        checkIfExtras();
        setup();

    }

    public void Back(View view){
        startActivity(new Intent(this, EventsExercises.class).
                putExtra("trainings", trainings).
                putExtra("events", events).
                putExtra("training", training));
    }

    public void Start(View view){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void Stop(View view){
        if(running){

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();

            running = false;
        }
    }

    public void Reset(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }// should be always in every activity to get the list of trainings
            if(extras.getSerializable("events")!=null){
                events = (ArrayList<Event>)extras.getSerializable("events");
            }
            if(extras.getSerializable("exercise")!=null){
                exercise = (Exercise) extras.getSerializable("exercise");
            }
            if(extras.getSerializable("training")!=null){
                training = (Training) extras.getSerializable("training");
            }
        }
    } // getting the extras if exist

    public void setup(){
        TextView textView15 = findViewById(R.id.textView15);
        TextView textView17 = findViewById(R.id.textView17);
        TextView textView18 = findViewById(R.id.textView18);
        TextView textView19 = findViewById(R.id.textView19);

        TextView textView16 = findViewById(R.id.textView16);
        TextView textView20 = findViewById(R.id.textView20);
        TextView textView21 = findViewById(R.id.textView21);
        TextView textView22 = findViewById(R.id.textView22);

        if(exercise.getSets().size()>=1){
            textView15.setText(String.valueOf(exercise.getSets().get(0).getWeights()));
            textView22.setText(String.valueOf(exercise.getSets().get(0).getRepeats()));
        }
        if(exercise.getSets().size()>=2){
            textView17.setText(String.valueOf(exercise.getSets().get(1).getWeights()));
            textView21.setText(String.valueOf(exercise.getSets().get(1).getRepeats()));
        }
        if(exercise.getSets().size()>=3){
            textView19.setText(String.valueOf(exercise.getSets().get(2).getWeights()));
            textView20.setText(String.valueOf(exercise.getSets().get(2).getRepeats()));
        }
        if(exercise.getSets().size()>=4){
            textView18.setText(String.valueOf(exercise.getSets().get(3).getWeights()));
            textView16.setText(String.valueOf(exercise.getSets().get(3).getRepeats()));
        }

    }
}
