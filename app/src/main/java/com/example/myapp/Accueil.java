package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Accueil extends AppCompatActivity {
    private Button nouvel,show,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        nouvel=(Button) findViewById(R.id.nouvel);
        show=(Button) findViewById(R.id.liste);
        search=(Button) findViewById( R.id.rechercher);
        search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search= new Intent(Accueil.this,Monsearch.class);
                startActivity(search);
            }
        } );
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shw= new Intent(Accueil.this, Recyclerlist.class);
                startActivity(shw);
            }
        });
        nouvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nouvel= new Intent(Accueil.this,Ajouter.class);
                startActivity(nouvel);
            }
        });

    }
}
