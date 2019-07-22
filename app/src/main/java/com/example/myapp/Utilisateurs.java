package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Utilisateurs extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateurs);
        firebaseAuth=FirebaseAuth.getInstance();
        utilisateur=(TextView) findViewById(R.id.myuser);
        user=firebaseAuth.getCurrentUser();
        utilisateur.setText(user.getEmail());
    }
    public void logout(View v){
        firebaseAuth.signOut();
        finish();
        Intent i=new Intent(this,Connexion.class);
        startActivity(i);
    }
}
