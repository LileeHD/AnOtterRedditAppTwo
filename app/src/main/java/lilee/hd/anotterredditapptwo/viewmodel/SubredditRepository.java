package lilee.hd.anotterredditapptwo.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lilee.hd.anotterredditapptwo.database.SubredditDB;
import lilee.hd.anotterredditapptwo.database.SubredditDao;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.reddit.RedditAPI;
import lilee.hd.anotterredditapptwo.reddit.RedditService;
import lilee.hd.anotterredditapptwo.search.SubredditNetworking;
// https://stackoverflow.com/questions/46930335/what-is-difference-between-mediatorlivedata-and-mutablelivedata-in-mvvm

public class SubredditRepository {
    private static final String TAG = "SubredditRepository";
    private static SubredditRepository sInstance;
    private SubredditDao mSubredditDao;
    private Executor mExecutor;
    private SubredditNetworking mNetworking;
    private LiveData<List<Subreddit>> subredditsFromDb;
    private LiveData<List<String>> namesFromDb;

    private SubredditRepository(SubredditDB database, Executor executor, SubredditNetworking networking) {
        mSubredditDao = database.subredditDao();
        this.mExecutor = executor;
        this.mNetworking = networking;
        subredditsFromDb = mSubredditDao.getAll();
        namesFromDb= mSubredditDao.getAllNames();
    }

    public static SubredditRepository getInstance(SubredditDB database, Executor executor,
                                                  SubredditNetworking networking) {
        if (sInstance == null) {
            sInstance = new SubredditRepository(database, executor, networking);
        }
        return sInstance;
    }

    public static SubredditRepository setInstance(Context context) {
        SubredditDB database = SubredditDB.getInstance(context);
        SubredditNetworking networking =
                SubredditNetworking.getInstance();
        Executor executor = Executors.newSingleThreadExecutor();
        return SubredditRepository.getInstance(database, executor, networking);
    }

    public SubredditNetworking getNetworking() {
        return mNetworking;
    }

    public void insertSubreddit(Subreddit subreddit) {
        mExecutor.execute(() -> mSubredditDao.save(subreddit));
        Log.d(TAG, "DAO: " + subreddit);
    }


// -------------- from database------------

    public LiveData<List<Subreddit>> getSubList() {
        mExecutor.execute(() -> mSubredditDao.getAll());
        return subredditsFromDb;
    }

    public void removeSubreddit(Subreddit subreddit) {
        mExecutor.execute(() -> mSubredditDao.remove(subreddit));
    }

    // -------------- from searchfragment------------

    public LiveData<Subreddit> getSubredditName(String name) {
        return mSubredditDao.getSubreddit(name);
    }

    public LiveData<List<String>> getnameList() {
        mExecutor.execute(() -> mSubredditDao.getAll());
        return namesFromDb;
    }


//        public MutableLiveData<Feed> getUserFeed(String name) {
//        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
//        redditAPI.getSavedFeed(name).enqueue(new Callback<Feed>() {
//            @Override
//            public void onResponse(Call<Feed> call, Response<Feed> response) {
//                if (response.isSuccessful()) {
//                    feedMutableLiveData.setValue(response.body());
//                    Log.d("DAO OTTER", "onResponse: " + response.code());
//
//                    Log.d(TAG, "onResponse: ");
//                }
//            }
//            @Override
//            public void onFailure(Call<Feed> call, Throwable t) {
//                feedMutableLiveData.setValue(null);
//                Log.d(TAG, "onFailure: "+ t);
//            }
//        });
//        return feedMutableLiveData;
//    }

}
