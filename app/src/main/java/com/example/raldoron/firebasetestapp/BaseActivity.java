package com.example.raldoron.firebasetestapp;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Raldoron on 29.06.17.
 */

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        getToolbar();
    }

    public Toolbar getToolbar() {
        if (toolbar == null){
            toolbar = (Toolbar) findViewById(R.id.app_toolbar);
            if (toolbar != null){
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }
}
