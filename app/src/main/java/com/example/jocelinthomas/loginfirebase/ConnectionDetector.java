package com.example.jocelinthomas.loginfirebase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 26-02-2018.
 */

public class ConnectionDetector {


    private Context context;

    public ConnectionDetector(Context context){
        this.context = context;
    }
    public boolean isConnectingToInternet() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

}
