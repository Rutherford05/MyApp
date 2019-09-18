package com.example.myapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Monsearch extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private ArrayList<Article> articles;
    private Context context=Monsearch.this;
    private RecyclerAdapter recyclerAdapter;
    private DataSnapshot snapshot;
    SearchView searchView;
    private Query mquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerlist);
        searchView=(SearchView) findViewById(R.id.mon_menu);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.mon_menu));
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (recyclerAdapter!=null){
                    recyclerAdapter.getFilter().filter(newText);
                }
                return true;
            }
        } );
        return super.onCreateOptionsMenu(menu);
    }
}
