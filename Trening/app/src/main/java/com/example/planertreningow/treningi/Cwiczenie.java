package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.CwiczenieSeria;
import com.example.planertreningow.treningi.encje.Seria;
import com.example.planertreningow.treningi.tables.MySQLiteCwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteCwiczenieSeria;

import java.util.ArrayList;

public class Cwiczenie extends AppCompatActivity {
    private com.example.planertreningow.treningi.encje.Trening trening;
    private com.example.planertreningow.treningi.encje.Cwiczenie cwiczenie;
    private ArrayList<Seria> serie;

//    bazy danych
    MySQLiteCwiczenie cwiczenieDB;
    MySQLiteCwiczenieSeria cwiczenieSeriaDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwiczenie);

        cwiczenieDB = new MySQLiteCwiczenie(this);
        cwiczenieSeriaDB = new MySQLiteCwiczenieSeria(this);

        Bundle extras = getIntent().getExtras();
        trening =(com.example.planertreningow.treningi.encje.Trening)extras.getSerializable("trening");

    }

    public void NavigateTrening(View view){
        startActivity(new Intent(this, Trening.class).putExtra("trening", trening));
    }

    public void addSets(){
        EditText powt1 = (EditText)findViewById(R.id.powt1);
        EditText ciez1 = (EditText)findViewById(R.id.ciez1);

        EditText powt2 = (EditText)findViewById(R.id.powt2);
        EditText ciez2 = (EditText)findViewById(R.id.ciez2);

        EditText powt3 = (EditText)findViewById(R.id.powt3);
        EditText ciez3 = (EditText)findViewById(R.id.ciez3);

        EditText powt4 = (EditText)findViewById(R.id.powt4);
        EditText ciez4 = (EditText)findViewById(R.id.ciez4);

        serie = new ArrayList<>();
        if(powt1.getText().toString().compareTo("")!=0&& ciez1.getText().toString().compareTo("")!=0) {
            serie.add(new Seria(Integer.parseInt(powt1.getText().toString()),
                    Double.parseDouble(ciez1.getText().toString())));
        }
        if(powt2.getText().toString().compareTo("")!=0&& ciez2.getText().toString().compareTo("")!=0) {
            serie.add(new Seria(Integer.parseInt(powt2.getText().toString()),
                    Double.parseDouble(ciez2.getText().toString())));
        }
        if(powt3.getText().toString().compareTo("")!=0&& ciez3.getText().toString().compareTo("")!=0) {
            serie.add(new Seria(Integer.parseInt(powt3.getText().toString()),
                    Double.parseDouble(ciez3.getText().toString())));
        }
        if(powt4.getText().toString().compareTo("")!=0&& ciez4.getText().toString().compareTo("")!=0) {
            serie.add(new Seria(Integer.parseInt(powt4.getText().toString()),
                    Double.parseDouble(ciez4.getText().toString())));
        }

    }

    public void saveExercise(View view){
        EditText exercise_name = (EditText)findViewById(R.id.cwiczenie_name);
        cwiczenie = new com.example.planertreningow.treningi.encje.Cwiczenie(exercise_name.getText().toString());
        cwiczenieDB.dodaj(cwiczenie);

        cwiczenie = cwiczenieDB.pobierzPoNazwie(cwiczenie.getName());
        addSets();
        for(Seria seria : serie){
//            cwiczenieSeriaDB.dodaj(new CwiczenieSeria(cwiczenie.get_id(), seria));
        }


//                dla każdego elementu list view dodać serie

        Intent intent = new Intent(this, Trening.class);
        intent.putExtra("trening", trening);
        intent.putExtra("cwiczenie", cwiczenie);
        intent.putExtra("serie", serie);
        startActivity(intent);
    }

}
