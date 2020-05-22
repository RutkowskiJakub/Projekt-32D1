package com.example.planertreningow.treningi.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Exercise;

import java.util.ArrayList;

public class ExerciseListAdapter extends ArrayAdapter<Exercise> {

    public ExerciseListAdapter(Context context, ArrayList<Exercise> exercises){
        super(context, 0, exercises);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        get data item for this position
        Exercise exercise = getItem(position);
//        check if existing view is being reused
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_list_item, parent, false);
        }
//        find correct places
        TextView id = convertView.findViewById(R.id.exercise_id_item);
        TextView name = convertView.findViewById(R.id.exercise_name_item);
//        insert data into correct places
        id.setText(String.valueOf(exercise.get_id()));
        name.setText(exercise.getName());
//        return completed view
        return convertView;
    }
}
