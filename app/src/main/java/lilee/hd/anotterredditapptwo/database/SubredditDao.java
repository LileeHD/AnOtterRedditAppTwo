package lilee.hd.anotterredditapptwo.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lilee.hd.anotterredditapptwo.model.Subreddit;

@Dao
public interface SubredditDao {
// to display
    @Query("SELECT * FROM subreddit_table")
    LiveData<List<Subreddit>> getAll();

    @Query("SELECT * FROM subreddit_table WHERE name = :name")
    LiveData<Subreddit> getSubreddit(String name);

    @Query("SELECT name FROM subreddit_table")
    LiveData<List<String>>getAllNames();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(Subreddit subreddit);

    @Delete
    void remove(Subreddit subreddit);

}
