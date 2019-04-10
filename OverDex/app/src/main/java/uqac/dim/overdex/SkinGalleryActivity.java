package uqac.dim.overdex;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.DatabaseHelper;
import uqac.dim.overdex.DataBase.Heroes;
import uqac.dim.overdex.DataBase.Skins;

public class SkinGalleryActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private MediaPlayer mainSound;
    private MediaPlayer clickSound;

    private ArrayList<Heroes> heroesArrayList;
    private ArrayList<Skins> skinsArrayList;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private SkinsAdaptater skinsAdaptater;

    private DatabaseHelper databaseHelper;

    private int ID_heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skins_layout);

        Intent intent = getIntent();
        ID_heroes = intent.getIntExtra(MainActivity.EXTRA_ID, 1);

        recyclerView = (RecyclerView) findViewById(R.id.skin_list);
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

        heroesArrayList = databaseHelper.getHeroesList();
        changeLayoutSkin(ID_heroes);
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

    private void changeLayoutSkin(int ID_heroes) {
        int index = -1;
        for(int i = 0; i < heroesArrayList.size(); i++) {
            if (heroesArrayList.get(i).getId() == ID_heroes) {
                index = i;
            }
        }
        if (index < 0) {
            Log.v("DIM","This ID does not exist in this DB");
            return;
        }
        skinsArrayList = databaseHelper.getSkinsList(ID_heroes);
        setTitle(heroesArrayList.get(index).getName());

        if ( this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        }
        else {
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        }

        recyclerView.setLayoutManager(gridLayoutManager);
        skinsAdaptater = new SkinsAdaptater(getApplicationContext(), skinsArrayList);
        recyclerView.setAdapter(skinsAdaptater);
    }
}
