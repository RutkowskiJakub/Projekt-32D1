package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planertreningow.MainActivity;
import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Trening;
import com.example.planertreningow.treningi.tables.MySQLiteTreningi;

import java.util.ArrayList;
import java.util.Arrays;

public class Treningi extends AppCompatActivity {
    private SimpleCursorAdapter adapter;
    private Trening trening;
//    bazy danych
    MySQLiteTreningi treningiDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treningi);

//        spradzam czy są extras i dodaje je do trening;
        checkIfExtras();

        treningiDB = new MySQLiteTreningi(this);

        this.adapter = new SimpleCursorAdapter(this, R.layout.training_list_item, treningiDB.lista(),
                new String[]{"_id","name"}, new int[]{R.id.training_id_list_item,R.id.training_name_list_item},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        ListView training_list = (ListView)findViewById(R.id.trainings_list);
        training_list.setAdapter(this.adapter);

        //        edycja elementów tablicy
        edit(training_list);
        //        usuwanie elementu z treningów
        deleteFromDB(training_list);

    }

    public void NavigateMainMenu(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void NavigateDodajTrening(View view){
        startActivity(new Intent(this, DodajTrening.class));
    }

    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            trening = (Trening)extras.getSerializable("trening");
        }
    }

    public void deleteFromDB(ListView training_list){
        training_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idToRemove = (TextView)view.findViewById(R.id.training_id_list_item);
                treningiDB.usun(idToRemove.getText().toString());

                adapter.changeCursor(treningiDB.lista());
                adapter.notifyDataSetChanged();

                return true;
            }
        });

    }

    public void edit(ListView training_list){
        training_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idToEdit = (TextView)view.findViewById(R.id.training_id_list_item);
                trening = treningiDB.pobierz(Integer.parseInt(idToEdit.getText().toString()));
//                trening.set_id(Integer.parseInt(idToEdit.getText().toString()));

                startActivity(new Intent(getApplicationContext(), DodajTrening.class).putExtra("trening", trening));
            }
        });
    }
}
