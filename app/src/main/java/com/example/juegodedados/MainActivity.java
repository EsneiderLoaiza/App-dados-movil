package com.example.juegodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvJugador1, tvJugador2;
    private EditText etNumeroJ1, etNumeroJ2;
    private Button btnJugar, btnDado1, btnDado2;

    int Dado1, Dado2;
    int ValorJ1, ValorJ2;
    int c1 = 0, c2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJugador1 = (TextView) findViewById(R.id.tvJugador1);
        tvJugador2 = (TextView) findViewById(R.id.tvJugador2);

        etNumeroJ1 = (EditText) findViewById(R.id.etNumeroJ1);
        etNumeroJ2 = (EditText) findViewById(R.id.etNumeroJ2);

        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnDado1 = (Button) findViewById(R.id.btnDado1);
        btnDado2 = (Button) findViewById(R.id.btnDado2);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        tvJugador1.setText(preferences.getString("valor",""));
        tvJugador2.setText(preferences.getString("valor2",""));

    }

    public void Jugar(View view) {
        Dado1 = (int) (Math.random() * 6 + 1);
        Dado2 = (int) (Math.random() * 6 + 1);

        ValorJ1 = Integer.parseInt(etNumeroJ1.getText().toString());
        ValorJ2 = Integer.parseInt(etNumeroJ2.getText().toString());

        for (int i = 1; i <= 6; i++){
            if (Dado1 == i) {
                btnDado1.setText(String.valueOf(i));
            }
            if (Dado2 == i) {
                btnDado2.setText(String.valueOf(i));
            }
        }

        if (Dado1 + Dado2 == ValorJ1) {
            c1++;
            tvJugador1.setText(String.valueOf(c1));
        }
        if (ValorJ1 == ValorJ2 || Dado1 == 1 || Dado2 == 1) {
            c2++;
            tvJugador2.setText(String.valueOf(c2));
        }
        guardar();
    }

    public void Reset(View view) {
        tvJugador1.setText(String.valueOf(0));
        tvJugador2.setText(String.valueOf(0));
        c1 = 0;
        c2 = 0;
    }

    public void guardar()
    {
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);

        SharedPreferences.Editor Obj_editor = preferencias.edit();
        Obj_editor.putString("valor", tvJugador1.getText().toString());
        Obj_editor.putString("valor2", tvJugador2.getText().toString());
        Obj_editor.commit();
        //finish();
    }
}