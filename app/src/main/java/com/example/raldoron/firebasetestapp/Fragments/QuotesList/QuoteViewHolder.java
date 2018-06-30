package com.example.raldoron.firebasetestapp.Fragments.QuotesList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.raldoron.firebasetestapp.Models.Quote;
import com.example.raldoron.firebasetestapp.R;

/*
 * Created by Raldoron on 19.05.17.
 */

class QuoteViewHolder extends RecyclerView.ViewHolder {

    private TextView author;
    private TextView book;
    private TextView quote_text;

    QuoteViewHolder(View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author_name);
        book = itemView.findViewById(R.id.book_name);
        quote_text = itemView.findViewById(R.id.quote_text);
    }

    void bindToQuote(Quote quote){
        author.setText(quote.getAuthor());
        book.setText(quote.getBook());
        quote_text.setText(quote.getText());
    }
}
