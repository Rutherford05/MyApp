package com.example.myapp;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText mNom;
    private TextInputEditText mPrenom;
    private TextInputEditText mEmail;
    private TextInputEditText mMp;
    private TextInputEditText mConfirm;;
    String mN,mP,mE,mC,mM;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private FirebaseAuth auth;
    DatabaseReference ref;
    User user;
     private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        mNom=(TextInputEditText) findViewById(R.id.Nom);
        mPrenom=(TextInputEditText) findViewById(R.id.Prenom);
        mEmail=(TextInputEditText) findViewById(R.id.email);
        mMp=(TextInputEditText) findViewById(R.id.Password);
        mConfirm=(TextInputEditText) findViewById(R.id.Confirmer);
        user= new User();
        ref= FirebaseDatabase.getInstance().getReference().child("User");
        btn2=(Button) findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mN=mNom.getText().toString();
                mP=mPrenom.getText().toString();
                mE=mEmail.getText().toString();
                mM=mMp.getText().toString();
                mC=mConfirm.getText().toString();
                if(!mEmail.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(MainActivity.this,"Entrer une adresse valide",Toast.LENGTH_LONG).show();
                    return;
                }
                  if(TextUtils.isEmpty(mN)){
                    Toast.makeText(getApplicationContext(),"Entrer votre Nom",Toast.LENGTH_LONG).show();
                    return;
                }
                 if(TextUtils.isEmpty(mP)){
                     Toast.makeText(getApplicationContext(),"Entrer votre Prenom",Toast.LENGTH_LONG).show();
                     return;
                }
                  if (TextUtils.isEmpty(mE)){
                     Toast.makeText(getApplicationContext(),"Entrer une adresse Email",Toast.LENGTH_LONG).show();
                     return;
                }
                  if(TextUtils.isEmpty(mM)){
                     Toast.makeText(getApplicationContext(),"Entrer un mot de passe",Toast.LENGTH_LONG).show();
                     return;
                }
                  if(!mConfirm.getText().toString().equals(mMp.getText().toString())){
                     Toast.makeText(MainActivity.this,"Les mots de passe ne se correspondent pas",Toast.LENGTH_LONG).show();
                     return;
                }
                  user.setNom(mNom.getText().toString().trim());
                  user.setPrenom(mPrenom.getText().toString().trim());
                  user.setEmail(mEmail.getText().toString().trim());
                  user.setMot_de_passe(mMp.getText().toString().trim());
                  ref.push().setValue(user);
                  Toast.makeText(MainActivity.this,"Data insert sucessfully",Toast.LENGTH_LONG).show();
                auth.createUserWithEmailAndPassword(mE,mM).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //display some message here
                            startActivity(new Intent(getApplicationContext(),Connexion.class));
                            Toast.makeText(MainActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

    }

}
