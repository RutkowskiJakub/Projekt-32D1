package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Training;

import java.util.ArrayList;

public class AddTrainingActivity extends AppCompatActivity {
    //    every activity must have
    private ArrayList<Training>trainings;
    private ArrayList<Training>templates;

    private Training training;
    ImageButton imageButton;
    private boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_trening);
        training = new Training();
        imageButton = findViewById(R.id.save_training_name);

        checkIfExtras();
    }

//    Navigation
    public void Back(View view){
        startActivity(new Intent(this, TrainingsActivity.class).
                putExtra("templates", templates).
                putExtra("trainings", trainings));
    } // to TrainingsActivity
    public void Save(View view){
        EditText training_name = findViewById(R.id.training_name);
        training.setName(training_name.getText().toString());
        trainings.set(training.get_id()-1, training);

        startActivity(new Intent(this, TrainingsActivity.class).
                putExtra("templates", templates).
                putExtra("trainings",trainings));
    } // to TrainingsActivity
    public void Next(View view){
//        get extras if there are any
        Bundle extras = getIntent().getExtras();
//        extract the training name form input
        EditText training_name = findViewById(R.id.training_name);
//        if extras then change the name if not create new training entity
        if(extras.getSerializable("training")==null) {
            training = new Training(training_name.getText().toString());
//            if new created id is needed
            setID();
        }else{
            training.setName(training_name.getText().toString());
        }// if passed

//        pass new training to TrainingActivity
        startActivity(new Intent(this, TrainingActivity.class).
                putExtra("training", training).
                putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("editing", editing));
    } // to TrainingActivity
//    Utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
//        if extras exist create obj else don't bother
        if(extras!=null){
            if(extras.getSerializable("training")!=null) {
                training = (Training) extras.getSerializable("training");
                EditText trainingName = (EditText) findViewById(R.id.training_name);
                trainingName.setText(training.getName());
                imageButton.setVisibility(View.VISIBLE);
                editing = true;
            }else {
                imageButton.setVisibility(View.INVISIBLE);
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
}
