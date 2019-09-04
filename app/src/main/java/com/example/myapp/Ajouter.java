package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ajouter extends AppCompatActivity {
    private TextInputEditText CodeA;
    private TextInputEditText Désignat;
    private TextInputEditText Unité;
    private TextInputEditText Prix;
    private Button Add;
    private FirebaseAuth auth;
    Article article;
    DatabaseReference ref;
    public static final String Database_Path = "Article";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        auth=FirebaseAuth.getInstance();
        CodeA=(TextInputEditText) findViewById(R.id.code);
        Désignat=(TextInputEditText) findViewById(R.id.design);
        Unité=(TextInputEditText) findViewById(R.id.unit);
        Prix=(TextInputEditText) findViewById(R.id.dat);
        Add=(Button) findViewById(R.id.btn_ajout);
        article= new Article();
        ref= FirebaseDatabase.getInstance().getReference().child("Article");
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=CodeA.getText().toString().trim();
                String Des=Désignat.getText().toString().trim();

                int px= (int) Float.parseFloat(Prix.getText().toString().trim());
                int un=Integer.parseInt(Unité.getText().toString().trim());
                article.setCodearticle(CodeA.getText().toString().trim());
                article.setDésignation(Désignat.getText().toString().trim());
                article.setPrix(px);
                article.setUnité(un);
                //article.setPrix(px);
                //article.setUnité(un);
                ref.push().setValue(article);
                Toast.makeText(getApplicationContext(),"Article ajouté avec succès",Toast.LENGTH_LONG).show();
                Intent list=new Intent(Ajouter.this, Accueil.class);
                startActivity(list);

            }
        });
    }
}
