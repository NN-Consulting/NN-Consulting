package com.losnn.spacia.presentation.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;

import com.losnn.spacia.MainActivity;
import com.losnn.spacia.R;
import com.losnn.spacia.base.BaseActivity;
import com.losnn.spacia.data.local.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Thread.sleep;

public class SplashActivity extends BaseActivity{

    @BindView(R.id.iv_splash_logo)
    ImageView ivSplashLogo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);
        animateImageView(ivSplashLogo);
        final SessionManager sessionManager = new SessionManager(getApplicationContext());
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    nextActivity(SplashActivity.this,null,LoginActivity.class,true);
                    if(sessionManager.isIntro()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    finish();

                } catch (Exception e) {
                    //nothing
                }
            }
        };
        t.start();
    }

    public void animateImageView(final ImageView v) {
        final int orange = getResources().getColor(R.color.colorAccent);

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaOrange = adjustAlpha(orange, mul);
                v.setColorFilter(alphaOrange, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    v.setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(1500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        colorAnim.start();

    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
