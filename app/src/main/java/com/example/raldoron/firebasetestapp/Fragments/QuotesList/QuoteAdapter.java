package com.example.raldoron.firebasetestapp.Fragments.QuotesList;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raldoron.firebasetestapp.Models.Quote;
import com.example.raldoron.firebasetestapp.QuoteActivity;
import com.example.raldoron.firebasetestapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Raldoron on 25.06.18.
 */

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private List<Quote> quoteList;

    QuoteAdapter() {
        quoteList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("quotes");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Quote quote = dataSnapshot.getValue(Quote.class);
                quoteList.add(quote);
                notifyItemInserted(getPositionForInsertion());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("========= ", databaseError.getMessage());

            }
        });
    }



    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder quoteViewHolder, int position) {
        quoteViewHolder.bindToQuote(quoteList.get(position));
        quoteViewHolder.itemView.setOnClickListener(view -> {
                        Intent intent = new Intent(view.getContext(), QuoteActivity.class);
                        intent.putExtra("quote", quoteList.get(position));
                        view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return quoteList != null ? quoteList.size() : 0;
    }

    private int getPositionForInsertion() {
        return quoteList.isEmpty() ? 0 : quoteList.size() - 1;
    }
}
