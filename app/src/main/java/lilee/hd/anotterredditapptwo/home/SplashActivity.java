package lilee.hd.anotterredditapptwo.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import lilee.hd.anotterredditapptwo.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> startActivity(new Intent(SplashActivity.this, HomeActivity.class)), 2000);

    }
}
