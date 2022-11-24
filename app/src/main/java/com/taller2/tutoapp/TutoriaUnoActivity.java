package com.taller2.tutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class TutoriaUnoActivity extends AppCompatActivity {

    LinearLayout ButtonMensajeTUno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoria_uno);

        ButtonMensajeTUno = findViewById(R.id.button_mensaje_t1);

        ButtonMensajeTUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSelectAuth();
            }
        });
    }
    private void goToSelectAuth() {
        Intent intent = new Intent(TutoriaUnoActivity.this,TutoriaUnoMensajeActivity.class);
        startActivity(intent);
    }
}