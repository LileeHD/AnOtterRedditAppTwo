package lilee.hd.anotterredditapptwo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import lilee.hd.anotterredditapptwo.model.Subreddit;

@Dao
public interface SubredditDao {

    @Query("SELECT * FROM subreddit_table")
    LiveData<List<Subreddit>> getAll();

    @Query("SELECT * FROM subreddit_table WHERE name = :name")
    LiveData<Subreddit> getSubreddit(String name);

    @Query("SELECT * FROM subreddit_table WHERE name = :name")
    Subreddit getSubredditImmediately(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Subreddit> subreddit);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Subreddit subreddit);

    @Delete
    void remove(Subreddit subreddit);
}
