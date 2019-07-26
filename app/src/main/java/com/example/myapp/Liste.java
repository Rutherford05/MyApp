package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.List;

public class Liste extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextInputEditText a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        a=(TextInputEditText) findViewById(R.id.code);
        b=(TextInputEditText) findViewById(R.id.design);
        c=(TextInputEditText) findViewById(R.id.dat);
        d=(TextInputEditText) findViewById(R.id.unit);
        databaseReference=FirebaseDatabase.getInstance().getReference("Article");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String cda=dataSnapshot.child("codearticle").getValue().toString();
               String dsn=dataSnapshot.child("désignation").getValue().toString();
               String px=dataSnapshot.child("prix").getValue().toString();
               String unt=dataSnapshot.child("unité").getValue().toString();
               a.setText(cda);
               b.setText(dsn);
               c.setText(px);
               d.setText(unt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
