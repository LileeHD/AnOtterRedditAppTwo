package lilee.hd.anotterredditapptwo.model;

public class SubredditNode {


    private Subreddit data;

    public SubredditNode(Subreddit data) {
        this.data = data;
    }

    public SubredditNode() {
    }

    public Subreddit getData() {
        return data;
    }

    public void setData(Subreddit data) {
        this.data = data;
    }
}
