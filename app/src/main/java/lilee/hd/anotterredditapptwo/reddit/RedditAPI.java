package lilee.hd.anotterredditapptwo.reddit;

import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedditAPI {
    /**
     * Home data
     *
     * @return
     */
    @Headers("Content-Type: application/json")

    // home display not logged in
//    @GET(".json?t=new&raw_json=1&type=link")
    @GET("r/otter+overwatch/.json?&sort=new&raw_json=1&type=sr")
    Call<Feed> getHomeFeed();

    @GET("r/otter/.json?t=new&raw_json=1&type=link")
    Call<Feed> getOtterFeed();

    // home display search
    @GET("search.json?&sort=new&raw_json=1&type=link")
    Call<Feed> searchPost(@Query("q") String postName,
                          @Query("t") String time);

    @GET("search.json?&sort=new&raw_json=1&type=link")
    Call<Feed> inputResult(@Query("q") String postName);

    // editText
    @GET("r/{subreddit}/about.json?&sort=new&raw_json=1&type=sr")
    Call<SubredditNode> getSubreddit(@Path("subreddit") String subreddit);

    @GET("r/{subreddit_name}/new.json")
    Call<Feed> getSavedFeed(@Path("subreddit_name") String subredditList);
}
