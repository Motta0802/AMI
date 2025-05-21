package com.example.tcc3;

import android.app.Application;

public class MyApp extends Application {
    private String sharedData;

    public String getSharedData() {
        return sharedData;
    }

    public void setSharedData(String sharedData) {
        this.sharedData = sharedData;
    }
}
