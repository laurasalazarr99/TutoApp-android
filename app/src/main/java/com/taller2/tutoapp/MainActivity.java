package com.taller2.tutoapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ButtonInicioSesion;
    Button ButtonCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonInicioSesion = findViewById(R.id.buttonIniciar);
        ButtonCrearCuenta = findViewById(R.id.btn_registro);


        ButtonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSelectAuth();
            }
        });

    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this,SelectOptionUser.class);
        startActivity(intent);
    }
}