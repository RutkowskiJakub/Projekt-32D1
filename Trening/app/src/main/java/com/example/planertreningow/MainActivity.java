package com.example.planertreningow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.planertreningow.treningi.Treningi;
import com.example.planertreningow.treningi.tables.MySQLiteTreningi;

public class MainActivity extends AppCompatActivity {
    MySQLiteTreningi treningi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void NavigateHistoryActivity(View view){
        // TODO: 07.05.2020 add History activity here
//        startActivity(new Intent(this, HistoryActivity.class));
    }

    public void NavigateTemplateActivity(View view){
        // TODO: 07.05.2020 add Template activity here
//        startActivity(new Intent(this, TemplateActivity.class));
    }

    public void NavigateTrainingActivity(View view){
        // TODO: 07.05.2020 add Training activity
        startActivity(new Intent(this, Treningi.class));
    }

    public void NavigateSettings(View view){
        // TODO: 07.05.2020 add Settings Activity here
//        startActivity(new Intent(this, SettingsActivity.class));
    }
}
