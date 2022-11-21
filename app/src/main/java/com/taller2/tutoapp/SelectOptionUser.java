package com.taller2.tutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectOptionUser extends AppCompatActivity {

    ImageButton OpcionDocente;
    Button OpcionEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_user);

        OpcionDocente = findViewById(R.id.docente);

        OpcionDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSelectAuth();
            }
        });
    }
    private void goToSelectAuth() {
        Intent intent = new Intent(SelectOptionUser.this,RegisterActivity.class);
        startActivity(intent);
    }
}