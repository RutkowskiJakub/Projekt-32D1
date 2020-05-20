package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planertreningow.MainActivity;
import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.CwiczenieSeria;
import com.example.planertreningow.treningi.encje.Seria;
import com.example.planertreningow.treningi.encje.TreningCwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteCwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteCwiczenieSeria;
import com.example.planertreningow.treningi.tables.MySQLiteTreningCwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteTreningi;

import java.util.ArrayList;
import java.util.Arrays;

public class Trening extends AppCompatActivity {
    private com.example.planertreningow.treningi.encje.Trening trening;
    private com.example.planertreningow.treningi.encje.Cwiczenie cwiczenie;
    private ArrayList<Seria> serie;
    private ArrayList<String>exercises;
    private SimpleCursorAdapter adapter;

    //    bazy danych
    private MySQLiteCwiczenie cwiczenieDB;
    private MySQLiteTreningCwiczenie treningCwiczenieDB;
    private MySQLiteTreningi treningiDB;
    private MySQLiteCwiczenieSeria cwiczenieSeriaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening);

        //  pobieram przesłany z wcześniejszego activity klasę trening
        checkIfExtras();

        //  inicjowanie baz danych
        cwiczenieDB = new MySQLiteCwiczenie(this);
        treningCwiczenieDB = new MySQLiteTreningCwiczenie(this);
        cwiczenieSeriaDB = new MySQLiteCwiczenieSeria(this);

        //  ustawiam adapter dla listy z ćwiczeniami
        this.adapter = new SimpleCursorAdapter(this, R.layout.exercise_list_item, cwiczenieDB.lista(),
                new String[]{"_id", "name"}, new int[]{R.id.cwiczenie_id_item, R.id.cwiczenie_name_item},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        //  dodaje elementy do listy
        ListView exercises_list = (ListView)findViewById(R.id.ecercise_list);
        exercises_list.setAdapter(this.adapter);

        deleteFromDB(exercises_list);
    }

    public void NavigateDodajTrening(View view){
        startActivity(new Intent(this,DodajTrening.class));
    }

    public void NavigateCwiczeneie(View view){
        Intent intent = new Intent(this, Cwiczenie.class);
        intent.putExtra("trening", trening);
        startActivity(intent);
    }

    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("trening")!=null) {
                this.trening = (com.example.planertreningow.treningi.encje.Trening) extras.getSerializable("trening");
            }
            if(extras.getSerializable("cwiczenie")!= null){
                this.cwiczenie = (com.example.planertreningow.treningi.encje.Cwiczenie)extras.getSerializable("cwiczenie");
                addToTreningCwiczenie();
            }
            if(extras.getSerializable("serie")!=null){
                this.serie = (ArrayList<Seria>)extras.getSerializable("serie");
                addToCwiczenieSeria();
            }
        }
    }

    public void dodajTrening(View view){
        startActivity(new Intent(this, Treningi.class).putExtra("trening", trening));
    }
    
    public void addToTreningCwiczenie(){
        // TODO: 20.05.2020 klucz treningu + klucz cwiczenia
//        treningCwiczenieDB.dodaj(new TreningCwiczenie(trening.get_id(), cwiczenie.get_id()));
    }
    
    public void addToCwiczenieSeria(){
        // TODO: 20.05.2020 klucz cwiczenia + seria
        for(Seria seria : serie){
//            cwiczenieSeriaDB.dodaj(new CwiczenieSeria(cwiczenie.get_id(), seria));
        }
    }

    public void edit(ListView exercise_list){
        exercise_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idToEdit = (TextView)view.findViewById(R.id.cwiczenie_id_item);
                cwiczenie = cwiczenieDB.pobierz(Integer.parseInt(idToEdit.getText().toString()));

//                pobrać serie

                Intent intent = new Intent(getApplicationContext(), Cwiczenie.class);
                intent.putExtra("cwiczenie", cwiczenie);
                intent.putExtra("serie", serie);

                startActivity(intent);
            }
        });
    }

    public void deleteFromDB(ListView exercise_list){
        exercise_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idToRemove = (TextView)view.findViewById(R.id.cwiczenie_id_item);
                cwiczenieDB.usun(idToRemove.getText().toString());

                adapter.changeCursor(cwiczenieDB.lista());
                adapter.notifyDataSetChanged();

                return true;
            }
        });

    }
}
