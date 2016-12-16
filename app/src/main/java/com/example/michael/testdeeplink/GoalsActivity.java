package com.example.michael.testdeeplink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.apusapps.browser.R;

import com.apusapps.facebook.FacebookLinkUtil;

/**
 * Created by michael on 16-12-14.
 */

public class GoalsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        Intent intent = getIntent();


        // parse deep link info
        FacebookLinkUtil.parseDeepLinkInfo(intent);

        // parse app link data
//        FacebookLinkUtil.createAndParseAppLinkData(intent);

        // listen app link data
        FacebookLinkUtil.listenAppLinkData(this.getApplicationContext(), false);



    }


}
