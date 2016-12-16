package com.apusapps.facebook;

import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by michael on 16-12-14.
 */

public class FacebookLinkUploader {



    private static class UploaderThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "UPLOAD_FACEBOOK_APP_LINK_THREAD";

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, THREAD_NAME);
            thread.setDaemon(true);
            thread.setPriority(Thread.MIN_PRIORITY);
            return thread;
        }
    }

    private static final ExecutorService mUploadExecutor = Executors.newFixedThreadPool(1, new UploaderThreadFactory());

    public static void uploadAppLink(final FacebookAppLink appLink, final boolean isActivating) {
        Log.i("deepLink", "uploadAppLink");
        mUploadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                uploadAppLinkImpl(appLink, isActivating);
            }
        });
    }

    private static void uploadAppLinkImpl(FacebookAppLink appLink, boolean isActivating) {
        if (appLink == null) {
            return;
        }

        // TODO
        if (!isActivating) {
            return;
        }

        String isActivatingStr = isActivating ? "1" : "0";
        Log.i("deepLink", "isActivatingStr = " + isActivatingStr);
        String appLinkJson = appLink.toJson();
        Log.i("deepLink", "appLinkJson = " + appLinkJson);
        // key values
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(FacebookUploadUrls.KEY_APP_LINK, appLinkJson);
        params.put(FacebookUploadUrls.KEY_IS_ACTIVATING, isActivatingStr);

        // TODO
        // do post
        // HttpUrlConnectionUtil.doPost(FacebookUploadUrls.URL_UPLOAD_APPLINK, params);








    }


}
