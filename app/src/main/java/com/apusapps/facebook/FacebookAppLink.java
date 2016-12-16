package com.apusapps.facebook;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by michael on 16-12-15.
 */

public class FacebookAppLink {
    // {com.facebook.platform.APPLINK_NATIVE_CLASS=, extras=Bundle[{deeplink_context=Bundle[{promo_code=PROMO-launcher-facebook-allen}]}], target_url=facebook://apus.wonderful/goals/543, com.facebook.platform.APPLINK_NATIVE_URL=facebook://apus.wonderful/goals/543}
    /*
    com.facebook.platform.APPLINK_NATIVE_CLASS=
    extras=Bundle[{deeplink_context=Bundle[{promo_code=PROMO-launcher-facebook-allen}]}]
    target_url=facebook://apus.wonderful/goals/543
    com.facebook.platform.APPLINK_NATIVE_URL=facebook://apus.wonderful/goals/543
    */

    /*
    fb_app_id=681913831938404
    com.facebook.platform.APPLINK_NATIVE_CLASS=null
    promo_code=PROMO-launcher-facebook-allen
    fb_app_id=681913831938404
    target_url=facebook://apus.wonderful/goals/543
    com.facebook.platform.APPLINK_NATIVE_URL=facebook://apus.wonderful/goals/543
    referer_app_link=Bundle[mParcelledData.dataSize=160]

    appId
    nativeClass
    promoCode
    targetUrl
    nativeUrl
    */


    /*
    public static final String KEY_APP_ID = "fb_app_id";
    public static final String KEY_NATIVE_CLASS = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String KEY_PROMO_CODE = "promo_code";
    public static final String KEY_TARGET_URL = "target_url";
    public static final String KEY_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    */

    private final String appId;
    private final String nativeClass;
    private final String promoCode;
    private final String targetUrl;
    private final String nativeUrl;

    public FacebookAppLink(String appId, String nativeClass, String promoCode, String targetUrl, String nativeUrl) {
        this.appId = appId;
        this.nativeClass = nativeClass;
        this.promoCode = promoCode;
        this.targetUrl = targetUrl;
        this.nativeUrl = nativeUrl;
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        try {
            object.put(FacebookLinkUtil.KEY_APP_ID, appId);
            object.put(FacebookLinkUtil.KEY_NATIVE_CLASS, nativeClass);
            object.put(FacebookLinkUtil.KEY_PROMO_CODE, promoCode);
            object.put(FacebookLinkUtil.KEY_TARGET_URL, targetUrl);
            object.put(FacebookLinkUtil.KEY_NATIVE_URL, nativeUrl);
            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
