package lilee.hd.anotterredditapptwo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lilee.hd.anotterredditapptwo.model.Children;
import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Post;
import lilee.hd.anotterredditapptwo.model.SubredditNode;

public class PostViewModel extends ViewModel {

    private final MutableLiveData<Post> currentPost = new MutableLiveData<Post>();
    private MutableLiveData<Feed> mutableLiveData;
    private MutableLiveData<SubredditNode> nodeMutableLiveData;
    private String minputResult;

    private RedditRepository repository;

    public void initHome() {
        if (mutableLiveData != null) {
            return;
        }
        repository = RedditRepository.getInstance();
        mutableLiveData = repository.getFeed();
//        mutableLiveData= repository.searchResult(minputResult);
//        mutableLiveData= repository.savedSubredditsFeed();
    }

    public void initOtter() {
        if (mutableLiveData != null) {
            return;
        }
        repository = RedditRepository.getInstance();
        mutableLiveData = repository.getOtter();
//        mutableLiveData= repository.searchResult(minputResult);
//        mutableLiveData= repository.savedSubredditsFeed();
    }

    public MutableLiveData<Feed> getFeedRepository() {
        return mutableLiveData;
    }

    public MutableLiveData<Feed> getSearchResults(String query) {
        mutableLiveData = repository.searchResult(query);
        return mutableLiveData;
    }

    public void sendData(Post post) {
        this.currentPost.setValue(post);
    }

    public LiveData<Post> getCurrentPost() {
        return currentPost;
    }

}
