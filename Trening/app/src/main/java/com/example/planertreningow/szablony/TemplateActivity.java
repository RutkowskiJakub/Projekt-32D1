package com.example.planertreningow.szablony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.ExerciseActivity;
import com.example.planertreningow.treningi.TrainingsActivity;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.ExerciseListAdapter;

import java.util.ArrayList;

public class TemplateActivity extends AppCompatActivity {

    private ArrayList<Training> trainings;
    private ArrayList<Training> templates;
    private Training template;

    private boolean editing;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        imageButton = findViewById(R.id.save_template_training);
        template = new Training();
        ListView exerciseList = findViewById(R.id.template_exercise_list);

        checkIfExtras();

        refreshExerciseList(exerciseList);
        edit(exerciseList);
        delete(exerciseList);
    }

//    Navigation
    public void Back(View view){
        Intent intent = new Intent(this, AddTemplateActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("template", template);
        if(editing){
            intent.putExtra("editing", editing);
        }
        startActivity(intent);
    }
    public void Next(View view){
        startActivity(new Intent(this, ExerciseTemplateActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates).
                putExtra("template", template).
                putExtra("editing", editing));
    }
    public void edit(ListView exercise_list){
        exercise_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                get right item from list
                TextView idToEdit = view.findViewById(R.id.exercise_id_item);
//                get exact object
                Exercise exercise = template.getExercises().get(Integer.parseInt(idToEdit.getText().toString())-1);
//                pass extras
                startActivity(new Intent(getApplicationContext(), ExerciseTemplateActivity.class).
                        putExtra("templates", templates).
                        putExtra("trainings", trainings).
                        putExtra("template", template).
                        putExtra("exercise", exercise).
                        putExtra("editing", editing));
        }
        });
    }// pass current training the list and editing item go to ExerciseActivity
    public void Save(View view){
        if(template.getExercises()!=null) {
            if(editing){
                templates.set(template.get_id()-1, template);
            }else{
                templates.add(template);
            }
            startActivity(new Intent(this, TemplatesActivity.class).
                    putExtra("trainings", trainings).
                    putExtra("templates", templates));
        }
    }// passing just the list of trainings to TrainingsActivity
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
                if(template.getExercises()==null){
                    imageButton.setVisibility(View.INVISIBLE);
                }else {
                    imageButton.setVisibility(View.VISIBLE);
                }// if no exercises no button
            }
            if(extras.get("editing")!=null){
                this.editing = (boolean)extras.get("editing");
            }
        }
    }
    public void refreshExerciseList(ListView exercisesList){
        if(template.getExercises()==null){
            template.setExercises(new ArrayList<Exercise>());
        }
        ExerciseListAdapter adapter = new ExerciseListAdapter(getApplicationContext(), template.getExercises());
        exercisesList.setAdapter(adapter);
    }
    public void delete(final ListView exerciseList){
        exerciseList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idToRemove = (TextView)view.findViewById(R.id.exercise_id_item);
                ArrayList<Exercise>exercises = template.getExercises(); // get exercises
                exercises.remove(Integer.parseInt(idToRemove.getText().toString())-1);
                for(Exercise exerc : exercises){
                    if(exerc.get_id()>Integer.parseInt(idToRemove.getText().toString())){
                        exerc.set_id(exerc.get_id()-1);
                    }
                }// updating id's
                template.setExercises(exercises);
                refreshExerciseList(exerciseList);
                return true;
            }
        });
    }
}
