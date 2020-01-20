package lilee.hd.anotterredditapptwo.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import lilee.hd.anotterredditapptwo.reddit.RedditAPI;
import lilee.hd.anotterredditapptwo.reddit.RedditService;
import retrofit2.Call;
import retrofit2.Callback;

public class SubredditNetworking {
    private static final String TAG = SubredditNetworking.class.getSimpleName();

    private static SubredditNetworking sInstance;
    private RedditAPI redditAPI;
    private MutableLiveData<List<Subreddit>> subreddits;

    private SubredditNetworking() {
        subreddits = new MutableLiveData<>();
        redditAPI = RedditService.createService(RedditAPI.class);
    }

    public static SubredditNetworking getInstance() {
        if (sInstance == null) {
            sInstance = new SubredditNetworking();
        }
        return sInstance;
    }

    public LiveData<List<Subreddit>> getSubreddi() {
        return subreddits;
    }

    void updateSubreddits(List<Subreddit> subreddits) {
        this.subreddits.postValue(subreddits);
    }


    public void fetchSubreddit(String subredditName, Callback<SubredditNode> callback) {
        redditAPI.getSubreddit(subredditName).enqueue(callback);
    }

    public void getSubredditForFeed(String query){
        Log.d(TAG, "initOtherViews: ");
        redditAPI.getSavedFeed(query);
    }
}
