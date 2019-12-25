package lilee.hd.anotterredditapptwo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import lilee.hd.anotterredditapptwo.reddit.RedditAPI;

public class SubredditViewModel extends ViewModel {
    private static RedditRepository redditRepository;
    private RedditAPI redditAPI;
    private MutableLiveData<SubredditNode> nodeLiveData;
    private Subreddit subredditLiveData;
    private MutableLiveData<List<SubredditNode>> subredditList;
    private RedditRepository repository;
    private String minputResult;


    //    private MutableLiveData<List<Subreddit>> mSubreddit;

    public void initFetchSubs(String input) {
        if (nodeLiveData != null && !input.isEmpty()) {
            return;
        }
        repository = RedditRepository.getInstance();
        nodeLiveData = repository.getSubName(input);
    }

    public MutableLiveData<SubredditNode> getSubredditName(String query) {
        nodeLiveData = repository.getSubName(query);
        return nodeLiveData;
    }

    public LiveData<List<SubredditNode>> getSubreddits(){
        return subredditList;
    }
    public Subreddit getSubreddit(){
        return subredditLiveData;
    }

}
