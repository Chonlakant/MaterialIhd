package net.com.io.ihd.activities;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivitytivity extends Activity {

    private static final int WAIT_TIME = 7000;


    private static final String AD_UNIT_ID = "ca-app-pub-1640846146810179/3434855447";

    private InterstitialAd interstitial;
    private Timer waitTimer;
    private boolean interstitialCanceled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(AD_UNIT_ID);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

                if (!interstitialCanceled) {
                    waitTimer.cancel();
                    interstitial.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                startMainActivity();
            }
        });
        interstitial.loadAd(new AdRequest.Builder().build());

        waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                interstitialCanceled = true;
                SplashScreenActivitytivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        startMainActivity();
                    }
                });
            }
        }, WAIT_TIME);
    }

    @Override
    public void onPause() {
        waitTimer.cancel();
        interstitialCanceled = true;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (interstitial.isLoaded()) {

            interstitial.show();
        } else if (interstitialCanceled) {

            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, SampleActivity.class);
        startActivity(intent);
        finish();
    }
}
