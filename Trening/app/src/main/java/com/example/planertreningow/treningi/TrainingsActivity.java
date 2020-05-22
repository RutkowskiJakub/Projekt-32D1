package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planertreningow.MainActivity;
import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Set;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.TrainingsListAdapter;

import java.util.ArrayList;

public class TrainingsActivity extends AppCompatActivity {
    private Training training;
    private ArrayList<Training>trainings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treningi);
//        initialize trainings
        trainings = new ArrayList<>();
        ListView trainingsList = findViewById(R.id.trainings_list);

        CreateExampleTraining();
        checkIfExtras();

//        set adapter
        refreshList(trainingsList);
        deleteFromList(trainingsList);
        edit(trainingsList);
    }

//    navigating in the same places with different purposes
    public void NavigateMainMenu(View view){
        startActivity(new Intent(this, MainActivity.class).
                putExtra("trainings", trainings));
    }

//    navigating in the same places with different purposes
    public void NavigateAddTraining(View view){
        startActivity(new Intent(this, AddTrainingActivity.class).
                putExtra("trainings", trainings));
    }// just put trainings to not loose it
    public void edit(ListView training_list){
        training_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                get right item form list
                TextView idToEdit = view.findViewById(R.id.training_id_list_item);
//                get exact object
                training = trainings.get(Integer.parseInt(idToEdit.getText().toString())-1);
//                pass training and trainingList to next activity to edit mf
                startActivity(new Intent(getApplicationContext(), AddTrainingActivity.class).
                        putExtra("training", training).
                        putExtra("trainings", trainings));
            }
        });
    }// put list and exact item to edit

//    utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }// should be always in every activity to get the list of trainings
        }
    }
    public void deleteFromList(final ListView trainingList){
        trainingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                get right item form list
                TextView idToRemove = view.findViewById(R.id.training_id_list_item);

//                remove exact item
                trainings.remove(Integer.parseInt(idToRemove.getText().toString())-1);

//                refresh list after deletion
                refreshList(trainingList);

                return true;
            }
        });
    }
    public void refreshList(ListView trainingList){
        TrainingsListAdapter adapter = new TrainingsListAdapter(getApplicationContext(), trainings);
        trainingList.setAdapter(adapter);
    }
    public void setID(){
        if(trainings.size()==0){
            training.set_id(1);
        }else{
            int id = 1;
            for(Training train : trainings){
                if(train.get_id() == id){
                    id++;
                }else{
                    training.set_id(id);
                }
            }
            training.set_id(id);
        }
    }

//    just 4 checkout
    public void CreateExampleTraining(){
    ArrayList<Set>set1 = new ArrayList<>();
    set1.add(new Set(8, 60.0));

    ArrayList<Exercise>ex1 = new ArrayList<>();
    ex1.add(new Exercise(1,"ex1", set1));

    training = new Training("Training1", ex1);
//        ustawianie id dla treningów
    if(trainings.size()==0){
        training.set_id(1);
    }else{
        setID();
    }
    trainings.add(training);
}
}
