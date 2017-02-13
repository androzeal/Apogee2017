package bitspilani.dvm.apogee2017;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         AVLoadingIndicatorView progress=(AVLoadingIndicatorView) findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),Home.class));
            }
        }, 1000);
    }
}
