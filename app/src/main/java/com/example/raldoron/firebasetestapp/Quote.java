package com.example.raldoron.firebasetestapp;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Raldoron on 18.05.17.
 */

@IgnoreExtraProperties
public class Quote {

    public String author;
    public String book;
    public String text;

    public Quote() {
    }

    public Quote(String author, String book, String text){
        this.author = author;
        this.book = book;
        this.text = text;
    }

}
