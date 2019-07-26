package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

public class Ajouter extends AppCompatActivity {
    private TextInputEditText CodeA;
    private TextInputEditText Désignat;
    private TextInputEditText Unité;
    private TextInputEditText Prix;
    private Button Add;
    private FirebaseAuth auth;
    Article article;
    DatabaseReference ref;
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
                Float px=Float.parseFloat(Prix.getText().toString().trim());
                int un=Integer.parseInt(Unité.getText().toString().trim());
                article.setCodearticle(CodeA.getText().toString().trim());
                article.setDésignation(Désignat.getText().toString().trim());
                article.setPrix(px);
                article.setUnité(un);
                ref.push().setValue(article);
                Toast.makeText(getApplicationContext(),"data insert successfully",Toast.LENGTH_LONG).show();
                Intent list=new Intent(Ajouter.this,Liste.class);
                startActivity(list);

            }
        });
    }
}
