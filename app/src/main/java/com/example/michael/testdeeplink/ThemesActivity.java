package com.example.michael.testdeeplink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.apusapps.browser.R;

import com.apusapps.facebook.FacebookLinkUtil;

/**
 * Created by michael on 16-12-13.
 */

public class ThemesActivity extends Activity {
    private TextView mDeepInfoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        // find deep link view
        mDeepInfoView = (TextView) findViewById(R.id.deep_info_view);

        Intent intent = getIntent();


        // parse app link data
//        FacebookLinkUtil.createAndParseAppLinkData(intent);
        // parse deep link info
//        FacebookLinkUtil.parseDeepLinkInfo(intent);



        // show deep link
        if (mDeepInfoView != null) {
            String time = FacebookLinkUtil.getCurrentTimeLabel();
//            CharSequence deepInfo = "time = " + time + "\n" + "action = " + action + "\n" + "dataString = " + dataString + "\n" + "dataStringDecoded = " + dataStringDecoded + "\n" + "==============";
//            mDeepInfoView.setText(deepInfo);
        }
    }

}
