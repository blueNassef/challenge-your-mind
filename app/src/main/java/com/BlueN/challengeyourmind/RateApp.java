package com.BlueN.challengeyourmind;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class RateApp extends AppCompatActivity {

    private Button BDismissRate, BAcceptRate;
    protected TextView RateTv;
    private Intent AppLink;
    private Intent rate;
    private Typeface font;
    private SoundPool sp;
    private int soundId;
    private Animation drop_down;
    private Animation blink;
    private Animation fade_in;
    private AlphaAnimation buttonClick;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rate_app);

        BDismissRate=(Button) findViewById(R.id.BDismissRate);
        BAcceptRate=(Button) findViewById(R.id.BAcceptRate);
        RateTv= (TextView) findViewById(R.id.RateTv);

        font=Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        BDismissRate.setTypeface(font);
        BAcceptRate.setTypeface(font);
        RateTv.setTypeface(font);

        sp= new SoundPool(5, AudioManager.STREAM_MUSIC,0 );
        soundId = sp.load(getBaseContext(), R.raw.bsound, 1);

        drop_down= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.drop_down_logo);

        blink= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        fade_in= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        RateTv.startAnimation(drop_down);
        BDismissRate.startAnimation(blink);
        BAcceptRate.startAnimation(blink);
        buttonClick = new AlphaAnimation(10F, 0.1F);


        rate=new Intent (this, Lev7Activity.class);
        AppLink= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.BlueN.challengeyourmind"));
    }
    public void dismiss (View v){
        BDismissRate.startAnimation(fade_in);
        sp.play(soundId, 1, 1, 0, 0, 1);
        BDismissRate.startAnimation(buttonClick);
        rate=new Intent (this, Lev7Activity.class);
        startActivity(rate);
        this.finish();
    }
    public void rate (View v){
        BAcceptRate.startAnimation(fade_in);
        sp.play(soundId, 1, 1, 0, 0, 1);
        BAcceptRate.startAnimation(buttonClick);
        startActivity(AppLink);
        for(int j=0;j<10;j++){
            if(j==10){
                startActivity(rate);
            }
        }
        this.finish();

    }
}
