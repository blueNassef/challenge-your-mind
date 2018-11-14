package com.BlueN.challengeyourmind;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.vungle.publisher.VunglePub;

public class FirstActivity extends AppCompatActivity {

    private Intent About;
    private Button b_start;
    private Button b_about;
    private Button b_exit;
    private Typeface font;
    private SoundPool sp;
    private int soundId;
    private Animation fade_in;
    private AlphaAnimation buttonClick;
    public static final String levstate= "levstate";
    private Context mycontext;
    final VunglePub vunglePub = VunglePub.getInstance();
    @SuppressWarnings("deprecation")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        StartAppSDK.init(this, "206917462", true);
        setContentView(R.layout.activity_first);
        mycontext= this;
        final String app_id = "57a7db86fea8acdf7e00001a";
        vunglePub.init(this, app_id);

        About= new Intent(this,AboutActivity.class);
        b_start= (Button) findViewById(R.id.FirstActivityStartButton);
        b_about= (Button) findViewById(R.id.FirstActivityAboutButton);
        b_exit= (Button) findViewById(R.id.FirstActivityExitButton);

        font= Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        b_start.setTypeface(font);
        b_about.setTypeface(font);
        b_exit.setTypeface(font);

        sp= new SoundPool(5, AudioManager.STREAM_MUSIC,0 );
        soundId = sp.load(getBaseContext(), R.raw.bsound, 1);


        fade_in= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        b_start.startAnimation(fade_in);
        b_about.startAnimation(fade_in);
        b_exit.startAnimation(fade_in);
        buttonClick = new AlphaAnimation(10F, 0.1F);


        b_start.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                sp.play(soundId, 1, 1, 0, 0, 1);b_start.startAnimation(buttonClick);

                Intent i = null;
                switch(getlevstate(mycontext)) {
                    case 1:
                        i = new Intent(mycontext, Lev1Activity.class);
                        break;
                    case 2:
                        i = new Intent(mycontext, Lev2Activity.class);
                        break;
                    case 3:
                        i = new Intent(mycontext, Lev3Activity.class);
                        break;
                    case 4:
                        i = new Intent(mycontext, Lev4Activity.class);
                        break;
                    case 5:
                        i = new Intent(mycontext, Lev5Activity.class);
                        break;
                    case 6:
                        i = new Intent(mycontext, Lev6Activity.class);
                        break;
                    case 7:
                        i = new Intent(mycontext, Lev7Activity.class);
                        break;
                    case 8:
                        i = new Intent(mycontext, Lev8Activity.class);
                        break;
                    case 9:
                        i = new Intent(mycontext, Lev9Activity.class);
                        break;
                    case 10:
                        i = new Intent(mycontext, Lev10Activity.class);
                        break;


                }
                startActivity(i);
            }

        });

    }

    public void About (View v){startActivity(About);sp.play(soundId, 1, 1, 0, 0, 1);v.startAnimation(buttonClick);}
    public void Exit (View v){finish();sp.play(soundId, 1, 1, 0, 0, 1);v.startAnimation(buttonClick);
        StartAppAd.onBackPressed(this);}


    public static void savelevstate(int state, Context mycontext)

    {
        SharedPreferences pref= mycontext.getSharedPreferences("preffile", MODE_APPEND);
        SharedPreferences.Editor editor= pref.edit();
        editor.putInt("levstate", state);
        editor.commit();
    }

    public static int getlevstate(Context mycontext)

    {
        SharedPreferences pref= mycontext.getSharedPreferences("preffile", MODE_PRIVATE);
        int state= pref.getInt("levstate", 1);

        return state;
    }

    @Override
    public void onBackPressed() {
    }
}
