package id.ramdannur.mobileappsanalytics.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import id.ramdannur.mobileappsanalytics.application.AnalyticsApplication;
import id.ramdannur.mobileappsanalytics.R;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private Tracker mTracker;
    private String name = "MainPage";
    private Button btnSend = null;
    private Button btnGoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init view
        btnSend = (Button) findViewById(R.id.btn_send);
        btnGoto = (Button) findViewById(R.id.btn_goto_second);

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        // Set listener
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Share")
                        .build());

                Toast.makeText(getApplicationContext(), "Successful sent!", Toast.LENGTH_SHORT).show();
            }
        });
        btnGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSecondPage = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(iSecondPage);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
