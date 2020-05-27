package com.example.planertreningow.szablony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Training;

import java.util.ArrayList;

public class AddTemplateActivity extends AppCompatActivity {
    private ArrayList<Training> trainings;
    private ArrayList<Training> templates;
    private Training template;
    ImageButton imageButton;
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        template = new Training();
        imageButton = findViewById(R.id.save_training_name);

        checkIfExtras();
    }
//    Navigation
    public void Next(View view){
        Bundle extras = getIntent().getExtras();
        EditText training_name = findViewById(R.id.training_name);
        if(extras.getSerializable("template")==null){
            template = new Training(training_name.getText().toString());
            setID();
        }else {
            template.setName(training_name.getText().toString());
        }
        startActivity(new Intent(this, TemplateActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("template", template).
                putExtra("editing", editing));
    } // go to TemplateActivity
    public void Back(View view){
        startActivity(new Intent(this, TemplatesActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates));
    } // go back to TemplatesActivity
    public void Save(View view){
        EditText trainingName = findViewById(R.id.training_name);
        template.setName((trainingName.getText().toString()));
        templates.set(template.get_id()-1, template);

        startActivity(new Intent(this, TemplatesActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates));
    } // save and go back to TemplatesActivity
//    Utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
//        if extras exist create obj else don't bother
        if(extras!=null){
            if(extras.getSerializable("template")!=null) {
                template = (Training) extras.getSerializable("template");
                EditText trainingName = (EditText) findViewById(R.id.training_name);
                trainingName.setText(template.getName());
                imageButton.setVisibility(View.VISIBLE);
                editing = true;
            }else {
                imageButton.setVisibility(View.INVISIBLE);
            }
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }
            if(extras.getSerializable("templates")!=null){
                templates = (ArrayList<Training>)extras.getSerializable("templates");
            }
        }
    }
    public void setID(){
        if(templates.size()==0){
            template.set_id(1);
        }else{
            int id = 1;
            for(Training temp : templates){
                if(temp.get_id() == id){
                    id++;
                }else{
                    template.set_id(id);
                }
            }
            template.set_id(id);
        }
    }
}
