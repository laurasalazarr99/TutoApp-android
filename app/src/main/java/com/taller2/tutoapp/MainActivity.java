package com.taller2.tutoapp;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;
    private Button btn_login;
    private Button btn_crearcuenta;


    private String email = "";
    private String password = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email_register = (EditText) findViewById(R.id.correo_login);
        password_register = (EditText) findViewById(R.id.contrasena_login);
        btn_login = (Button) findViewById(R.id.buttonIniciar);
        btn_crearcuenta = (Button) findViewById(R.id.Button_crearcuenta);


        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                email = email_register.getText().toString();
                password = password_register.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser();
                } else {
                    Toast.makeText((MainActivity.this), "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    public void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, InicioActivity.class));
                    finish();
                } else {
                    Toast.makeText((MainActivity.this), "No se pudo iniciar sesión", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}