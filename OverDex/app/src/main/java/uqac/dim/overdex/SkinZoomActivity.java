package uqac.dim.overdex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import uqac.dim.overdex.DataBase.DatabaseHelper;


public class SkinZoomActivity extends AppCompatActivity {
    private MediaPlayer mainSound;
    private MediaPlayer clickSound;

    private ImageView imageView;
    private TextView textView;
    public static final String EXTRA_SKIN_NAME = "SkinZoomActivity.SKIN_NAME";
    public static final String EXTRA_SKIN_IMAGE = "SkinZoomActivity.SKIN_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_skin_layout);

        imageView = (ImageView) findViewById(R.id.skin_zoom);
        textView = (TextView) findViewById(R.id.skin_name);
        Intent intent = getIntent();
        String nameSkin = intent.getStringExtra(EXTRA_SKIN_NAME);
        String imageSkin = intent.getStringExtra(EXTRA_SKIN_IMAGE);

        textView.setText(nameSkin);
        Glide.with(this).load(imageSkin).into(imageView);


        clickSound = MediaPlayer.create(this, R.raw.soundclick);
        try{
            mainSound = MediaPlayer.create(this, R.raw.soundmain);
            mainSound.setLooping(true);
            mainSound.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        mainSound.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (mainSound != null && !mainSound.isPlaying()) {
            mainSound.start();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (mainSound != null && mainSound.isPlaying()) {
            mainSound.pause();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        clickSound.start();
        if (mainSound != null && mainSound.isPlaying()) {
            mainSound.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainSound.release();
        mainSound = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
        }
    }

}




