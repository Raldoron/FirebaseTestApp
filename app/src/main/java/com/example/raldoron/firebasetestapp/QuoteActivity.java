package com.example.raldoron.firebasetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raldoron.firebasetestapp.Models.Quote;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/*
 * Created by Raldoron on 27.06.17.
 */

public class QuoteActivity extends BaseActivity {

    private static final String TAG = "QuoteActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Toolbar toolbar = getToolbar();
        toolbar.setNavigationOnClickListener(v -> finish());

        Quote quote = getIntent().getParcelableExtra("quote");

        TextView author = findViewById(R.id.author);
        TextView book = findViewById(R.id.book);
        TextView quoteText = findViewById(R.id.quote);
        ImageView bookCover = findViewById(R.id.book_cover);

        AppCompatButton shareButton = findViewById(R.id.share_button);

        author.setText(quote.getAuthor());
        book.setText(quote.getBook());
        quoteText.setText(quote.getText());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        if (quote.getImage() != null) {
            storageReference
                    .child(quote.getImage())
                    .getDownloadUrl()
                    .addOnSuccessListener(uri -> Picasso.with(getBaseContext())
                    .load(uri)
                    .into(bookCover))
                    .addOnFailureListener(e -> Log.e(TAG, "Error: " + e.toString()));
        }

        shareButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    getResources().getString(
                            R.string.share_message,
                            quote.getText(),
                            quote.getAuthor(),
                            quote.getBook()
                    )
            );
            intent.setType("text/plain");
            startActivity(intent);
        });

    }
}
