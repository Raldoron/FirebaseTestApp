package com.example.raldoron.firebasetestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * Created by Raldoron on 27.06.17.
 */

public class QuoteActivity extends BaseActivity {

    private static final String TAG = "QuoteActivity";

    private Toolbar toolbar;

    private Quote quote;
    private TextView author;
    private TextView book;
    private TextView quoteText;
    private ImageView bookCover;

    private AppCompatButton shareButton;

    private StorageReference storageReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        toolbar = getToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        quote = getIntent().getParcelableExtra("quote");

        author = (TextView) findViewById(R.id.author);
        book = (TextView) findViewById(R.id.book);
        quoteText = (TextView) findViewById(R.id.quote);
        bookCover = (ImageView) findViewById(R.id.book_cover);

        shareButton = (AppCompatButton) findViewById(R.id.share_button);

        author.setText(quote.getAuthor());
        book.setText(quote.getBook());
        quoteText.setText(quote.getText());

        storageReference = FirebaseStorage.getInstance().getReference();
        if (quote.getImage() != null) {
            storageReference.child(quote.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(getBaseContext())
                            .load(uri)
                            .into(bookCover);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Error: " + e.toString());
                }
            });
        }

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

    }
}
