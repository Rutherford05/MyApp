package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<Article> artlist;
    private ArrayList<Article> artistList;


     public RecyclerAdapter(Context context,ArrayList<Article> artlist){
         this.mcontext=context;
         this.artlist=artlist;
         artistList=artlist;
     }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.des.setText(artlist.get(position).getDésignation());
         holder.pr.setText( "P.U: " +artlist.get(position).getPrix()+"F");
         holder.un.setText("Qté: " +artlist.get(position).getUnité());
    }

    @Override
    public int getItemCount() {
        return artlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView des,pr,un;
        SearchView search;
        public ViewHolder(View itemView){
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.image);
            des=(TextView) itemView.findViewById(R.id.mondes);
            pr=(TextView) itemView.findViewById(R.id.un);
            un=(TextView) itemView.findViewById(R.id.monpr);
            search=(SearchView) itemView.findViewById(R.id.mon_menu);

        }
    }
    public Filter getFilter(){
         return examplefilter;
    }
    private Filter examplefilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Article> filterlist=new ArrayList<>();
            if (constraint==null||constraint.length()==0){
                filterlist.addAll(artlist);
            }else{
                String filterpattern=constraint.toString().toLowerCase().trim();
                for (Article item:artlist){
                    if(item.getDésignation().toLowerCase().contains(filterpattern));
                    filterlist.add(item);
                }
            }
            FilterResults results=new FilterResults();
            results.values=filterlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            artlist.clear();
            artlist.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
