package br.com.deivisondfs2.babyborntime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppSplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(this, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        run();
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, TabsActivity.class);
        startActivity(intent);

        finish();

        overridePendingTransition(
                android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
