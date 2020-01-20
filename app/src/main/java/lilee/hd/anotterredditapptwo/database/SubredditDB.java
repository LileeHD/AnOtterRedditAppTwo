package lilee.hd.anotterredditapptwo.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lilee.hd.anotterredditapptwo.model.Subreddit;

@androidx.room.Database(entities = {Subreddit.class}, version = 1, exportSchema = false)
public abstract class SubredditDB extends RoomDatabase {

    public abstract SubredditDao subredditDao();

    private static final String DATABASE_NAME = "user_subreddit_list";

    static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();
    private static volatile SubredditDB sInstance;

    public static SubredditDB getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SubredditDB.class) {
                if (sInstance == null){
                    sInstance = getDatabaseBuilder(context).build();
                }
            }
        }
        Log.d(DATABASE_NAME, "DAO: getInstance: Hey !");
        return sInstance;
    }

    private static RoomDatabase.Builder<SubredditDB> getDatabaseBuilder(Context context) {
        return Room
                .databaseBuilder(context.getApplicationContext(),
                        SubredditDB.class, DATABASE_NAME)
                .fallbackToDestructiveMigration();
    }




}