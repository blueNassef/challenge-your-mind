package com.BlueN.challengeyourmind;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent i;
    private ImageView iv_logo;
    private ImageView iv_splash;
    private TextView tv_subs;
    private TextView tv_loading;
    private Animation an_blink;
    private Animation an_drop_down_logo;
    private Animation an_drop_down_subs;
    private Animation an_rotate;
    private Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i= new Intent(this, FirstActivity.class);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
        iv_splash= (ImageView) findViewById(R.id.iv_splash);
        tv_subs= (TextView) findViewById(R.id.tv_subs);
        tv_loading= (TextView) findViewById(R.id.tv_loading);

        an_blink= AnimationUtils.loadAnimation(getBaseContext(), R.anim.blink_loading);
        an_drop_down_logo= AnimationUtils.loadAnimation(getBaseContext(), R.anim.drop_down_logo);
        an_drop_down_subs= AnimationUtils.loadAnimation(getBaseContext(), R.anim.drop_down_subs);
        an_rotate= AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        iv_logo.startAnimation(an_drop_down_logo);
        iv_splash.startAnimation(an_rotate);
        tv_subs.startAnimation(an_drop_down_subs);
        tv_loading.startAnimation(an_blink);

        font=Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        tv_subs.setTypeface(font);
        tv_loading.setTypeface(font);

        an_rotate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                finish();
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }

            @Override
            public void onAnimationStart(Animation animation) {


            }
        });
    }
}
