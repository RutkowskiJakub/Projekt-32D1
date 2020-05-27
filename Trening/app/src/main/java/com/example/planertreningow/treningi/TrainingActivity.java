package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.ExerciseListAdapter;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    //    every activity must have
    private ArrayList<Training>trainings;
    private ArrayList<Training>templates;

    private Training training;
    private Exercise exercise;
    private ArrayList<Exercise> exercises;

    private ImageButton imageButton;
    private boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening);

        imageButton = findViewById(R.id.save_training);
        exercises = new ArrayList<>();
        ListView exerciseList = findViewById(R.id.exercise_list);

        checkIfExtras();
        getExercises();

        refreshExercise(exerciseList);
        deleteFromList(exerciseList);
        edit(exerciseList);

    }

//    Navigation
    public void Back(View view){
        Intent intent = new Intent(this, AddTrainingActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("training", training);
        if(editing){
            intent.putExtra("editing", editing);
        }
        startActivity(intent);
    }// go back to AddTrainingActivity, pass current training and the list
    public void Next(View view){
        startActivity(new Intent(this, ExerciseActivity.class).
                putExtra("training", training).
                putExtra("templates", templates).
                putExtra("trainings", trainings).
                putExtra("editing", editing));
    }// pass current training and the list of these go to ExerciseActivity
    public void edit(ListView exercise_list){
        exercise_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                get right item from list
                TextView idToEdit = (TextView)view.findViewById(R.id.exercise_id_item);

//                get exact object
                exercise = exercises.get(Integer.parseInt(idToEdit.getText().toString())-1);

//                pass extras
                startActivity(new Intent(getApplicationContext(), ExerciseActivity.class).
                        putExtra("training", training).
                        putExtra("trainings", trainings).
                        putExtra("templates", templates).
                        putExtra("exercise", exercise).
                        putExtra("editing", editing));
            }
        });
    }// pass current training the list and editing item go to ExerciseActivity
    public void Save(View view){
        if(training.getExercises()!=null) {
            if(editing){
                trainings.set(training.get_id()-1, training);
            }else{
                trainings.add(training);
            }
            startActivity(new Intent(this, TrainingsActivity.class).
                    putExtra("templates", templates).
                    putExtra("trainings", trainings));
        }
    }// passing just the list of trainings to TrainingsActivity
//    Utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            if(extras.getSerializable("training")!=null) {
                this.training = (Training) extras.getSerializable("training");
                if(training.getExercises()==null){
                    imageButton.setVisibility(View.INVISIBLE);
                }else {
                    imageButton.setVisibility(View.VISIBLE);
                }
            }
            if(extras.get("editing")!=null){
                this.editing = (boolean)extras.get("editing");
            }

            //            every activity must have these trainings and templates
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }
            if(extras.getSerializable("templates")!=null){
                templates = (ArrayList<Training>)extras.getSerializable("templates");
            }
        }
    }
    public void deleteFromList(final ListView exercise_list){
        exercise_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                get right item
                TextView idToRemove = (TextView)view.findViewById(R.id.exercise_id_item);

//                remove exact item
                exercises.remove(Integer.parseInt(idToRemove.getText().toString())-1);

                for(Exercise exerc : exercises){
                    if(exerc.get_id()>Integer.parseInt(idToRemove.getText().toString())){
                        exerc.set_id(exerc.get_id()-1);
                    }
                }

//                refresh list after deletion
                refreshExercise(exercise_list);

                return true;
            }
        });
    }
    public void refreshExercise(ListView exercisesList){
        ExerciseListAdapter adapter = new ExerciseListAdapter(getApplicationContext(), exercises);
        exercisesList.setAdapter(adapter);
    }
    public void getExercises(){
        if(training.getExercises()!=null){
            exercises = training.getExercises();
        }
    }
}
