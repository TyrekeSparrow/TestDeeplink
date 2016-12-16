
package util;

public class Urls {
    // common ==================================================================================
    // app name
    public static final String APP_NAME = "wallpaper";
    // charset
    public static final String REQUEST_CHARSET = "UTF-8";
    // separator
    public static final String PARAMS_SEP = "&";
    // scheme
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    // host
    public static final String HOST_WALLPAPER_LOCAL_TEST = "10.176.28.228:31452";
    public static final String HOST_WALLPAPER = "wallpaper.scloud.letv.com";
    public static final String HOST_PRODUCTOR = "xsquare.scloud.letv.com"; 
    // url header key
    public static final String HEAD_REGION_KEY = "region";
    public static final String HEAD_LANGUAGE_KEY = "user-prefer-language";
    // signature
    public static final String WALLPAPER_SECRETKEY = "8hAZL4kFaSnSVmwrlPx2";
    public static final String WALLPAPER_AKEY = "rLpkhJN9phj9G8zcjv2g";
    public static final String XSQUARE_SIGNKEY = "30d94aa732a26d4ee63afc2b7a57619b";
    // keys
    public static final String KEY_TIME ="_time";
    public static final String KEY_AK = "_ak";
    public static final String KEY_SIGN = "_sign";
    public static final String KEY_TOKEN = "sso_tk";
    // sso_tk imei width height source num
    public static final String KEY_IMEI = "imei";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_SOURCES = "source";
    public static final String KEY_CID =  "cid";
    public static final String kEY_NUM = "num";
    // sources
    public static final String SOURCES_RECOMMENDED = "recommend";
    public static final String SOURCES_FOLLOW = "tag";
    public static final String SOURCES_COLLECTION = "fav";
    public static final String SOURCES_RECOMMENDED_FOLLOW = SOURCES_RECOMMENDED + "," + SOURCES_FOLLOW;
    public static final String SOURCES_RECOMMENDED_COLLECTION = SOURCES_RECOMMENDED + "," + SOURCES_COLLECTION;
    public static final String SOURCES_FOLLOW_COLLECTION = SOURCES_FOLLOW + "," + SOURCES_COLLECTION;
    public static final String SOURCES_RECOMMENDED_FOLLOW_COLLECTION = SOURCES_RECOMMENDED + "," + SOURCES_FOLLOW + "," + SOURCES_COLLECTION;

    private static final String TEST_HOST = "wallpaperapi.lianpenglin.cc";

    // productors ========================================================================================
    public static final String URL_SUBSCRIBE_TAGS = HTTPS + HOST_PRODUCTOR + "/api/v1/tags";// 废弃
    /**
     * 明星主页查询背板图
     */
    public static final String URL_QUERY_BACKGROUND = HTTPS + HOST_PRODUCTOR + "/api/v1/tag/background";
    /**
     * 搜索
     */
    public static final String URL_SEARCH_TAG = HTTPS + HOST_PRODUCTOR + "/api/v1/search"; 
    /**
     * 根据id查询tag信息
     */
    public static final String URL_TAG_NAME = HTTPS + HOST_PRODUCTOR + "/api/v1/tagname";
    /**
     * 专题的列表
     */
    public static final String URL_TOPICS = HTTP + HOST_WALLPAPER + "/api/v1/wallpaper/topic/list";
    /**
     * 专题的壁纸列表
     */
    public static final String URL_TOPIC_WALLPAPERS_V3 = HTTP + HOST_WALLPAPER + "/api/v3/topic/list";
    /**
     * 根据tag查询壁纸列表
     */
    public static final String URL_TAG_WALLPAPERS_V3 = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper/tag";
    /**
     * 查询全图信息
     */
    public static final String URL_FULL_WALLPAPER = HTTP + HOST_WALLPAPER + "/api/v2/wallpaper";
    /**
     * 批量查询全图信息
     */
    public static final String URL_FULL_WALLPAPERS = HTTP + HOST_WALLPAPER + "/api/v2/wallpaper/indexAll";
    /**
     * 批量查询缩略图信息
     */
    public static final String URL_THUMBNAILS = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper";
    /**
     * 定时更换
     */
    public static final String URL_SCHEDULE_WALLPAPER_V3 = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper/everyday";
    /**
     * 之前的摇一摇，现在的下一张
     */
    public static final String URL_SHAKE_WALLPAPER_V3 = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper/shake";
    /**
     * 添加/删除收藏
     */
    public static final String URL_COLLECT_WALLPAPER = HTTP + HOST_WALLPAPER + "/api/v2/fav";
    /**
     * 收藏列表
     */
    public static final String URL_COLLECT_WALLPAPER_V3 = HTTP + HOST_WALLPAPER + "/api/v3/fav";
    /**
     * id查询专题信息
     */
    public static final String URL_TOPIC_INFO = HTTP + HOST_WALLPAPER + "/api/v2/wallpaper/topicinfo";
    /**
     * 壁纸新版智能推荐接口(V3)
     */
    public static final String URL_RECOMMEND_V3 = HTTP + HOST_WALLPAPER + "/api/v3/recommend";
    /**
     * 获取相关壁纸列表接口
     */
    public static final String URL_RELEVANT_WALLPAPERS_V3 = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper/related";
    /**
     * 获取壁纸排行
     */
    public static final String URL_RANKLIST_V3 = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper/rank";
    /**
     * 获取壁纸发布顺序(最新)
     */
    public static final String URL_PUBLISH_WALLPAPERS_V3 = HTTP + HOST_WALLPAPER + "/api/v3/wallpaper/publist";
    /**
     * 获取壁纸分类
     */
    public static final String URL_CATEGORY_V3 = HTTP + HOST_WALLPAPER + "/api/v3/category";
    /**
     * 分类-热门关注
     */
    public static final String URL_HOT_CONCERN_V3 = HTTP + HOST_WALLPAPER + "/api/v3/concern";
    /**
     * 壁纸新版根据颜色获取壁纸
     */
    public static final String URL_COLOR_WALLPAPERS_V3 = HTTP + HOST_WALLPAPER + "/api/v3/square/wallpaper";
    /**
     * 根据壁纸类型获取壁纸
     */
    public static final String URL_CATEGORY_WALLPAPER_V3 = HTTP + HOST_WALLPAPER + "/api/v3/category/wallpaper";

}
