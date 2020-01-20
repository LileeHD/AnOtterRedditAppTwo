package lilee.hd.anotterredditapptwo.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import lilee.hd.anotterredditapptwo.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment detailFragment = new DetailFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment).commit();
    }
}
