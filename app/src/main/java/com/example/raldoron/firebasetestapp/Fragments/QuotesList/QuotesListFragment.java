package com.example.raldoron.firebasetestapp.Fragments.QuotesList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raldoron.firebasetestapp.R;

/*
 * Created by Raldoron on 17.05.17.
 */

public class QuotesListFragment extends Fragment {

    public QuotesListFragment(){
    }

    public static QuotesListFragment getInstance() {
        QuotesListFragment quotesListFragment = new QuotesListFragment();
        quotesListFragment.setArguments(new Bundle());
        return quotesListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.quotes_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new QuoteAdapter());

        return view;
    }

}
