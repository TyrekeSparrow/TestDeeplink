package com.apusapps.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michael on 16-12-14.
 */

public class FacebookLinkUtil {
    private static final String DATE_PATTERN = "YYYY-MM-dd HH:mm:ss";

    public static String getCurrentTimeLabel() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        Date date = new Date(System.currentTimeMillis());
        String dateLabel = format.format(date);
        return dateLabel;
    }

























    public static void handleFacebook(Context context) {
        // This function initializes the Facebook SDK, the behavior of Facebook SDK functions are undetermined if this function is not called. It should be called as early as possible.
        FacebookSdk.sdkInitialize(context);
        // Notifies the events system that the app has launched and activate and deactivate events should start being logged automatically. This should be called from the OnCreate method of you application.
        // Now, when people install or engage with your app, you'll see this data reflected in your app's Insights dashboard.
        AppEventsLogger.activateApp(context);

        updateActivateState(context);
        initFacebookAppId(context);

        FacebookLinkUtil.listenAppLinkData(context.getApplicationContext(), true);
    }

    private static final String KEY_FACEBOOK_APP_ID = "com.facebook.sdk.ApplicationId";

    private static void initFacebookAppId(Context context) {
        if (context == null) {
            return;
        }

        // get facebook app id
        String facebookAppId = readFacebookAppId(context);
        // set facebook app id
        if (facebookAppId != null) {
            setFacebookAppId(facebookAppId);
        }
    }

    private static String readFacebookAppId(Context context) {
        if (context == null) {
            return null;
        }

        Context applicationContext = context.getApplicationContext();
        PackageManager packageManager = applicationContext.getPackageManager();
        String packageName = applicationContext.getPackageName();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            String facebookAppId = applicationInfo.metaData.getString(KEY_FACEBOOK_APP_ID);
            Log.i("deepLink", "mFacebookAppId = " + facebookAppId);
            return facebookAppId;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String mFacebookAppId;

    private static void setFacebookAppId(String facebookAppId) {
        mFacebookAppId = facebookAppId;
    }

    private static String getFacebookAppId() {
        return mFacebookAppId;
    }

    private static final String KEY_ACTIVATE = "activate";
    private static final int ACTIVATE_DEFAULT = -1;
    private static final int ACTIVATE_ACTIVATING = 0;
    private static final int ACTIVATE_ACTIVATED = 1;

    private static void updateActivateState(Context context) {
        // get old activate state
        Context applicationContext = context.getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        int oldActivateState = sharedPreferences.getInt(KEY_ACTIVATE, ACTIVATE_DEFAULT);
        Log.i("deepLink", "---- updateActivateState oldActivateState = " + oldActivateState);

        // get new activate state
        int newActivateState = ACTIVATE_DEFAULT;
        switch (oldActivateState) {
            case ACTIVATE_DEFAULT:
                newActivateState = ACTIVATE_ACTIVATING;
                break;
            case ACTIVATE_ACTIVATING:
                newActivateState = ACTIVATE_ACTIVATED;
                break;
            case ACTIVATE_ACTIVATED:
                newActivateState = ACTIVATE_ACTIVATED;
            default:
                // do nothing
                break;
        }
        Log.i("deepLink", "---- updateActivateState newActivateState = " + newActivateState);


        // persistent new activate state
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ACTIVATE, newActivateState);
        editor.apply();;
    }

    private static int getActivateState(Context context) {
        Context applicationContext = context.getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        int currentActivateState = sharedPreferences.getInt(KEY_ACTIVATE, ACTIVATE_DEFAULT);
        Log.i("deepLink", "---- getActivateState currentActivateState = " + currentActivateState);
        return currentActivateState;
    }

    /**
     * @param context application context
     * @param isFromOnCreate is from application onCreate is true or from deep page is false
     */
    public static void listenAppLinkData(final Context context, final boolean isFromOnCreate) {
        Log.i("deepLink", "..... Facebook listenAppLinkData isFromOnCreate = " + isFromOnCreate);
        AppLinkData.fetchDeferredAppLinkData(context,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        Log.i("deepLink", "======================================================");
                        Log.i("deepLink", isFromOnCreate + " isFromOnCreate onDeferredAppLinkDataFetched appLinkData = " + appLinkData);
                        if (appLinkData != null) {
                            // is is activating && is from onCreate, it is facebook install activate action
                            // check is activating
                            boolean isActivating = false;
                            if (isFromOnCreate && (getActivateState(context) == ACTIVATE_ACTIVATING)) {
                                isActivating = true;
                            }

                            // parse facebook app link
                            FacebookAppLink facebookAppLink = parseAppLinkDataFromListener(appLinkData);

                            // upload facebook app link
                            // TODO
                            // check network is available
                            FacebookLinkUploader.uploadAppLink(facebookAppLink, isActivating);
                        }
                    }
                }
        );
    }

    public static final String KEY_NATIVE_CLASS = "com.facebook.platform.APPLINK_NATIVE_CLASS";

    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_DEEP_LINK_CONTEXT = "deeplink_context";
    public static final String KEY_PROMO_CODE = "promo_code";

    public static final String KEY_APP_ID = "fb_app_id";
    public static final String KEY_TARGET_URL = "target_url";
    public static final String KEY_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
//    private static final String KEY_REFER_APP_LINK = "referer_app_link";

    private static FacebookAppLink parseAppLinkDataFromListener(AppLinkData appLinkData) {
        Bundle bundle = appLinkData.getArgumentBundle();
        if (bundle == null) {return null;}

        Log.i("deepLink", "parseAppLinkDataFromListener bundle = " + bundle);
        // {com.facebook.platform.APPLINK_NATIVE_CLASS=, extras=Bundle[{deeplink_context=Bundle[{promo_code=PROMO-launcher-facebook-allen}]}], target_url=facebook://apus.wonderful/goals/543, com.facebook.platform.APPLINK_NATIVE_URL=facebook://apus.wonderful/goals/543}
        /*
        com.facebook.platform.APPLINK_NATIVE_CLASS=
        extras=Bundle[{deeplink_context=Bundle[{promo_code=PROMO-launcher-facebook-allen}]}]
        target_url=facebook://apus.wonderful/goals/543
        com.facebook.platform.APPLINK_NATIVE_URL=facebook://apus.wonderful/goals/543
        */

        /*
        fb_app_id=670411869784955
        com.facebook.platform.APPLINK_NATIVE_CLASS=null
        promo_code=PROMO-launcher-facebook-allen
        target_url=facebook://apus.wonderful/goals/543
        com.facebook.platform.APPLINK_NATIVE_URL=facebook://apus.wonderful/goals/543

        12-15 16:20:36.969 I/deepLink( 8156): nativeClass =
        12-15 16:20:36.969 I/deepLink( 8156): promoCode = PROMO765
        12-15 16:20:36.969 I/deepLink( 8156): targetUrl = facebook://apus.wonderful/goals/765
        12-15 16:20:36.969 I/deepLink( 8156): nativeUrl = facebook://apus.wonderful/goals/765

        appId
        nativeClass
        promoCode
        targetUrl
        nativeUrl
        */

        // nativeClass
        String nativeClass = bundle.getString(KEY_NATIVE_CLASS);
        Log.i("deepLink", "nativeClass = " + nativeClass);

        // promoCode
        // extras=Bundle[{deeplink_context=Bundle[{promo_code=PROMO-launcher-facebook-allen}]}]
        Bundle extraBundle = bundle.getBundle(KEY_EXTRAS);
        String promoCode = null;
        if (extraBundle != null) {
            Bundle deepLinkContextBundle = extraBundle.getBundle(KEY_DEEP_LINK_CONTEXT);
            if (deepLinkContextBundle != null) {
                promoCode = deepLinkContextBundle.getString(KEY_PROMO_CODE);
                Log.i("deepLink", "promoCode = " + promoCode);
            }
        }

        // targetUrl
        String targetUrl = bundle.getString(KEY_TARGET_URL);
        Log.i("deepLink", "targetUrl = " + targetUrl);
        // nativeUrl
        String nativeUrl = bundle.getString(KEY_NATIVE_URL);
        Log.i("deepLink", "nativeUrl = " + nativeUrl);
        String appId = getFacebookAppId();
        /*
        private final String appId;
        private final String nativeClass;
        private final String promoCode;
        private final String targetUrl;
        private final String nativeUrl;
        */
        FacebookAppLink facebookAppLink = new FacebookAppLink(appId, nativeClass, promoCode, targetUrl, nativeUrl);
        return facebookAppLink;

    }


    /**
     * @param intent deep link format intent
     */
    /*
    detail action = android.intent.action.VIEW
    detail dataStringDecoded = facebook://apus.wonderful/goals/71?target_url=facebook://apus.wonderful/goals/71
    */
    public static void parseDeepLinkInfo(Intent intent) {
//        if (intent == null) {
//            return;
//        }
        // TODO
        if (true) {
            return;
        }

        Log.i("deepLink", "======================================================");
        if (intent != null) {
            String action = intent.getAction();
            // regex is deep link intent
            Log.i("deepLink", "detail action = " + action);
            String dataString = intent.getDataString();
            String dataStringDecoded = null;
            if (dataString != null) {
                try {
                    dataStringDecoded = URLDecoder.decode(dataString, "UTF-8");
                    Log.i("deepLink", "detail dataStringDecoded = " + dataStringDecoded);
                    // TODO
                    // handle deep link



                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
