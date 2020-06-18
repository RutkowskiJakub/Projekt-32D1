package com.example.planertreningow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.planertreningow.history.Event;
import com.example.planertreningow.history.kalendarz;
import com.example.planertreningow.szablony.TemplatesActivity;
import com.example.planertreningow.treningi.TrainingsActivity;
import com.example.planertreningow.treningi.encje.Training;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Training>trainings = new ArrayList<>();
    private ArrayList<Training>templates = new ArrayList<>();
    private ArrayList<Event>events = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        checkIfExtras();
    }
    @Override
    protected void onStart() {
        super.onStart();
        parseAndLoad();
    }

//    Navigation
    public void NavigateHistoryActivity(View view){
        startActivity(new Intent(this, kalendarz.class).
                putExtra("trainings", trainings).
                putExtra("events", events));
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

//    Utilities
    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trainings")!=null){
                trainings = (ArrayList<Training>)extras.getSerializable("trainings");
                if(trainings.size()!=0) {
                    parseAndSave();
                }
            }// should be always in every activity to get the list of trainings
            if(extras.getSerializable("templates")!=null){
                templates = (ArrayList<Training>)extras.getSerializable("templates");
                if(templates.size()!=0) {
                    parseAndSave();
                }
            }// should be always in every activity to get the list of trainings
            if(extras.getSerializable("events")!=null){
                events = (ArrayList<Event>)extras.getSerializable("events");
                if(events.size()!=0) {
                    parseAndSave();
                }
            }// should be always in every activity to get the list of trainings
        }
    } // getting the extras if exist
    public void parseAndSave(){
        Gson gson = new Gson();
        String trainingsJson = null;
        String templatesJson = null;
        String eventsJson = null;
        String settingsJson;

        if(trainings.size()!=0){
            trainingsJson = gson.toJson(trainings);
        }
        if(templates.size()!=0){
            templatesJson = gson.toJson(templates);
        }
        if(events.size()!=0){
            eventsJson = gson.toJson(events);
        }
//        add settings here
        try {
            if(trainingsJson!=null) {
                OutputStreamWriter outputStreamWriterTrainings = new OutputStreamWriter(getApplicationContext().openFileOutput("trainings.txt", Context.MODE_PRIVATE));
                outputStreamWriterTrainings.write(trainingsJson);
                outputStreamWriterTrainings.close();
            }
            if(templatesJson!=null) {
                OutputStreamWriter outputStreamWriterTemplates = new OutputStreamWriter(getApplicationContext().openFileOutput("templates.txt", Context.MODE_PRIVATE));
                outputStreamWriterTemplates.write(templatesJson);
                outputStreamWriterTemplates.close();
            }
            if(eventsJson!=null) {
                OutputStreamWriter outputStreamWriterTemplates = new OutputStreamWriter(getApplicationContext().openFileOutput("events.txt", Context.MODE_PRIVATE));
                outputStreamWriterTemplates.write(eventsJson);
                outputStreamWriterTemplates.close();
            }
        }catch (IOException e){
            Log.e("Exception", "File write faled: "+e.toString());
        }

    }
    public void parseAndLoad(){
//        initialize all needed variables and objects
        Gson gson = new Gson();
        Training[]trainingsJsonArray;
        String trainingsJsonString = "";
        Training[]templatesJsonArray;
        String templatesJsonString = "";
        Event[]eventsJsonArray;
        String eventsJsonString = "";

        String settingsJsonString = "";
//        load data from file
        try{
            InputStream inputStream = getApplicationContext().openFileInput("trainings.txt");
            if(inputStream!=null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String recievestring = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((recievestring = bufferedReader.readLine())!=null){
                    stringBuilder.append("").append(recievestring);
                }
                inputStream.close();
                trainingsJsonString = stringBuilder.toString();
            }

            inputStream = getApplication().openFileInput("templates.txt");
            if(inputStream!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String recievestring = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((recievestring = bufferedReader.readLine())!=null){
                    stringBuilder.append("").append(recievestring);
                }

                inputStream.close();
                templatesJsonString = stringBuilder.toString();
            }
            inputStream = getApplicationContext().openFileInput("events.txt");
            if(inputStream!=null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String recievestring = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((recievestring = bufferedReader.readLine())!=null){
                    stringBuilder.append("").append(recievestring);
                }
                inputStream.close();
                eventsJsonString = stringBuilder.toString();
            }

        }catch (FileNotFoundException e){
            Log.e("Exception", "File not found: "+e.toString());
        }catch (IOException e){
            Log.e("Exception", "Can not read file: "+e.toString());
        }

//                set application data
        if(!trainingsJsonString.isEmpty()) {
            trainingsJsonArray = gson.fromJson(trainingsJsonString, Training[].class);
            trainings = new ArrayList<>(Arrays.asList(trainingsJsonArray));
        }
        if(!templatesJsonString.isEmpty()){
            templatesJsonArray = gson.fromJson(templatesJsonString, Training[].class);
            templates = new ArrayList<>(Arrays.asList(templatesJsonArray));
        }
        if(!eventsJsonString.isEmpty()){
            eventsJsonArray = gson.fromJson(eventsJsonString, Event[].class);
            events = new ArrayList<>(Arrays.asList(eventsJsonArray));
        }
        if(!settingsJsonString.isEmpty()){
            // TODO: 28.05.2020 add settings loader here
        }
    }


}
