package lilee.hd.anotterredditapptwo.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import lilee.hd.anotterredditapptwo.R;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Context mContext = HomeActivity.this;
    private BottomNavigationView navView;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavSetup();

        if (savedInstanceState == null && getIntent().getData() != null) {
            navView.setSelectedItemId(R.id.ic_search);
        } else {
            navView.setSelectedItemId(R.id.ic_home);
        }
    }

    private void bottomNavSetup() {
        navView = findViewById(R.id.bottom_nav_bar);
        navListener = item -> {
            Fragment selectedFragment = new HomeFragment();
            switch (item.getItemId()) {
                case R.id.ic_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.ic_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.ic_otter:
                    selectedFragment = new UserFeedFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        };
        navView.setOnNavigationItemSelectedListener(navListener);
    }
    private void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){

        }else{
//            connectionInfo.setVisibility(View.VISIBLE);
//            connectionInfo.setText(getText(R.string.no_connected));
//            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }
}
