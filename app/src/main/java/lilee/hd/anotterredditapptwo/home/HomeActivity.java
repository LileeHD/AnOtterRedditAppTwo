package lilee.hd.anotterredditapptwo.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import lilee.hd.anotterredditapptwo.R;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Context mContext = HomeActivity.this;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavSetup();
        checkConnection(savedInstanceState);
    }

    private void bottomNavSetup() {
        navView = findViewById(R.id.bottom_nav_bar);
        BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
            Fragment selectedFragment = new HomeFragment();
            switch (item.getItemId()) {
                case R.id.ic_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.ic_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.ic_otter:
                    selectedFragment = new OtterFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        };
        navView.setOnNavigationItemSelectedListener(navListener);
    }
    private void checkConnection(Bundle savedInstanceState){
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
            if (savedInstanceState == null && getIntent().getData() != null) {
                navView.setSelectedItemId(R.id.ic_search);
            } else {
                navView.setSelectedItemId(R.id.ic_home);
            }
        }else{
            Toast.makeText(mContext, "No connection", Toast.LENGTH_SHORT).show();
        }
    }

}
