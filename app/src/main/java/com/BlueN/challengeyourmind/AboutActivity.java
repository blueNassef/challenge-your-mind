package com.BlueN.challengeyourmind;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    private Button how_to_play_btn;
    private Button review_game_btn;
    private Button contact_us_btn;
    private Typeface font;
    private SoundPool sp;
    private int soundId;
    private Intent applink;
    private Intent mail;
    private HowToPlayActivity frag;
    private FragmentManager fm;
    private Animation fade_in;
    private AlphaAnimation buttonClick;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        how_to_play_btn= (Button) findViewById(R.id.about_how_to_play_button);
        review_game_btn= (Button) findViewById(R.id.about_review_game_button);
        contact_us_btn= (Button) findViewById(R.id.about_contact_us_button);
        applink= new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.BlueN.challengeyourmind"));
        frag= new HowToPlayActivity();
        frag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        fm= getFragmentManager();
        fade_in= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        how_to_play_btn.startAnimation(fade_in);
        review_game_btn.startAnimation(fade_in);
        contact_us_btn.startAnimation(fade_in);
        buttonClick = new AlphaAnimation(10F, 0.1F);


        font=Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains.ttf");
        how_to_play_btn.setTypeface(font);
        review_game_btn.setTypeface(font);
        contact_us_btn.setTypeface(font);


        sp= new SoundPool(5, AudioManager.STREAM_MUSIC,0 );
        soundId = sp.load(getBaseContext(), R.raw.bsound, 1);
    }
    public void HowToPlay(View v){frag.show(fm,"how to play dialog");sp.play(soundId, 1, 1, 0, 0, 1);v.startAnimation(buttonClick);}
    public void ReviewGame(View v){startActivity(applink);sp.play(soundId, 1, 1, 0, 0, 1);v.startAnimation(buttonClick);}
    public void ContactUs(View v){ mail = new Intent(Intent.ACTION_SEND);
        mail.setType("text/plain");
        mail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bluen.dev@gmail.com"});
        mail.putExtra(Intent.EXTRA_SUBJECT, "Enter level number");
        mail.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(mail, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AboutActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }sp.play(soundId, 1, 1, 0, 0, 1);v.startAnimation(buttonClick);}
}
