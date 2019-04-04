package uqac.dim.overdex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import uqac.dim.overdex.DataBase.DatabaseHelper;

public class SkinGalleryActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private MediaPlayer mainSound;
    private MediaPlayer clickSound;

    private DatabaseHelper databaseHelper;

    private int ID_heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skins_layout);

        Intent intent = getIntent();
        ID_heroes = intent.getIntExtra(MainActivity.EXTRA_ID, 1);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clickSound = MediaPlayer.create(this, R.raw.soundclick);
        try{
            mainSound = MediaPlayer.create(this, R.raw.soundmain);
            mainSound.setLooping(true);
            mainSound.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();
        databaseHelper.openDatabase();
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
        databaseHelper.closeDataBase();
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
        outState.putInt("ID", ID_heroes);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            ID_heroes = savedInstanceState.getInt("ID");
        }
    }
}
