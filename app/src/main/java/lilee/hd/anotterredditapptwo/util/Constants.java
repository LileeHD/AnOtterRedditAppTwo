package lilee.hd.anotterredditapptwo.util;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Constants {
    /**
     * Tags
     */
    public static final String TAG_TOKEN = "OTTER";
    /**
     * Postman is my best friend
     * https://www.getpostman.com/downloads/
     */

    public static final String CLIENT_NAME = "An Otter Reddit App";
    /**
     * Base urls
     */
    public static final String BASE_URL = "https://www.reddit.com/";
    public static final String DEFAULT_URL = "https://www.reddit.com/subreddits/default.json";

    /**
     * Reddit request keys
     */
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_ID = "YddhwvCaFPuz8Q";
    public static final String PACKAGE = "lilee.hd.anotterredditapp";
    public static final String PASSWORD = "";

    public static final String CODE = "code";

    public static String SORT_KEY = "sort";
    public static String SORT_NEW = "new";
    public static String SORT_POPULAR = "popular";
    public static String SORT_DEFAULT = "default";


    public static RequestBody getRequestBody(String s) {
        return RequestBody.create(s, MediaType.parse("text/plain"));
    }
//     Firebase
    public static final String DATABASE_REF= "subreddit";
}
