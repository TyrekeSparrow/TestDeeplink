package com.example.michael.testdeeplink;

import android.app.Application;

import com.apusapps.facebook.FacebookLinkUtil;


/**
 * Created by michael on 16-12-13.
 */

public class WonderfulApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookLinkUtil.handleFacebook(this);
    }



}
