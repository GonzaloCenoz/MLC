package com.gonzalocenoz.mlc.view;

import android.app.Application;

public class MLCApplication extends Application {

    private static MLCApplication instance;

    public static MLCApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
