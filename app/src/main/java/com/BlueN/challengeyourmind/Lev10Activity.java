package com.BlueN.challengeyourmind;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;

public class Lev10Activity extends AppCompatActivity {

    private ImageView Lev10Iv;
    private EditText Lev10Et;
    private Button Lev10SubmitButton, Lev10HintButton;
    private String value;
    private int FinalValue;
    private Typeface font;
    private SoundPool sp;
    private int soundId;
    private int soundIdcorrect;
    private int soundIdwrong;
    private Animation drop_down;
    private Animation blink;
    private Animation fade_in;
    private AlphaAnimation buttonClick;
    final VunglePub vunglePub = VunglePub.getInstance();
    private Lev10HintActivity frag;
    private FragmentManager fm;
    final EventListener vungleListener = new EventListener(){
        @Override
        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
            if(isCompletedView==true)
                frag.show(fm, "Lev10Hint");
        }
        @Override
        public void onAdStart() {

            frag.show(fm, "Lev10Hint");
        }

        @Override
        public void onAdEnd(boolean wasCallToActionClicked) {

        }

        @Override
        public void onAdPlayableChanged(boolean isAdPlayable) {

        }

        @Override
        public void onAdUnavailable(String reason) {

        }

    };
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "206917462", true);
        vunglePub.init(this, "57a7db86fea8acdf7e00001a");
        setContentView(R.layout.activity_lev10);

        frag= new Lev10HintActivity();
        frag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        fm= getFragmentManager();
        vunglePub.setEventListeners(vungleListener);

        Lev10Iv=(ImageView) findViewById(R.id.Lev10Iv);
        Lev10Et=(EditText) findViewById(R.id.Lev10Et);
        Lev10SubmitButton=(Button) findViewById(R.id.Lev10SubmitButton);
        Lev10HintButton=(Button) findViewById(R.id.Lev10HintButton);

        font=Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        Lev10SubmitButton.setTypeface(font);
        Lev10HintButton.setTypeface(font);

        sp= new SoundPool(5, AudioManager.STREAM_MUSIC,0 );
        soundId = sp.load(getBaseContext(), R.raw.bsound, 1);
        soundIdcorrect = sp.load(getBaseContext(), R.raw.correctanswer, 1);
        soundIdwrong = sp.load(getBaseContext(), R.raw.wronganswer, 1);

        drop_down= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.drop_down_logo);

        blink= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        fade_in= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        Lev10Iv.startAnimation(drop_down);
        Lev10SubmitButton.startAnimation(blink);
        Lev10HintButton.startAnimation(blink);
        Lev10Et.startAnimation(blink);
        buttonClick = new AlphaAnimation(10F, 0.1F);

        vunglePub.init(this, "57a7db86fea8acdf7e00001a");
        vunglePub.setEventListeners(vungleListener);
    }
    public void Submit (View v) {

        Lev10SubmitButton.startAnimation(fade_in);
        Lev10SubmitButton.startAnimation(buttonClick);

        value= Lev10Et.getText().toString();

        if(value.equals(""))

        {

        }
        else

            FinalValue=Integer.parseInt(value);

        if(FinalValue==11)

        {
            StartAppAd.showAd(this);
            sp.play(soundIdcorrect,1,1,0,0,1);
            Toast.makeText(this, "Well done.", Toast.LENGTH_SHORT).show();
            System.out.println();
            Intent i = new Intent (this, FinalActivity.class);
            startActivity(i);
            this.finish();

        }

        else{
            Lev10Iv.startAnimation(blink);
            sp.play(soundIdwrong,1,1,0,0,1);
        }
    }
    public void Hint (View v){
        Lev10HintButton.startAnimation(fade_in);
        sp.play(soundId, 1, 1, 0, 0, 1);
        Lev10HintButton.startAnimation(buttonClick);
        vunglePub.isAdPlayable();
        vunglePub.playAd();

        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
        } else {
            Toast.makeText(this, "Network error!, please check your internet conection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        vunglePub.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        vunglePub.onResume();
    }
}
