package com.example.raldoron.firebasetestapp.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.raldoron.firebasetestapp.Quote;
import com.example.raldoron.firebasetestapp.R;

/**
 * Created by Raldoron on 19.05.17.
 */

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    private TextView author;
    private TextView book;
    private TextView quote_text;

    public QuoteViewHolder(View itemView) {
        super(itemView);
        author = (TextView) itemView.findViewById(R.id.author_name);
        book = (TextView) itemView.findViewById(R.id.book_name);
        quote_text = (TextView) itemView.findViewById(R.id.quote_text);
    }

    public void bindToQuote(Quote quote){
        author.setText(quote.getAuthor());
        book.setText(quote.getBook());
        quote_text.setText(quote.getText());
    }
}
