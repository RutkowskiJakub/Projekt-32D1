package com.example.planertreningow.szablony;

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

public class ExerciseTemplateActivity extends AppCompatActivity {
    private ArrayList<Training>trainings;
    private ArrayList<Training>templates;
    private Training template;
    private Exercise exercise;
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_template);
        trainings = new ArrayList<>();
        templates = new ArrayList<>();
        template = new Training();
        exercise = new Exercise();

        checkIfExtras();
    }

//    Navigation
    public void Save(View view){
        EditText exerciseName = findViewById(R.id.exercise_name);
        exercise.setName(exerciseName.getText().toString());

        addSets();

        Intent intent = new Intent(this, TemplateActivity.class);
        intent.putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("template", template).
                putExtra("editing", editing);

        startActivity(intent);

    }
    public void Back(View view){
        Intent intent = new Intent(this, TemplateActivity.class);
        intent.putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("template", template).
                putExtra("editing", editing);

        startActivity(intent);
    }
//    Utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                this.trainings = (ArrayList<Training>) extras.getSerializable("trainings");
            }
            if(extras.getSerializable("templates")!=null){
                this.templates = (ArrayList<Training>) extras.getSerializable("templates");
            }
            if(extras.getSerializable("template")!=null) {
                this.template = (Training) extras.getSerializable("template");
            }
            if(extras.getSerializable("editing")!=null){
                editing = (boolean)extras.getSerializable("editing");
            }
            if(extras.getSerializable("exercise")!=null){
                this.exercise = (Exercise) extras.getSerializable("exercise");
                editing();
            }
        }
    }
    private void editing() {
        EditText exerciseName = findViewById(R.id.exercise_name);
        exerciseName.setText(exercise.getName());
        setSets();
    }
    private void addSets() {
        EditText powt1 = findViewById(R.id.powt1);
        EditText ciez1 = findViewById(R.id.ciez1);
        EditText powt2 = findViewById(R.id.powt2);
        EditText ciez2 = findViewById(R.id.ciez2);
        EditText powt3 = findViewById(R.id.powt3);
        EditText ciez3 = findViewById(R.id.ciez3);
        EditText powt4 = findViewById(R.id.powt4);
        EditText ciez4 = findViewById(R.id.ciez4);

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

        if(template.getExercises()==null){
            template.setExercises(new ArrayList<Exercise>());
        }
//        if editing then change the data
        if(getIntent().getExtras().getSerializable("exercise")==null){
            setID();
            template.addExercise(exercise);
        }else{
            template.getExercises().set(exercise.get_id()-1, exercise);// get id -1 cuz the id;s are 1 higher than table indexes
        }
    }
    private void setID() {
        if(template.getExercises().size()==0){
            exercise.set_id(1);
        }else{
            int id = 1;
            for(Exercise ex : template.getExercises()){
                if(ex.get_id() == id){
                    id++;
                }else{
                    exercise.set_id(id);
                }
            }
            exercise.set_id(id);
        }
    }
    private void setSets(){
        ArrayList<Set> sets = exercise.getSets();
        //        1 row
        EditText powt1 = findViewById(R.id.powt1);
        EditText ciez1 = findViewById(R.id.ciez1);
        //        2 row
        EditText powt2 = findViewById(R.id.powt2);
        EditText ciez2 = findViewById(R.id.ciez2);
        //        3 row
        EditText powt3 = findViewById(R.id.powt3);
        EditText ciez3 = findViewById(R.id.ciez3);
        //        4 row
        EditText powt4 = findViewById(R.id.powt4);
        EditText ciez4 = findViewById(R.id.ciez4);

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
    }
}
