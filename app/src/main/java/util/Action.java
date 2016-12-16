//
//package com.apusapps.facebook;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.letv.android.accountinfo.medal.appInterface.MedalActionInterface;
//import com.letv.android.wallpaper.application.WallpaperApplication;
//import com.letv.android.wallpaper.log.Tags;
//import com.letv.android.wallpaper.log.WallpaperLog;
//import com.letv.leui.common.recommend.report.ReportData;
//import com.letv.leui.common.recommend.widget.LeRecommendViewGroup;
//import com.letv.tracker.agnes.Agnes;
//import com.letv.tracker.agnes.App;
//import com.letv.tracker.agnes.Event;
//import com.letv.tracker.agnes.Widget;
//import com.letv.tracker.enums.EventType;
//import com.letv.tracker.enums.Key;
//import com.letv.tracker.enums.LeUIApp;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadFactory;
//
///*
// App start
// App stop
//
// Show Tab
// Show All Categories
// Show Themes In Categories
// Show Themes In Hot
// Show Themes In Mine
// Show Topic
//
// Show Theme Follow Cancel
// Show Full Wallpaper
// Download Wallpaper
// Set Wallpaper home lock
// Share Wallpaper
//
// Show My Wallpapers
// Show Wallpaper history
// */
//public class Action {
//    private static final String TAG = "userAction";
//    private static MedalActionInterface mMedalActionInterface;
//
//    static class ActionThreadFactory implements ThreadFactory {
//        private static final String NAME = "USER_ACTION";
//
//        @Override
//        public Thread newThread(Runnable r) {
//            Thread thread = new Thread(r, NAME);
//            thread.setDaemon(true);
//            thread.setPriority(Thread.MIN_PRIORITY);
//            return thread;
//        }
//    }
//
//    private static final ExecutorService mUploadExecutor = Executors.newFixedThreadPool(1, new ActionThreadFactory());
//
//    private static App getApp() {
//        return Agnes.getInstance().getApp(LeUIApp.Wallpaper);
//    }
//
//    public static void init() {
//        Log.i(Tags.USER_ACTION, "init");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                Agnes agnes = Agnes.getInstance();
//                agnes.setContext(WallpaperApplication.mContext);
//            }
//        });
//    }
//
//    // App create
//    public static void uploadStartApp() {
//        Log.i(Tags.USER_ACTION, "uploadStartApp");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadStartAppImpl();
//            }
//        });
//    }
//
//    private static void uploadStartAppImpl() {
//        App app = getApp();
//        app.run();
//        app.ready();
//        Agnes.getInstance().report(app);
//    }
//
//    // App stop
//    public static void uploadStopApp() {
//        Log.i(Tags.USER_ACTION, "uploadStopApp");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadStopAppImpl();
//            }
//        });
//    }
//
//    private static void uploadStopAppImpl() {
//        App app = getApp();
//        app.exit();
//        Agnes.getInstance().report(app);
//    }
//
//    // Show tab
//    public static void uploadShowTab(final int index) {
//        Log.i(Tags.USER_ACTION, "uploadShowTab : " + index);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowTabImpl(index);
//            }
//        });
//    }
//
//    private static void uploadShowTabImpl(int index) {
//        String tag = null;
//        switch (index) {
//            case 0:
//                tag = "1";
//                break;
//            case 1:
//                tag = "2";
//                break;
//            case 2:
//                tag = "3";
//                break;
//            case 3:
//                tag = "4";
//                break;
//            case 4:
//                tag = "5";
//                break;
//            default:
//                break;
//        }
//        if (tag == null) {
//            return;
//        }
//        // latest 1
//        // mine 2
//        // top 3
//        // hot 4
//        // settings 5
//        App app = getApp();
//        Widget widget = app.createWidget(tag);
//        Event event = widget.createEvent(EventType.Expose);
//        Agnes.getInstance().report(event);
//    }
//
//    // Show topic cover
//    public static void uploadShowTopicCover(final int positionIndex, final int topicId) {
//        Log.i(Tags.USER_ACTION, "uploadShowTopicCover");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowTopiCovercImpl(positionIndex, topicId);
//            }
//        });
//    }
//
//    private static void uploadShowTopiCovercImpl(int positionIndex, int topicId) {
//        App app = getApp();
//        Widget widget = app.createWidget("1.1");
//        Event event = widget.createEvent(EventType.Expose);
//        event.addProp(Key.Class, positionIndex + "");
//        event.addProp(Key.Content, topicId + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Show all categories
//    public static void uploadShowAllCategories() {
//        WallpaperLog.print(TAG, "uploadShowAllCategories");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });
//    }
//
//    /*
//    private static void uploadShowAllCategoriesImpl() {
//    }
//    */
//
//    public static void uploadShowCategoryInHot(final int id) {
//        WallpaperLog.print(TAG, "uploadShowCategoryInHot");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowCategoryInHotImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowCategoryInHotImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("4.2");
//        Event event = widget.createEvent("EnterHotClass");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadShowCategoryInAll(final int id) {
//        WallpaperLog.print(TAG, "uploadShowCategoryInAll");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowCategoryInAllImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowCategoryInAllImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("4.2.M");
//        Event event = widget.createEvent("EnterHotClass");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadShowAllHotThemes() {
//        WallpaperLog.print(TAG, "uploadShowAllHotThemes");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });
//    }
//
//    /*
//    private static void uploadShowAllHotThemesImpl() {
//    }
//    */
//
//    public static void uploadShowThemeInHot(final int id) {
//        WallpaperLog.print(TAG, "uploadShowThemeInHot");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowThemeInHotImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowThemeInHotImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("4.1");
//        Event event = widget.createEvent("EnterStar");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadShowThemeInAllHot(final int id) {
//        WallpaperLog.print(TAG, "uploadShowThemeInAllHot");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowThemeInAllHotImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowThemeInAllHotImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("4.1.M");
//        Event event = widget.createEvent("EnterStar");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Show themes in category
//    public static void uploadShowThemeInCategory(final int id) {
//        WallpaperLog.print(TAG, "uploadShowThemeInCategory");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowThemeInCategoryImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowThemeInCategoryImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("4.2.D");
//        Event event = widget.createEvent("EnterStar");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Show topic
//    public static void uploadShowTopic(final int topicId) {
//        WallpaperLog.print(TAG, "uploadShowTopic");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowTopicImpl(topicId);
//            }
//        });
//    }
//
//    private static void uploadShowTopicImpl(int topicId) {
//        App app = getApp();
//        Widget widget = app.createWidget("1.1.D");
//        Event event = widget.createEvent(EventType.Expose);
//        event.addProp(Key.Class, topicId + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Show theme
//    public static void uploadShowTheme(final int id) {
//        WallpaperLog.print(TAG, "uploadShowTheme");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowThemeImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowThemeImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("OB");
//        Event event = widget.createEvent(EventType.Expose);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Follow theme
//    public static void uploadFollowTheme(final int id) {
//        WallpaperLog.print(TAG, "uploadFollowTheme");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadSwitchFollowImpl(id, true);
//            }
//        });
//    }
//
//    // Unfollow theme
//    public static void uploadUnfollowTheme(final int id) {
//        WallpaperLog.print(TAG, "uploadUnfollowTheme");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadSwitchFollowImpl(id, false);
//            }
//        });
//    }
//
//    private static void uploadSwitchFollowImpl(int id, boolean action) {
//        App app = getApp();
//        Widget widget = app.createWidget("OB.1");
//        EventType type = null;
//        if (action) {
//            type = EventType.Subscrible;
//        } else {
//            type = EventType.Unsubscrible;
//        }
//        Event event = widget.createEvent(type);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Show full wallpaper
//    public static void uploadShowFullWallpaper(final int id) {
//        WallpaperLog.print(TAG, "uploadShowFullWallpaper");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowFullWallpaperImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowFullWallpaperImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("YL");
//        Event event = widget.createEvent(EventType.Expose);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    // Download wallpaper
//    public static void uploadDownloadWallpaper(final int id) {
//        Log.i(Tags.USER_ACTION, "uploadDownloadWallpaper");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadDownloadWallpaperImpl(id);
//            }
//        });
//    }
//
//    private static void uploadDownloadWallpaperImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("YL.1");
//        Event event = widget.createEvent(EventType.Download);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadSetWallpaperTimes(Context context, final int times) {
//        if (context == null || times < 1) {
//            return;
//        }
//        Log.i(Tags.USER_ACTION, "uploadSetWallpaperTimes : " + times);
//        if (mMedalActionInterface == null) {
//            ServiceConnection connection = new ServiceConnection() {
//
//                public void onServiceDisconnected(ComponentName name) {
//                    mMedalActionInterface = null;
//                }
//
//                @Override
//                public void onServiceConnected(ComponentName name, IBinder service) {
//                    mMedalActionInterface = MedalActionInterface.Stub.asInterface(service);
//                    uploadSetWallpaperTimes(times);
//                }
//            };
//            Intent intent = new Intent();
//            intent.setComponent(new ComponentName("com.letv.android.accountinfo", "com.letv.android.accountinfo.medal.appInterface.MedalActionService"));
//            context.getApplicationContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
//        } else {
//            uploadSetWallpaperTimes(times);
//        }
//    }
//
//    private static void uploadSetWallpaperTimes(final int times) {
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    mMedalActionInterface.doActionContainTimes(13, times);
//                    Log.i(Tags.USER_ACTION, "doActionContainTimes : " + times);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    mMedalActionInterface = null;
//                }
//            }
//        });
//    }
//
//    public static final String HOME_WALLPAPER = "desktop";
//    public static final String LOCK_WALLPAPER = "lock";
//    public static final String ALL_WALLPAPER = "both";
//    // Upload apply wallpaper
//    public static void uploadApplyWallpaper(final String type, final int id) {
//        Log.i(Tags.USER_ACTION, "uploadApplyWallpaper");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadApplyWallpaperImpl(type, id);
//            }
//        });
//    }
//
//    private static void uploadApplyWallpaperImpl(String type, int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("YL.2");
//        Event event = widget.createEvent(EventType.Set);
//        event.addProp(Key.Type, type);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static final String LOCK_SCEEN = "lock_screen";
//    public static final String LOCK_SCEEN_SET_HOME = "lock_home";
//    public static final String LOCK_SCEEN_COLLECT = "lock_collect";
//    public static final String LOCK_SCEEN_NEXT = "lock_next";
//    public static final String LOCK_SCEEN_SHARE = "lock_share";
//    public static final String LOCK_SCEEN_THEME_ICON = "lock_theme";
//
//    public static void uploadClickUse(final String type, final String wallpaperId) {
//        Log.i(Tags.USER_ACTION, "uploadClickUse type : " + type  + " wallpaperId : " + wallpaperId);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickUseImpl(type, wallpaperId);
//            }
//        });
//    }
//
//
//    private static void uploadClickUseImpl(String type, String wallpaperId) {
//        App app = getApp();
//        Widget widget = app.createWidget(LOCK_SCEEN);
//        Event event = widget.createEvent(EventType.Click);
//        event.addProp(Key.Type, type);
//        event.addProp(Key.Content, wallpaperId);
//        Agnes.getInstance().report(event);
//    }
//
//
//    // Share wallpaper
//    public static void uploadShareWallpaper() {
//    }
//
//    // Show downloaded wallpapers
//    public static void uploadShowDownloadedWallpapers() {
//    }
//
//    // Change everyday
//    public static void uploadChangeEveryday(final boolean on) {
//        WallpaperLog.print(TAG, "uploadChangeEveryday");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadChangeEverydayImpl(on);
//            }
//        });
//    }
//
//    private static void uploadChangeEverydayImpl(boolean on) {
//        WallpaperLog.print(TAG, "uploadChangeEverydayImpl");
//        App app = getApp();
//        Widget widget = app.createWidget("5.3");
//        Event event;
//        if (on) {
//            event = widget.createEvent(EventType.Open);
//        } else {
//            event = widget.createEvent(EventType.Close);
//        }
//        Agnes.getInstance().report(event);
//    }
//
//    // Change shake
//    public static void uploadChangeShake(final boolean on) {
//        WallpaperLog.print(TAG, "uploadChangeShake");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadChangeShakeImpl(on);
//            }
//        });
//    }
//
//    private static void uploadChangeShakeImpl(boolean on) {
//        App app = getApp();
//        Widget widget = app.createWidget("5.4");
//        Event event;
//        if (on) {
//            event = widget.createEvent(EventType.Open);
//        } else {
//            event = widget.createEvent(EventType.Close);
//        }
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadToProductorHome(final int id) {
//        WallpaperLog.print(TAG, "uploadClickThemeIcon");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickThemeIconImpl(id);
//            }
//        });
//    }
//
//    private static void uploadClickThemeIconImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("YL.3");
//        Event event = widget.createEvent("EnterStar");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadClickThemeOtherContentTab(final int id) {
//        WallpaperLog.print(TAG, "uploadClickThemeOtherContentTab");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickThemeOtherContentTabImpl(id);
//            }
//        });
//    }
//
//    private static void uploadClickThemeOtherContentTabImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("OB.2");
//        Event event = widget.createEvent(EventType.Expose);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadShowSearchTheme(final int id) {
//        WallpaperLog.print(TAG, "uploadShowSearchTheme");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShowSearchThemeImpl(id);
//            }
//        });
//    }
//
//    private static void uploadShowSearchThemeImpl(int id) {
//        App app = getApp();
//        Widget widget = app.createWidget("6.2");
//        Event event = widget.createEvent("EnterStar");
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadSearchResult(final String content, final boolean result) {
//        WallpaperLog.print(TAG, "uploadSearchResult result = " + result);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadSearchResultImpl(content, result);
//            }
//        });
//    }
//
//    private static void uploadSearchResultImpl(String content, boolean result) {
//        String resultType = result ? "succ" : "fail";
//        App app = getApp();
//        Widget widget = app.createWidget("6.1");
//        Event event = widget.createEvent("search");
//        event.addProp(Key.Content, content + "");
//        event.addProp(Key.Type, resultType);
//        Agnes.getInstance().report(event);
//    }
//
//    // helper
//    public static void helpRelevanceUpload(LeRecommendViewGroup othersView) {
//        Log.i(TAG, "helpRelevanceUpload enable = ");
//        App app = getApp();
//        othersView.initReporter(new ReportData(LeUIApp.Wallpaper, app));
//    }
//
//    // feature 4
//    // ===================================================================================================
//    private static final String SWITCH_SCHEDULE_CHANGE = "5.3";
//    private static final String SWITCH_SHAKE_CHANGE = "5.4";
//    private static final String SERVICE_SCHEDULE_CHANGE_WALLPAPER = "App.1";
//    private static final String SERVICE_SHAKE_CHANGE_WALLPAPER = "App.2";
//
//    private static final String EVENT_GET_SWITCH_STATE = "add_status";
//    private static final String EVENT_SCHEDULE_CHANGE_WALLPAPER = "auto_change";
//    private static final String EVENT_SHAKE_CHANGE_WALLPAPER = "sway_change";
//    private static final String STATE_OPEN = "open";
//    private static final String STATE_CLOSE = "close";
//    private static final String RESULT_SUCCESS = "succ";
//    private static final String RESULT_FAILURE = "fail";
//
//    public static void uploadScheduleChangeSwitchState(final boolean enable) {
//        Log.i(TAG, "uploadScheduleChangeSwitchState enable = " + enable);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadScheduleChangeSwitchStateImpl(enable);
//            }
//        });
//    }
//    private static void uploadScheduleChangeSwitchStateImpl(boolean enable) {
//        Log.i(TAG, "uploadScheduleChangeSwitchStateImpl enable = " + enable);
//        final String state = (enable ? STATE_OPEN : STATE_CLOSE);
//        App app = getApp();
//        Widget widget = app.createWidget(SWITCH_SCHEDULE_CHANGE);
//        Event event = widget.createEvent(EVENT_GET_SWITCH_STATE);
//        event.addProp(Key.Content, state);
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadShakeChangeSwitchState(final boolean enable) {
//        Log.i(TAG, "uploadShakeChangeSwitchState enable = " + enable);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShakeChangeSwitchStateImpl(enable);
//            }
//        });
//    }
//
//    private static void uploadShakeChangeSwitchStateImpl(boolean enable) {
//        Log.i(TAG, "uploadShakeChangeSwitchStateImpl enable = " + enable);
//        final String state = (enable ? STATE_OPEN : STATE_CLOSE);
//        App app = getApp();
//        Widget widget = app.createWidget(SWITCH_SHAKE_CHANGE);
//        Event event = widget.createEvent(EVENT_GET_SWITCH_STATE);
//        event.addProp(Key.Content, state);
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadScheduleChangeResult(final int id, final boolean success) {
//        Log.i(TAG, "uploadScheduleChangeResult result = " + success);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadScheduleChangeResultImpl(id, success);
//            }
//        });
//    }
//
//    private static void uploadScheduleChangeResultImpl(int id, boolean success) {
//        final String result = (success ? RESULT_SUCCESS : RESULT_FAILURE);
//        App app = getApp();
//        Widget widget = app.createWidget(SERVICE_SCHEDULE_CHANGE_WALLPAPER);
//        Event event = widget.createEvent(EVENT_SCHEDULE_CHANGE_WALLPAPER);
//        event.addProp(Key.Type, result);
//        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadShakeChangeResult(final int id, final boolean success) {
//        Log.i(TAG, "uploadShakeChangeResult result = " + success);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadShakeChangeResultImpl(id, success);
//            }
//        });
//    }
//
//    private static void uploadShakeChangeResultImpl(int id, boolean success) {
//        final String result = (success ? RESULT_SUCCESS : RESULT_FAILURE);
//        App app = getApp();
//        Widget widget = app.createWidget(SERVICE_SHAKE_CHANGE_WALLPAPER);
//        Event event = widget.createEvent(EVENT_SHAKE_CHANGE_WALLPAPER);
//        event.addProp(Key.Type, result);
//        // TODO temporarily no id
////        event.addProp(Key.Content, id + "");
//        Agnes.getInstance().report(event);
//    }
//
//    private static final String LATEST_RECOMMENDATIONS = "latest_recommendation";
//    private static final String WALLPAPER_ID = "wallpaper_id";
//    private static final String POSITION = "position";
//
//    private static final String LATEST_MORE_RECOMMENDATIONS = "latest_morerecom";
//
//    private static final String LATEST_HOT_WALLPAPERS = "latest_hotwallpapers";
//    private static final String LATEST_HOT_PRODUCTORS = "latest_hotsubscriptions";
//    private static final String LATEST_HOT_CATEGORIES = "latest_hotcategories";
//
//    public static void uploadClickLatestRecommendation(final int position, final int wallpaperId) {
//        Log.i(TAG, "uploadClickLatestRecommendation position = " + position + ", wallpaperId = " + wallpaperId);
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickLatestRecommendationImpl(position, wallpaperId);
//            }
//        });
//    }
//
//    private static void uploadClickLatestRecommendationImpl(int position, int wallpaperId) {
//        App app = getApp();
//        Widget widget = app.createWidget(LATEST_RECOMMENDATIONS);
//        Event event = widget.createEvent(EventType.Click);
//        event.addProp(POSITION, position + "");
//        event.addProp(WALLPAPER_ID, wallpaperId + "");
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadClickMoreRecommadations() {
//        Log.i(TAG, "uploadClickMoreRecommadations");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickMoreRecommadationsImpl();
//            }
//        });
//    }
//
//    private static void uploadClickMoreRecommadationsImpl() {
//        App app = getApp();
//        Widget widget = app.createWidget(LATEST_MORE_RECOMMENDATIONS);
//        Event event = widget.createEvent(EventType.Click);
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadClickHotWallpapers() {
//        Log.i(TAG, "uploadClickHotWallpapers");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickHotWallpapersImpl();
//            }
//        });
//    }
//
//    private static void uploadClickHotWallpapersImpl() {
//        App app = getApp();
//        Widget widget = app.createWidget(LATEST_HOT_WALLPAPERS);
//        Event event = widget.createEvent(EventType.Click);
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadClickHotProductors() {
//        Log.i(TAG, "uploadClickHotProductors");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickHotProductorsImpl();
//            }
//        });
//    }
//
//    protected static void uploadClickHotProductorsImpl() {
//        App app = getApp();
//        Widget widget = app.createWidget(LATEST_HOT_PRODUCTORS);
//        Event event = widget.createEvent(EventType.Click);
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadClickHotCategories() {
//        Log.i(TAG, "uploadClickHotCategories");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadClickHotCategoriesImpl();
//            }
//        });
//    }
//
//    private static void uploadClickHotCategoriesImpl() {
//        App app = getApp();
//        Widget widget = app.createWidget(LATEST_HOT_CATEGORIES);
//        Event event = widget.createEvent(EventType.Click);
//        Agnes.getInstance().report(event);
//    }
//
//    private static final String CATEGORIES = "categories_v3";
//    private static final String CATEGORY_COLOR = "category_color";
//    private static final String CATEGORY_WALLPAPER = "category_wallpaper";
//
//    public static void uploadClickCategoriesColor(final String color) {
//        Log.i(TAG, "uploadClickCategoriesColor");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                // 分类颜色
//                uploadClickCategoryImpl(color + "", CATEGORY_COLOR);
//            }
//        });
//    }
//
//    public static void uploadClickWallpaperCategory(final int categoryId) {
//        Log.i(TAG, "uploadClickWallpaperCategory");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                // 壁纸分类
//                uploadClickCategoryImpl(categoryId + "", CATEGORY_WALLPAPER);
//            }
//        });
//    }
//
//    protected static void uploadClickCategoryImpl(String content, String type) {
//        App app = getApp();
//        Widget widget = app.createWidget(CATEGORIES);
//        Event event = widget.createEvent(type);
//        event.addProp(Key.Content, content);
//        Agnes.getInstance().report(event);
//    }
//
//    public static void uploadCollect(final int wallpaperId, final String uid, final boolean collect) {
//        if (TextUtils.isEmpty(uid)) {
//            return;
//        }
//        Log.i(TAG, "uploadCollect");
//        mUploadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                uploadCollectImpl(wallpaperId, uid, collect);
//            }
//        });
//    }
//
//    private static final String COLLECT = "collect";
//    private static final String COLLECT_CHANGE = "collect_change";
//    private static final String UID = "uid";
//    private static final String COLLECTED = "collected";
//    private static final String UNCOLLECTED = "uncollected";
//    /**
//     * 收藏成功
//     * @param categoryId
//     * @param collect2
//     * @param uid
//     */
//    protected static void uploadCollectImpl(int wallpaperId, String uid, boolean collect) {
//        App app = getApp();
//        Widget widget = app.createWidget(COLLECT);
//        Event event = null;
//        if (collect) {
//            event = widget.createEvent(COLLECTED);
//        } else {
//            event = widget.createEvent(UNCOLLECTED);
//        }
//        event.addProp(WALLPAPER_ID, wallpaperId + "");
//        event.addProp(UID, uid);
//        Agnes.getInstance().report(event);
//    }
//}
