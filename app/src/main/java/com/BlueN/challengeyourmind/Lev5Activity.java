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

public class Lev5Activity extends AppCompatActivity {

    private ImageView Lev5Iv;
    private EditText Lev5Et1,Lev5Et2;
    private Button Lev5SubmitButton, Lev5HintButton;
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
    private Lev5HintActivity frag;
    private FragmentManager fm;
    private String Value1, Value2;
    private int FinalValue1, FinalValue2;
    final EventListener vungleListener = new EventListener(){
        @Override
        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
            if(isCompletedView==true)
                frag.show(fm, "Lev5Hint");
        }
        @Override
        public void onAdStart() {

            frag.show(fm, "Lev5Hint");
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
        setContentView(R.layout.activity_lev5);

        frag= new Lev5HintActivity();
        frag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        fm= getFragmentManager();
        vunglePub.setEventListeners(vungleListener);

        Lev5Iv=(ImageView) findViewById(R.id.Lev5Iv);
        Lev5Et1=(EditText) findViewById(R.id.Lev5Et1);
        Lev5Et2=(EditText) findViewById(R.id.Lev5Et2);
        Lev5SubmitButton=(Button) findViewById(R.id.Lev5SubmitButton);
        Lev5HintButton=(Button) findViewById(R.id.Lev5HintButton);

        font=Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        Lev5SubmitButton.setTypeface(font);
        Lev5HintButton.setTypeface(font);

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

        Lev5Iv.startAnimation(drop_down);
        Lev5SubmitButton.startAnimation(blink);
        Lev5HintButton.startAnimation(blink);
        Lev5Et1.startAnimation(blink);
        Lev5Et2.startAnimation(blink);
        buttonClick = new AlphaAnimation(10F, 0.1F);

        vunglePub.init(this, "57a7db86fea8acdf7e00001a");
        vunglePub.setEventListeners(vungleListener);
    }
    public void Submit (View v) {

        Lev5SubmitButton.startAnimation(fade_in);
        Lev5SubmitButton.startAnimation(buttonClick);

        Value1= Lev5Et1.getText().toString();
        Value2= Lev5Et2.getText().toString();


        if(Value1.equals(""))

        {



        }
        else


            FinalValue1=Integer.parseInt(Value1);

        if(Value2.equals(""))

        {



        }
        else


            FinalValue2=Integer.parseInt(Value2);



        if(FinalValue1==10 && FinalValue2==11){

            StartAppAd.showAd(this);
            sp.play(soundIdcorrect,1,1,0,0,1);
            Toast.makeText(this, "Well done", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(this,Lev6Activity.class);
            FirstActivity.savelevstate(6, this);

            startActivity(i);

            this.finish();


        }else{
            Lev5Iv.startAnimation(blink);
            sp.play(soundIdwrong,1,1,0,0,1);
        }
    }


    public void Hint (View v){
        Lev5HintButton.startAnimation(fade_in);
        sp.play(soundId, 1, 1, 0, 0, 1);
        Lev5HintButton.startAnimation(buttonClick);
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
