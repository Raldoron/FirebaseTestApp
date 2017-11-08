package com.example.raldoron.firebasetestapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raldoron.firebasetestapp.Quote;
import com.example.raldoron.firebasetestapp.QuoteActivity;
import com.example.raldoron.firebasetestapp.ViewHolders.QuoteViewHolder;
import com.example.raldoron.firebasetestapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Raldoron on 17.05.17.
 */

public class QuotesListFragment extends Fragment {

    private static final String TAG = "QuotesList";

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Quote, QuoteViewHolder> adapter;

    public QuotesListFragment(){
    }

    public static QuotesListFragment getInstance() {
        QuotesListFragment quotesListFragment = new QuotesListFragment();
        quotesListFragment.setArguments(new Bundle());
        return quotesListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.quotes_list);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Quote quote = dataSnapshot.getValue(Quote.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed: " + databaseError.toException());
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<Quote, QuoteViewHolder>(
                Quote.class,
                R.layout.item_list,
                QuoteViewHolder.class,
                databaseReference.child("quotes")
        ) {
            @Override
            protected void populateViewHolder(QuoteViewHolder viewHolder, final Quote quote, int position) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), QuoteActivity.class);
                        intent.putExtra("quote", quote);
                        startActivity(intent);
                    }
                });
                viewHolder.bindToQuote(quote);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }
}
