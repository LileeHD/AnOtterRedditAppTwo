package lilee.hd.anotterredditapptwo.reddit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import lilee.hd.anotterredditapptwo.model.Children;
import lilee.hd.anotterredditapptwo.model.Feed;
import lilee.hd.anotterredditapptwo.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static lilee.hd.anotterredditapptwo.util.Constants.BASE_URL;
import static lilee.hd.anotterredditapptwo.util.Constants.TAG_TOKEN;

public class RedditNetworking {

    private static final String TAG = "RedditNetworking";
    private RedditAPI redditAPI;
    private Context context;
    private String time = "new";

//    private OtterDatabase database;

    public RedditNetworking() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        redditAPI = retrofit.create(RedditAPI.class);
    }

    public void searchCall(String result) {
        Call<Feed> call = redditAPI.searchPost(result, time);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d("searchCall", "onResponse: Server Response" + response.toString());
                Log.d("searchCall", "onResponse: received information" + response.body().toString());
                List<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d("searchCall", "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getImageUrl() + "\n" +
                            "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e("searchCall", "onFailure: ERROR: " + t.getMessage());
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void redditCall() {
        Call<Feed> call = redditAPI.getHomeFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());
                if (response.isSuccessful()) {
                    response.body().getData().getChildren().toString();
                    List<Children> childrenArrayList = response.body().getData().getChildren();
                    for (int i = 0; i < childrenArrayList.size(); i++) {
//                    Post info = childrenArrayList.get(i).getData()();
//                    String title = info.getTitle();
                        Log.d(TAG_TOKEN, "onResponse: \n" +
                                "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                                "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                                "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                                "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                                "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                                "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                                "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                                "thumbnail: " + childrenArrayList.get(i).getData().getImageUrl() + "\n" +
                                "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                                "-------------------------------------------------------------------------\n\n");
                        Log.d(TAG, "onResponse: redditCall");
                    }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void otterCall() {
        Call<Feed> call = redditAPI.getOtterFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());

                List<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d(TAG_TOKEN, "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getImageUrl() + "\n" +
                            "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
//                TODO: EXCEPTIONS
            }
        });
    }


}

