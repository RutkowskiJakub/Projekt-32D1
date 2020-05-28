package com.example.planertreningow.treningi.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Training;

import java.util.ArrayList;

public class TrainingsListAdapter extends ArrayAdapter<Training> {
//    private final Context context;
//    private final String[] values;

    public TrainingsListAdapter(Context context, ArrayList<Training>trainings){
        super(context, 0, trainings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        get data item for this position
        Training training = getItem(position);
//        check if existing view is being reused
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.training_list_item, parent, false);
        }
//        find correct places
        TextView id = convertView.findViewById(R.id.training_id_list_item);
        TextView name = convertView.findViewById(R.id.training_name_list_item);
//        insert data into correct places
        id.setText(String.valueOf(training.get_id()));
        name.setText(training.getName());
//        return completed view
        return convertView;
    }
}
