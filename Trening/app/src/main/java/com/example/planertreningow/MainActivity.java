package com.example.planertreningow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planertreningow.szablony.TemplatesActivity;
import com.example.planertreningow.treningi.TrainingsActivity;
import com.example.planertreningow.treningi.encje.Training;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Training>trainings = new ArrayList<>();
    private ArrayList<Training>templates = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        checkIfExtras();
    }

    public void NavigateHistoryActivity(View view){
        // TODO: 07.05.2020 add History activity here
//        startActivity(new Intent(this, HistoryActivity.class).
//                putExtra("trainings", trainings).
//                putExtra("templates", templates));
    }

    public void NavigateTemplatesActivity(View view){
        startActivity(new Intent(this, TemplatesActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates));
    }

    public void NavigateTrainingActivity(View view){
        if(trainings!=null){
            startActivity(new Intent(this, TrainingsActivity.class).
                    putExtra("trainings", trainings).
                    putExtra("templates", templates));
        }else {
            startActivity(new Intent(this, TrainingsActivity.class));
        }
    }

    public void NavigateSettings(View view){
        // TODO: 07.05.2020 add Settings Activity here
//        startActivity(new Intent(this, SettingsActivity.class).
//                putExtra("trainings", trainings).
//                putExtra("templates", templates));
    }

    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }// should be always in every activity to get the list of trainings
            if(extras.getSerializable("templates")!=null){
                templates = (ArrayList<Training>)extras.getSerializable("templates");
            }// should be always in every activity to get the list of trainings
        }
    } // getting the extras if exist
}
