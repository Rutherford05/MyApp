package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Connexion extends AppCompatActivity {
    private Button connect;
    private TextInputEditText email;
    private TextInputEditText password;
    String mail,mot_de_passe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        email=(TextInputEditText) findViewById(R.id.valid_email);
        password=(TextInputEditText) findViewById(R.id.valid_password);
        connect=(Button) findViewById(R.id.button2);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void creer(View v){
        Intent gerline= new Intent(Connexion.this,MainActivity.class);
        startActivity(gerline);
    }
}
