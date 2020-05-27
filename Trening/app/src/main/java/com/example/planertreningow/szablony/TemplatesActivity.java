package com.example.planertreningow.szablony;

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
        deleteFromList(templatesList);
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
            }else{
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
    public void deleteFromList(final ListView templatesList){
        templatesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                get right item form list
                TextView idToRemove = view.findViewById(R.id.training_id_list_item);
//                remove exact item
                templates.remove(Integer.parseInt(idToRemove.getText().toString())-1);
//                change the id of list items
                for(Training temp : templates){
                    if(temp.get_id()>Integer.parseInt(idToRemove.getText().toString())){
                        temp.set_id(temp.get_id()-1);
                    }
                }
//                refresh list after deletion
                refreshList(templatesList);
                return true;
            }
        });
    } // delete item from list
    // TODO: 23.05.2020 add possibility to choose template and probably add to my trainings
}
