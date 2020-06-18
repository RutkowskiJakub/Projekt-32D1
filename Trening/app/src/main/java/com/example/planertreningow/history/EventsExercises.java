package com.example.planertreningow.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.ExerciseListAdapter;
import com.example.planertreningow.treningi.listAdapters.TrainingsListAdapter;

import java.util.ArrayList;

public class EventsExercises extends AppCompatActivity {
    ArrayList<Training> trainings;
    ArrayList<Event>events;
    Training training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_exercises);
        ListView listView = findViewById(R.id.events_exercises);
        checkIfExtras();

        refreshList(listView);

        startExercise(listView);
    }

    private void startExercise(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idToEdit = (TextView)view.findViewById(R.id.exercise_id_item);
                Exercise exercise = training.getExercises().get(Integer.parseInt(idToEdit.getText().toString())-1);

                startActivity(new Intent(getApplicationContext(), Exing.class).
                        putExtra("trainings", trainings).
                        putExtra("events", events).
                        putExtra("training", training).
                        putExtra("exercise", exercise));
            }
        });
    }

    private void refreshList(ListView listView) {
        ExerciseListAdapter adapter = new ExerciseListAdapter(getApplicationContext(), training.getExercises());
        listView.setAdapter(adapter);
    }

    public void Back(View view){
        startActivity(new Intent(this, kalendarz.class).
                putExtra("trainings", trainings).
                putExtra("events", events));
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
            if(extras.getSerializable("training")!=null){
                training = (Training) extras.getSerializable("training");
            }
        }
    } // getting the extras if exist


}
