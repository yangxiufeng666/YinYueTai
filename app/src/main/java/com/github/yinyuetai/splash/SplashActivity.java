package com.github.yinyuetai.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.yinyuetai.R;
import com.github.yinyuetai.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/12
 * YinYueTai
 */
public class SplashActivity extends AppCompatActivity {
    @Bind(R.id.splash)
    ImageView splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ButterKnife.bind(this);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_to_normal);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
