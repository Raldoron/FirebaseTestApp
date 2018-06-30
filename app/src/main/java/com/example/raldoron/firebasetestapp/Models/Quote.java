package com.example.raldoron.firebasetestapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;


/*
 * Created by Raldoron on 18.05.17.
 */

@IgnoreExtraProperties
public class Quote implements Parcelable {

    private String author;
    private String book;
    private String text;
    private String image;

    public Quote() {
    }

    public Quote(String author, String book, String text, String image){
        this.author = author;
        this.book = book;
        this.text = text;
        this.image = image;
    }

    private Quote(Parcel in) {
        author = in.readString();
        book = in.readString();
        text = in.readString();
        image = in.readString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(book);
        dest.writeString(text);
        dest.writeString(image);
    }
}
