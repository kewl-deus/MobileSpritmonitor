package de.dengot.spritmonitor;

import android.app.Application;
import android.support.v4.app.LoaderManager;

public class SpritmonitorApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LoaderManager.enableDebugLogging(true);
    }

    //TODO close database on app shutdown
}
