package lilee.hd.anotterredditapptwo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Post;

public class PostViewModel extends ViewModel {

    private final MutableLiveData<Post> currentPost = new MutableLiveData<>();
    private MutableLiveData<Feed> mutableLiveData;

    public void initHome() {
        if (mutableLiveData != null) {
            return;
        }
        RedditRepository repository = RedditRepository.getInstance();
        mutableLiveData = repository.getFeed();
    }

    public MutableLiveData<Feed> getFeedRepository() {
        return mutableLiveData;
    }

    public void sendData(Post post) {
        this.currentPost.setValue(post);
    }

    public LiveData<Post> getCurrentPost() {
        return currentPost;
    }

}
