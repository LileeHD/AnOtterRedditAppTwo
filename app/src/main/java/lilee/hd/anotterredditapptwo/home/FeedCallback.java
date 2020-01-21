package lilee.hd.anotterredditapptwo.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.FeedData;
import lilee.hd.anotterredditapptwo.model.Post;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedCallback implements Callback<Feed> {
    private SubredditRepository mRepository;
    private Post post;
    private static final String TAG = "SubredditCallback";
    private MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
    private Subreddit mSubreddit;

    public FeedCallback(SubredditRepository mRepository) {
        this.mRepository = mRepository;
    }

    public FeedCallback(SubredditRepository mRepository, Subreddit mSubreddit) {
        this.mRepository = mRepository;
        this.mSubreddit = mSubreddit;
    }

    @Override
    public void onResponse(@NotNull Call<Feed> call, @NotNull Response<Feed> response) {
        Feed feed = response.body();
        FeedData data = feed != null ? feed.getData():null;
        if (data!=null){
            Log.d("HOBO", "onResponse: " + response.code());
        }
        if (response.isSuccessful()) {
            feedMutableLiveData.setValue(response.body());
        }
    }

    @Override
    public void onFailure(Call<Feed> call, Throwable t) {
        feedMutableLiveData.setValue(null);
    }
}
