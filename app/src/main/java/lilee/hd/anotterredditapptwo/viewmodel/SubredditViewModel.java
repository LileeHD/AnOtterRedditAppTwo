package lilee.hd.anotterredditapptwo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lilee.hd.anotterredditapptwo.model.Subreddit;

public class SubredditViewModel extends ViewModel {

    private RedditRepository repository;
    private MutableLiveData<List<Subreddit>> mSubreddit;


}
