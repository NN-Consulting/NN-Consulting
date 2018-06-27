package com.losnn.spacia;

import android.app.Application;
import android.content.Context;

import com.losnn.spacia.utils.ConnectivityReceiver;

public class Spacia extends Application {

    private static Spacia sApplication;
    public static Spacia getApplication() { return sApplication; }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static synchronized Spacia getInstance() {
        return sApplication;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
