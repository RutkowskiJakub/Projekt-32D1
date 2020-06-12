package com.example.planertreningow.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.TrainingsListAdapter;

import java.util.ArrayList;

public class AddEvent extends AppCompatActivity {

    private ArrayList<Training>trainings;
    private ArrayList<Event>events;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ListView addEvent = findViewById(R.id.add_event_list);
        checkIfExtras();

        refreshList(addEvent);
        add(addEvent);
    }

    private void add(ListView addEvent) {
        addEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idToEdit = view.findViewById(R.id.training_id_list_item);
                event.setTraining(trainings.get(Integer.parseInt(idToEdit.getText().toString())-1));
                events.add(event);

                startActivity(new Intent(getApplicationContext(), kalendarz.class).
                        putExtra("trainings", trainings).
                        putExtra("events", events));
            }
        });
    }

    private void refreshList(ListView addEvent) {
        TrainingsListAdapter adapter = new TrainingsListAdapter(getApplicationContext(), trainings);
        addEvent.setAdapter(adapter);
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
            if(extras.getSerializable("events")!=null) {
                events = (ArrayList<Event>) extras.getSerializable("events");
            }
            if(extras.getSerializable("event")!=null) {
                event = (Event) extras.getSerializable("event");
            }
        }
    } // getting the extras if exist

}
