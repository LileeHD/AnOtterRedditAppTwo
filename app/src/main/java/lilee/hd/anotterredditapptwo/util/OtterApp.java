package lilee.hd.anotterredditapptwo.util;

import android.app.Application;

import lilee.hd.anotterredditapptwo.database.AppExecutors;

public class OtterApp extends Application {


    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

//    public OtterDatabase getDatabase() {
//        return OtterDatabase.getInstance(this, mAppExecutors);
//    }
//
//    public RedditRepository getRepository() {
//        return RedditRepository.getInstance();
//    }

//    public TokenRepository getTokenInfo(){
//        return TokenRepository.getInstance(getDatabase());
//    }
}
