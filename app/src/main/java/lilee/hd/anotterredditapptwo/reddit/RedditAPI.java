package lilee.hd.anotterredditapptwo.reddit;

import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedditAPI {
    /**
     * Home data
     * @return
     */
    @Headers("Content-Type: application/json")

    // home display not logged in
    @GET(".json?t=new&raw_json=1&type=link")
    Call<Feed> getHomeFeed();

    @GET("r/otter/.json?t=new&raw_json=1&type=link")
    Call<Feed> getOtterFeed();

//    Comments
//    @GET("r/{subreddit}.json")
//    Call<Feed> getPosts(@Path("subreddit") String subreddit);

//    @GET("{subreddit}/comments/{id}.json")
//    Call<List<PostDetail>> getComments(@Path("subreddit") String subreddit, @Path("id") String id);

    // home display search
    @GET("search.json?&sort=new&raw_json=1&type=link")
    Call<Feed> searchPost(@Query("q") String postName,
                          @Query("t") String time);

    @GET("search.json?&sort=new&raw_json=1&type=link")
    Call<Feed> inputResult(@Query("q") String postName);

    @GET("{subreddit}/about.json")
    Call<Subreddit> getSubreddit(@Path("subreddit") String subreddit);

    @GET("r/{subreddit_list_name}/new.json")
    Call<Feed>getSavedFeed(@Path("subreddit_list_name") String subredditList);
}
