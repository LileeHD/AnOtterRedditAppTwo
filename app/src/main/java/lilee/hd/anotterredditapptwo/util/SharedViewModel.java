package lilee.hd.anotterredditapptwo.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lilee.hd.anotterredditapptwo.model.Post;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<Post>currentPost = new MutableLiveData<>();

    public void sendData(Post post){
        currentPost.setValue(post);
    }

    public LiveData<Post>getSelected(){
        return currentPost;
    }

}
