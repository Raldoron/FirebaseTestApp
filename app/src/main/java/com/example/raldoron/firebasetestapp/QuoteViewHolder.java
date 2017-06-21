package com.example.raldoron.firebasetestapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Raldoron on 19.05.17.
 */

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    public TextView author;
    public TextView book;
    public TextView quote_text;

    public QuoteViewHolder(View itemView) {
        super(itemView);
        author = (TextView) itemView.findViewById(R.id.author_name);
        book = (TextView) itemView.findViewById(R.id.book_name);
        quote_text = (TextView) itemView.findViewById(R.id.quote_text);
    }

    public void bindToQuote(Quote quote){
        author.setText(quote.author);
        book.setText(quote.book);
        quote_text.setText(quote.text);
    }
}
