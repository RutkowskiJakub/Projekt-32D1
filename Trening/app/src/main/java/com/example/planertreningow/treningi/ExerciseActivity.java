package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Set;
import com.example.planertreningow.treningi.encje.Training;
import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    private Training training;
    private ArrayList<Training>trainings;
    private Exercise exercise;
    private ArrayList<Exercise>exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwiczenie);
        trainings = new ArrayList<>();
        training = new Training();
        exercise = new Exercise();
        exercises = new ArrayList<>();

        checkIfExtras();
    }

//    navigate to TrainingActivity
    public void NavigateTraining(View view){
        startActivity(new Intent(this, TrainingActivity.class).
                putExtra("trening", training).
                putExtra("trainings", trainings));
    }
    public void saveExercise(View view){
        EditText exercise_name = (EditText)findViewById(R.id.cwiczenie_name);
        exercise = new Exercise(exercise_name.getText().toString());

        addSets();

        startActivity(new Intent(this, TrainingActivity.class).
                putExtra("training", training).
                putExtra("trainings", trainings));
    }

//    utilities
    public void checkIfExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getSerializable("training") != null) {
                training = (Training) extras.getSerializable("training");
                if (training.getExercises() != null) {
                    exercises = training.getExercises();
                }
            }
            if (extras.getSerializable("trainings") != null) {
                trainings = (ArrayList<Training>) extras.getSerializable("trainings");
            }
            if (extras.getSerializable("exercise") != null) {
                exercise = (Exercise) extras.getSerializable("exercise");
                editing();
            }// if exists then proceed to edit the exercise
        }
    } // checking whether extras exist if so assigning them to variables
    public void editing() {
        EditText exercise_name = findViewById(R.id.cwiczenie_name);
        exercise_name.setText(exercise.getName());
        setSets();
    } // editing and setting the exercise's inputs while editing exercise
    public void addSets(){
        EditText powt1 = (EditText)findViewById(R.id.powt1);
        EditText ciez1 = (EditText)findViewById(R.id.ciez1);

        EditText powt2 = (EditText)findViewById(R.id.powt2);
        EditText ciez2 = (EditText)findViewById(R.id.ciez2);

        EditText powt3 = (EditText)findViewById(R.id.powt3);
        EditText ciez3 = (EditText)findViewById(R.id.ciez3);

        EditText powt4 = (EditText)findViewById(R.id.powt4);
        EditText ciez4 = (EditText)findViewById(R.id.ciez4);

        ArrayList<Set> sets = new ArrayList<>();
        if(powt1.getText().toString().compareTo("")!=0&& ciez1.getText().toString().compareTo("")!=0) {
            sets.add(new Set(Integer.parseInt(powt1.getText().toString()),
                    Double.parseDouble(ciez1.getText().toString())));
        }
        if(powt2.getText().toString().compareTo("")!=0&& ciez2.getText().toString().compareTo("")!=0) {
            sets.add(new Set(Integer.parseInt(powt2.getText().toString()),
                    Double.parseDouble(ciez2.getText().toString())));
        }
        if(powt3.getText().toString().compareTo("")!=0&& ciez3.getText().toString().compareTo("")!=0) {
            sets.add(new Set(Integer.parseInt(powt3.getText().toString()),
                    Double.parseDouble(ciez3.getText().toString())));
        }
        if(powt4.getText().toString().compareTo("")!=0&& ciez4.getText().toString().compareTo("")!=0) {
            sets.add(new Set(Integer.parseInt(powt4.getText().toString()),
                    Double.parseDouble(ciez4.getText().toString())));
        }

        exercise.setSets(sets);
        setID();
        if(training.getExercises()==null){
            training.setExercises(new ArrayList<Exercise>());
        }
        Bundle extras = getIntent().getExtras();
        if(extras.getSerializable("exercise")==null) {
            training.addExercise(exercise);
        }
    }// adding sets, id to exercise then adding exercise to training
    public void setID(){
        if(exercises.size()==0){
            exercise.set_id(1);
        }else{
            int id = 1;
            for(Exercise ex : exercises){
                if(ex.get_id() == id){
                    id++;
                }else{
                    exercise.set_id(id);
                }
            }
            exercise.set_id(id);
        }
    } // set id of the exercise
    public void setSets() {
        ArrayList<Set>sets = exercise.getSets();
//        1 row
        EditText powt1 = (EditText)findViewById(R.id.powt1);
        EditText ciez1 = (EditText)findViewById(R.id.ciez1);
//        2 row
        EditText powt2 = (EditText)findViewById(R.id.powt2);
        EditText ciez2 = (EditText)findViewById(R.id.ciez2);
//        3 row
        EditText powt3 = (EditText)findViewById(R.id.powt3);
        EditText ciez3 = (EditText)findViewById(R.id.ciez3);
//        4 row
        EditText powt4 = (EditText)findViewById(R.id.powt4);
        EditText ciez4 = (EditText)findViewById(R.id.ciez4);

        if(sets.size()>=1){
            powt1.setText(String.valueOf(sets.get(0).getRepeats()));
            ciez1.setText(String.valueOf(sets.get(0).getWeights()));
        }
        if(sets.size()>=2){
            powt2.setText(String.valueOf(sets.get(1).getRepeats()));
            ciez2.setText(String.valueOf(sets.get(1).getWeights()));
        }
        if(sets.size()>=3){
            powt3.setText(String.valueOf(sets.get(2).getRepeats()));
            ciez3.setText(String.valueOf(sets.get(2).getWeights()));
        }
        if(sets.size()>=4){
            powt4.setText(String.valueOf(sets.get(3).getRepeats()));
            ciez4.setText(String.valueOf(sets.get(3).getWeights()));
        }
    } // put data int inputs while editing exercise


}
