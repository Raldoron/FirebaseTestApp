package com.example.raldoron.firebasetestapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by Raldoron on 29.06.17.
 */

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

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

    public void checkNetworkConnection() {
        connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isConnectedOrConnecting()){
            Toast.makeText(getBaseContext(), "Internet is not available!", Toast.LENGTH_LONG).show();
        }
    }
}
