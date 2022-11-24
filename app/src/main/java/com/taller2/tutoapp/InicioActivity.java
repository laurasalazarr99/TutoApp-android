package com.taller2.tutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class InicioActivity extends AppCompatActivity {

    private LinearLayout ButtonTutoriaUno;
    private TextView CerrarSesion;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mAuth = FirebaseAuth.getInstance();
        CerrarSesion = findViewById(R.id.cerrar_sesion);
        ButtonTutoriaUno = findViewById(R.id.button_materia_t1);

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(InicioActivity.this, MainActivity.class));
                finish();
            }


        });

        ButtonTutoriaUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( InicioActivity.this,TutoriaUnoActivity.class));
            }
        });
    }
    }

