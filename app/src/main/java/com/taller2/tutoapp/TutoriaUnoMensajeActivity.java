package com.taller2.tutoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class TutoriaUnoMensajeActivity extends AppCompatActivity {

    private MediaRecorder grabacion; //Objeto con el cual se grabara en la aplicacion
    private String salida = null; // path (ruta) del archivo a grabar
    private ImageButton btnGrabar; //boton que ejecutara la accion de grabar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoria_uno_mensaje);

        btnGrabar = (ImageButton) findViewById(R.id.Button_Grabar);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TutoriaUnoMensajeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
            //Comprobacion para saber si el archivo manifest contiene los permisos, si no los posee, la aplicacion no funcionara
        }
    }

    public void Grabar(View view) {
        if (grabacion == null) {
            salida = getExternalFilesDir(null).getAbsolutePath() + "/Grabacion.mp3";
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            grabacion.setOutputFile(salida);// ruta donde se guardara el audio que se grabe

            try {

                grabacion.prepare(); //se prepara el estado de la grabacion
                grabacion.start();//se inicia el estado de la grabacion

            } catch (IOException e) {

            }
            btnGrabar.setImageResource(R.drawable.audio_grabando);// el boton de grabar cambia de color para saber que se esta grabando
            Toast.makeText(getApplicationContext(), "Grabando....", Toast.LENGTH_SHORT).show();

        } else if (grabacion != null) {
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            btnGrabar.setImageResource(R.drawable.audio_inicio);
            Toast.makeText(getApplicationContext(), "Grabación Finalizada....", Toast.LENGTH_SHORT).show();
        }
    }
        public void  Reproducir(View view){
            if (salida != null){

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(salida);
                    mediaPlayer.prepare();
                }catch (IOException e){

                }
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(), "Reproduciendo Audio...", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "Necesita Grabar un Audio...", Toast.LENGTH_SHORT).show();
            }
        }
}