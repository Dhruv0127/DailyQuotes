package com.example.daily_quotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {


    Context context;
    ArrayList<Quote> list;

    public QuoteAdapter(Context context, ArrayList<Quote> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.quote_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.MyViewHolder holder, int position) {
        Quote quoteobj = list.get(position);
        holder.quoteq1.setText(quoteobj.getQuoteq1());
        holder.author1.setText("~ " + quoteobj.getAuthor1()); // Adding "~" prefix
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView key,quoteq1,author1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            quoteq1=itemView.findViewById(R.id.quote_card_tv);
            author1=itemView.findViewById(R.id.author_card_tv);

        }
    }

}
