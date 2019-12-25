//package lilee.hd.anotterredditapptwo.database;
//
//import android.content.Context;
//
//import androidx.lifecycle.MutableLiveData;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import lilee.hd.anotterredditapp.AppExecutors;
//
//import static lilee.hd.anotterredditapp.database.DatabaseUtils.DB_NAME;
//
////@Database(entities = {TokenResponse.class, Account.class, Subreddit.class}, version = 1, exportSchema = false)
////@TypeConverters({ConverterUtil.class})
//public abstract class OtterDatabase extends RoomDatabase {
//
//    private static OtterDatabase sInstance;
//    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
//    private static final String DATABASE_NAME = "otter";
//
//    public static synchronized OtterDatabase getInstance(Context context, AppExecutors executors) {
//        if (sInstance == null) {
//            synchronized (OtterDatabase.class) {
//                if (sInstance == null) {
//                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
//                            OtterDatabase.class, DB_NAME)
//                            .fallbackToDestructiveMigration()
//                            .build();
//                }
//            }
//        }
//        return sInstance;
//    }
//
////    public abstract TokenDao tokenDao();
////
////    public abstract AccountDao accountDao();
////
////    public abstract SubredditDao subredditDao();
//
//}
