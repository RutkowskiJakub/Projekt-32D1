package com.example.planertreningow.ustawienia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.planertreningow.MainActivity;
import com.example.planertreningow.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkIfExtras();
    }
//    Navigation
    public void Back(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void Save(View view){
        EditText username = findViewById(R.id.user_name);
        EditText weight = findViewById(R.id.weight_inp);
        EditText goal_weight = findViewById(R.id.goal_weight_inp);
        EditText strength = findViewById(R.id.strength_in);
        EditText goal_strength  = findViewById(R.id.goal_strength_in);
        user = new User(username.getText().toString(), weight.getText().toString(),
                goal_weight.getText().toString(), strength.getText().toString(), goal_strength.getText().toString());

        startActivity(new Intent(this, MainActivity.class).putExtra("user", user));
    }

    public void checkIfExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            if(extras.getSerializable("user")!=null){
                user = (User) extras.getSerializable("user");
                EditText username = findViewById(R.id.user_name);
                EditText weight = findViewById(R.id.weight_inp);
                EditText goal_weight = findViewById(R.id.goal_weight_inp);
                EditText strength = findViewById(R.id.strength_in);
                EditText goal_strength  = findViewById(R.id.goal_strength_in);

                username.setText(user.getUser_name());
                weight.setText(user.getWeight());
                goal_weight.setText(user.getGoal_weight());
                strength.setText(user.getStrength());
                goal_strength.setText(user.getGoal_strength());
            }// should be always in every activity to get the list of trainings

        }
    } // getting the extras if exist


}
