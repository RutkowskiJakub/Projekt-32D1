package com.example.planertreningow.treningi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planertreningow.R;
import com.example.planertreningow.treningi.encje.Cwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteCwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteTreningCwiczenie;
import com.example.planertreningow.treningi.tables.MySQLiteTreningi;

import java.util.ArrayList;

public class DodajTrening extends AppCompatActivity {
    private com.example.planertreningow.treningi.encje.Trening trening;
    private ArrayList<Cwiczenie> cwiczenia;

    //    baza danych
    MySQLiteTreningi treningiDB;
    MySQLiteTreningCwiczenie treningCwiczenieDB;
    MySQLiteCwiczenie cwiczenieDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_trening);

        checkIfExtras();
    }

    public void NavigateTreningi(View view){
        startActivity(new Intent(this, Treningi.class));
    }

    public void NavigateTrening(View view){
//        pobieram nazwę treningu i tworzę nowy obiekt trening
        EditText training_name = (EditText)findViewById(R.id.training_name);
        trening = new com.example.planertreningow.treningi.encje.Trening(training_name.getText().toString());
//        dodanie treningu do tabeli z treningami
        treningiDB = new MySQLiteTreningi(this);
        treningiDB.dodaj(trening);

//        przekazuje dalej trening
        Intent intent = new Intent(this, Trening.class);
        intent.putExtra("trening", trening);

        startActivity(intent);
    }

    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            trening = (com.example.planertreningow.treningi.encje.Trening) extras.getSerializable("trening");
            EditText treningName = (EditText)findViewById(R.id.training_name);
            treningName.setText(trening.getName());
            // cwiczenia powiązane z treningiem
            getAllCwiczenia();
        }
    }

    public void getAllCwiczenia(){
//        Toast.makeText(getApplicationContext(), trening.getName(), Toast.LENGTH_SHORT);
    }
}
