package com.BlueN.challengeyourmind;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    private Button BFinalDismiss, BFinalOk;
    private TextView RateTv;
    private Intent AppLink;
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
        setContentView(R.layout.activity_final);

        BFinalDismiss=(Button) findViewById(R.id.BFinalDismiss);
        BFinalOk=(Button) findViewById(R.id.BFinalOk);
        RateTv= (TextView) findViewById(R.id.FinalHintTv);



        sp= new SoundPool(5, AudioManager.STREAM_MUSIC,0 );
        soundId = sp.load(getBaseContext(), R.raw.bsound, 1);

        drop_down= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.drop_down_logo);

        blink= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        fade_in= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        RateTv.startAnimation(drop_down);
        BFinalDismiss.startAnimation(blink);
        BFinalOk.startAnimation(blink);
        buttonClick = new AlphaAnimation(10F, 0.1F);

        font=Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        BFinalDismiss.setTypeface(font);
        BFinalOk.setTypeface(font);
        RateTv.setTypeface(font);

        AppLink= new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.BlueN.challengeyourmindpro"));
    }

    public void dismiss (View v){
        BFinalDismiss.startAnimation(fade_in);
        sp.play(soundId, 1, 1, 0, 0, 1);
        BFinalDismiss.startAnimation(buttonClick);
        this.finish();
    }
    public void Pro (View v){
        BFinalOk.startAnimation(fade_in);
        sp.play(soundId, 1, 1, 0, 0, 1);
        BFinalOk.startAnimation(buttonClick);
        startActivity(AppLink);
        this.finish();
    }
}
