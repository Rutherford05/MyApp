package com.example.myapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    View mView;
    ImageView img;
    TextView des,uni,pr;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
        this.img=itemView.findViewById(R.id.image);
        this.des=itemView.findViewById(R.id.mondes);
        this.uni=itemView.findViewById(R.id.monpr);
        this.pr=itemView.findViewById(R.id.un);
    }

}
