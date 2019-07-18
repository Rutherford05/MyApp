package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        create=(TextView) findViewById(R.id.créer);
    }
    public void creer(View c){
        Intent intent= new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
}
