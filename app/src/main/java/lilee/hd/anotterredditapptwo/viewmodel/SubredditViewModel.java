package lilee.hd.anotterredditapptwo.viewmodel;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Post;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.search.SubredditCallback;
import lilee.hd.anotterredditapptwo.search.SubredditNetworking;

public class SubredditViewModel extends ViewModel {
    private static final String TAG = "SubredditViewModel";
    private SubredditRepository mRepository;
    private SubredditNetworking mNetworking;
    private LiveData<List<Subreddit>> mSubList;
    private LiveData<List<String>> mNameList;

    private final MutableLiveData<Post> currentPost = new MutableLiveData<>();
    private final MutableLiveData<Subreddit> currentSubreddit = new MutableLiveData<>();
    private MutableLiveData<Feed> mutableLiveData;


    public SubredditViewModel(SubredditRepository repository) {
        this.mRepository = repository;
        mNetworking = repository.getNetworking();
        mSubList = repository.getSubList();
        mNameList = repository.getnameList();
    }

    //    ---------------------------Subreddit in database ---------------
    public void saveSubreddit(EditText userInput) {
        String subredditName = userInput.getText().toString().trim();
        mNetworking.fetchSubreddit(subredditName, new SubredditCallback(mRepository, userInput));
        Log.d(TAG, "DAO: insertSubreddit: " + subredditName);
    }

    public LiveData<List<Subreddit>> getSubreddits() {
        return mSubList;
    }

    public void removeSubreddit(Subreddit subreddit){
        mRepository.removeSubreddit(subreddit);
    }

//    -------------------------send data from searchfragment to otterfragment
    public void sendSubredditforNewFeed(Subreddit subreddit) {
        this.currentSubreddit.setValue(subreddit);
        String name = subreddit.getName();
        Log.d(TAG, "sendSubredditforNewFeed: " + name);
    }

    public LiveData<List<String>> getnames() {
        return mNameList;
    }

//    -------------------------- user feed

    public void initCry() {
        if (mutableLiveData != null) {
            return;
        }
        Subreddit subreddit = currentSubreddit.getValue();
        String query = null;
        if (subreddit != null) {
            query = subreddit.getName();
            RedditRepository repository = RedditRepository.getInstance();
            mutableLiveData = repository.getUserFeed(query);

        }
        Log.d(TAG, "initHome: " + query);
    }

    public void initCryAgain(String query) {
        if (mutableLiveData != null) {
            return;
        }
            RedditRepository repository = RedditRepository.getInstance();
            mutableLiveData = repository.getUserFeed(query);
        Log.d(TAG, "initCryAgain: " + query);
    }

    public MutableLiveData<Feed> needHelp(){
        Log.d(TAG, "needHelp: " + mNameList.getValue());
        return mutableLiveData;
    }
    public void sendData(Post post) {
        this.currentPost.setValue(post);
    }

    public LiveData<Post> getCurrentPost() {
        return currentPost;
    }

}
