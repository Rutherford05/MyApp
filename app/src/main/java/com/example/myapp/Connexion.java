package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Connexion extends AppCompatActivity {
    private Button connect;
    private TextInputEditText email;
    private TextInputEditText password;
    private ProgressBar progressBar;
    String mail,mot_de_passe;
    private FirebaseAuth firebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Accueil.class));
        }
        email=(TextInputEditText) findViewById(R.id.valid_email);
        password=(TextInputEditText) findViewById(R.id.valid_password);
        connect=(Button) findViewById(R.id.button2);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mail=email.getText().toString();
                mot_de_passe=password.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(Connexion.this, "Veuillez entrer une adresse", Toast.LENGTH_SHORT).show();
                    return;
                }
                 if(!email.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(getApplicationContext(),"l'adresse saisie n'est pas valide",Toast.LENGTH_LONG).show();
                }
                 if(TextUtils.isEmpty(mot_de_passe)){
                    Toast.makeText(Connexion.this,"Veuillez Entrer un mot de passe",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
    public void creer(View v){
        Intent gerline= new Intent(Connexion.this,MainActivity.class);
        startActivity(gerline);
    }
}
