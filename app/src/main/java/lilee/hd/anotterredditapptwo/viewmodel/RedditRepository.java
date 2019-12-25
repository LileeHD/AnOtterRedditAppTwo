package lilee.hd.anotterredditapptwo.viewmodel;

import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

import butterknife.BindView;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.database.SubredditDao;
import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import lilee.hd.anotterredditapptwo.reddit.RedditAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedditRepository {

    //    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference myRef = database.getReference(DATABASE_REF);

    private static final String TAG = "RedditRepository";
    private static RedditRepository sInstance;
//    private PostDao postDao;
    private SubredditDao subredditDao;
    private Executor diskIO;
    private MediatorLiveData<Subreddit> subredditsFromNetwork;
    private LiveData<List<Subreddit>> subredditsFromDb;
    private List<Subreddit> lastLoadedSubreddits;

    @BindView
   (R.id.search_edit_text) EditText editText;
    private RedditAPI redditAPI;

    public RedditRepository() {
        redditAPI = RedditService.createService(RedditAPI.class);
    }

    public static RedditRepository getInstance() {
        if (sInstance == null) {
            sInstance = new RedditRepository();
        }
        return sInstance;
    }
//    public MutableLiveData<SubredditNode> getSubName(String input) {
//        MutableLiveData<SubredditNode> nodeMutableLiveData = new MutableLiveData<>();
//        redditAPI.getSubreddit(input).enqueue(new Callback<SubredditNode>() {
//            @Override
//            public void onResponse(Call<SubredditNode> call, Response<SubredditNode> response) {
//                nodeMutableLiveData.setValue(response.body());
//
//            }
//
//            @Override
//            public void onFailure(Call<SubredditNode> call, Throwable t) {
//                nodeMutableLiveData.setValue(null);
//            }
//        });
//        return nodeMutableLiveData;
//    }



    public MutableLiveData<Feed> getFeed() {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.getHomeFeed().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    feedMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                feedMutableLiveData.setValue(null);
//                    TODO THe app crash when no wifi connection
            }
        });
        return feedMutableLiveData;
    }

    public MutableLiveData<Feed> getOtter() {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.getOtterFeed().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    feedMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                feedMutableLiveData.setValue(null);
//                    TODO THe app crash when no wifi connection
            }
        });
        return feedMutableLiveData;
    }

    public MutableLiveData<Feed> searchResult(String input) {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.inputResult(input).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    feedMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                feedMutableLiveData.setValue(null);
                Log.d(TAG, "onFailure: searchResult failed");
            }
        });
        return feedMutableLiveData;
    }

//  ------------------      SUBREDDIT      -----------------------------

    public MutableLiveData<SubredditNode> getSubName(String input) {
        MutableLiveData<SubredditNode> subredditNode = new MutableLiveData<>();
        redditAPI.getSubreddit(input).enqueue(new Callback<SubredditNode>() {
            @Override
            public void onResponse(Call<SubredditNode> call, Response<SubredditNode> response) {
                subredditNode.setValue(response.body());
                Subreddit subreddit = subredditNode.getValue().getData();
                if (subreddit!= null && subreddit.getName() != null && !subreddit.isNsfw()){
                    insertSubreddit(subreddit);
                }
                Log.d(TAG, "onResponse: repository call"+subreddit);
            }
            @Override
            public void onFailure(Call<SubredditNode> call, Throwable t) {
                Log.d(TAG, "onFailure: getSubName Repository failed");
//                nodeMutableLiveData.setValue(null);
            }
        });
        return subredditNode;
    }

    public LiveData<List<Subreddit>> getSubreddits() {
        return subredditsFromDb;
    }

    public LiveData<Subreddit> getSubreddit(String name) {
        return subredditDao.getSubreddit(name);
    }

    public Subreddit getSubredditImmediately(String name) {
        return subredditDao.getSubredditImmediately(name);
    }

    public void insertSubreddit(Subreddit subreddit) {
        diskIO.execute(() -> subredditDao.insert(subreddit));
    }

    public void removeSubreddit(Subreddit subreddit) {
        diskIO.execute(() -> subredditDao.remove(subreddit));
    }






    public void writeNewSubreddit(String subname, String imgUrl, String description) {
        Subreddit subreddit = new Subreddit(subname, imgUrl, description);
    }

    public MutableLiveData<Feed> savedSubredditsFeed() {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        String list = "concatanation from db";
        redditAPI.getSavedFeed(list).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    feedMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                feedMutableLiveData.setValue(null);
            }
        });
        return feedMutableLiveData;
    }

//    public Subreddit getSubredditImmediately(String name) {
//        return subredditDao.getSubredditImmediately(name);
//    }
}
