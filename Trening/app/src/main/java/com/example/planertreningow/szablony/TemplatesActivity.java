package com.example.planertreningow.szablony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planertreningow.MainActivity;
import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;
import com.example.planertreningow.treningi.encje.Set;
import com.example.planertreningow.treningi.encje.Training;
import com.example.planertreningow.treningi.listAdapters.TrainingsListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TemplatesActivity extends AppCompatActivity {

    private ArrayList<Training> trainings;
    private ArrayList<Training> templates;
    private Training template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);

        ListView templatesList = findViewById(R.id.templates_list);

        checkIfExtras();

        refreshList(templatesList);
        edit(templatesList);
        addToTrainings(templatesList);

    }

//  Navigation
    public void Back(View view){
        startActivity(new Intent(this, MainActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates));
    } // passing lists to Main Menu
    public void edit(final ListView templatesList){
        templatesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idToEdit = view.findViewById(R.id.training_id_list_item);
                template = templates.get(Integer.parseInt(idToEdit.getText().toString())-1);
                startActivity(new Intent(getApplicationContext(), AddTemplateActivity.class).
                        putExtra("trainings", trainings).
                        putExtra("templates", templates).
                        putExtra("template", template));
            }
        });
    } // passing lists and editing template to AddTemplateActivity
    public void Next(View view){
        startActivity(new Intent(this, AddTemplateActivity.class).
                putExtra("trainings", trainings).
                putExtra("templates", templates));
    } // passing lists to AddTrainingActivity
// Utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
            }// should be always in every activity to get the list of trainings
            if(extras.getSerializable("templates")!=null){
                templates = (ArrayList<Training>)extras.getSerializable("templates");
            }
            if(templates.size()==0) {
                someTemplates();
            }
        }
    } // getting the extras if exist
    public void someTemplates(){
        templates = new ArrayList<>();
//        adding example training as template
        templates.add(new Training(1,"Klata", new ArrayList<Exercise>(
                Arrays.asList(new Exercise(1, "Wyciskanie", new ArrayList<Set>
                                (Arrays.asList(new Set(8, 40.0), new Set(8, 45.0), new Set(8, 50.0)))),
                            new Exercise(2, "Rozpiętki", new ArrayList<Set>(
                                Arrays.asList(new Set(8, 30.0), new Set(8, 30.0))))))));
        templates.add(new Training(2,"Nogi", new ArrayList<Exercise>(
                Arrays.asList(new Exercise(1, "Przysiady", new ArrayList<Set>
                                (Arrays.asList(new Set(8, 40.0), new Set(8, 45.0), new Set(8, 50.0)))),
                        new Exercise(2, "Wypychanie", new ArrayList<Set>(
                                Arrays.asList(new Set(8, 30.0), new Set(8, 30.0))))))));
    } // just get some example templates
    public void refreshList(ListView templatesList){
//        TrainingsListAdapter adapter = new TrainingsListAdapter(getApplicationContext(), templates);
        TrainingsListAdapter adapter = new TrainingsListAdapter(this, templates);
        templatesList.setAdapter(adapter);
    } // just refreshing the list
    public void addToTrainings(final ListView templatesList){
        templatesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                get right item form list
                TextView idToAdd = view.findViewById(R.id.training_id_list_item);

            // dunno why but couldn't get rid of reference other way
                Training templateToAdd = (Training) templates.get(
                        Integer.parseInt(idToAdd.getText().toString())-1);
                Training newTemplate = new Training(templateToAdd.get_id(),
                        templateToAdd.getName(), templateToAdd.getExercises());

//                set id and add new item to trainings
                newTemplate.set_id(trainings.size()+1);
                trainings.add(newTemplate);
//                show some message for added
                Toast.makeText(getApplicationContext(), "Dodano trening: "+
                        newTemplate.getName(), Toast.LENGTH_SHORT).show();

//                refresh list after deletion
                refreshList(templatesList);
                return true;
            }
        });
    } // delete item from list
}
