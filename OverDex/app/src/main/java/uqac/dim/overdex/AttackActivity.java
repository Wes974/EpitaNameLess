package uqac.dim.overdex;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.Attacks;
import uqac.dim.overdex.DataBase.DatabaseHelper;
import uqac.dim.overdex.DataBase.Heroes;

public class AttackActivity extends AppCompatActivity {
    private ListView listView;
    private android.support.v7.widget.Toolbar toolbar;
    private MediaPlayer mainSound;
    private MediaPlayer clickSound;

    private DatabaseHelper databaseHelper;
    private ArrayList<Heroes> heroesArrayList;

    private ArrayList<Attacks> attacksArrayList;
    private AttacksAdaptater attacksadaptater;
    private int ID_heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attacks_layout);

        Intent intent = getIntent();
        ID_heroes = intent.getIntExtra(MainActivity.EXTRA_ID, 1);

        listView = (ListView) findViewById(R.id.attacks_listView);
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
        changeLayoutAttacks(ID_heroes);
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
            changeLayoutAttacks(savedInstanceState.getInt("ID"));
            ID_heroes = savedInstanceState.getInt("ID");
        }
    }

    public void  changeLayoutAttacks(int ID_heroes) {

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

        attacksArrayList = databaseHelper.getAttacksList(ID_heroes);
        attacksadaptater = new AttacksAdaptater(this,attacksArrayList);
        listView.setAdapter(attacksadaptater);

        setTitle(heroesArrayList.get(index).getName());
        ((TextView)findViewById(R.id.Classe_heroes)).setText(heroesArrayList.get(index).getClasse());
        ((TextView)findViewById(R.id.Descr_heroes)).setText(heroesArrayList.get(index).getDescription());
    }
}
