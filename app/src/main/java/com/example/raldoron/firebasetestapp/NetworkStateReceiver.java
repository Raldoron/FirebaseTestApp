package com.example.raldoron.firebasetestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Raldoron on 09.11.17.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isConnectedOrConnecting()){
            Toast.makeText(context, "Internet is not available!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, networkInfo.getTypeName() + " network connected", Toast.LENGTH_LONG).show();
        }
    }
}
