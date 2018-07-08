package com.example.raldoron.firebasetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.raldoron.firebasetestapp.Models.Quote;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * Created by Raldoron on 27.06.17.
 */

public class QuoteActivity extends BaseActivity {


    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.book)
    TextView book;
    @BindView(R.id.quote)
    TextView quoteText;
    @BindView(R.id.book_cover)
    ImageView bookCover;

    private static final String TAG = "QuoteActivity";

    private Quote quote;

    @Override
    public int getLayoutId() {
        return R.layout.activity_quote;
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar.setNavigationOnClickListener(v -> finish());

        quote = getIntent().getParcelableExtra("quote");

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

    }

    @OnClick(R.id.share_button)
    void onShareClick() {
        if (quote != null) {
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
    }

}
