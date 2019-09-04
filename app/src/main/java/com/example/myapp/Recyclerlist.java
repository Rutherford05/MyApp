package com.example.myapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recyclerlist extends AppCompatActivity {
      private RecyclerView recyclerView;
      private DatabaseReference reference;
      private ArrayList<Article> articles;
      private Context context=Recyclerlist.this;
      private RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerlist);
        recyclerView=(RecyclerView) findViewById(R.id.myrecyclerview);
        reference= FirebaseDatabase.getInstance().getReference();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        articles= new ArrayList<>();
        init();

    }
    private void init(){
        clearAll();
        Query query=reference.child("Article");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Article mesarticles=new Article();
                    mesarticles.setDésignation((snapshot.child("désignation").getValue().toString()));
                    String prix=snapshot.child("prix").getValue().toString();
                    String unite=snapshot.child("unité").getValue().toString();
                    int prixInt=Integer.parseInt(prix) ;
                    int uniteInt=Integer.parseInt(unite);
                   mesarticles.setPrix(prixInt);
                   mesarticles.setUnité(uniteInt);
                    articles.add(mesarticles);
                }
                recyclerAdapter=new RecyclerAdapter(context,articles);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void clearAll(){
        if(articles!=null){
            articles.clear();
            if (recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        articles= new ArrayList<>();
    }

}
