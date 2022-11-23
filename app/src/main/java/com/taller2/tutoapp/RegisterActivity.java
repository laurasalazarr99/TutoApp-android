package com.taller2.tutoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;



public class RegisterActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;
    private Button btn_register;


    //VARIABLES DATOS

    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference Data_base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        Data_base = FirebaseDatabase.getInstance().getReference();

        email_register = (EditText) findViewById(R.id.correo_registro);
        password_register = (EditText) findViewById(R.id.contrasena_registro);
        btn_register = (Button) findViewById(R.id.btn_registro);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_register.getText().toString();
                password = password_register.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    if (password.length() >=6) {
                    registerUser();
                    }
                    else {
                        Toast.makeText( RegisterActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText( RegisterActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser() {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Map<String, Object> map = new HashMap<>();
                        map.put( "email", email);
                        map.put( "password", password);

                        String id = mAuth.getCurrentUser().getUid();

                        Data_base.child("Docentes").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if (task2.isSuccessful()) {
                                    startActivity(new Intent(RegisterActivity.this,InicioActivity.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText( RegisterActivity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                    else {
                        Toast.makeText( RegisterActivity.this, "Fallo al registrar este usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }));

        }
}