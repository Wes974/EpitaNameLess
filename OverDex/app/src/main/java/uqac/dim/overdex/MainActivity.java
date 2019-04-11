package uqac.dim.overdex;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.DatabaseHelper;
import uqac.dim.overdex.DataBase.Heroes;

public class MainActivity extends AppCompatActivity  {

    public final static String EXTRA_ID = "uqac.dim.overdex.ID";
    private ListView listView;
    private DrawerLayout layDrawer;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private ActionBarDrawerToggle drawerToggle;
    private EditText searchHeroes;

    private  MediaPlayer clickSound;
    private  MediaPlayer mainSound;

    private DatabaseHelper databaseHelper;
    private ArrayList<Heroes> heroesArrayList;
    private HeroesAdaptater heroesadaptaer;

    private int ID_heroes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLay();
        setSupportActionBar(toolbar);
        drawerToggle = setDrawerToggle();
        layDrawer.addDrawerListener(drawerToggle);

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

        searchHeroes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                heroesadaptaer.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        heroesadaptaer = new HeroesAdaptater(this,heroesArrayList);
        listView.setAdapter(heroesadaptaer);

        listView.setOnItemClickListener(new DrawerItemClickListener());

        changeLayoutHeroes(ID_heroes);
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
        clickSound.start();
        super.onStop();
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

    public void initLay() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        layDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navigation_view);
        listView = (ListView) findViewById(R.id.nav_listView);
        searchHeroes = (EditText) findViewById(R.id.search_bar_heroes);
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
            changeLayoutHeroes(savedInstanceState.getInt("ID"));
            ID_heroes = savedInstanceState.getInt("ID");
        }
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
            }
    }


    private void selectItem(int position) {
        ID_heroes = (int)heroesadaptaer.getItemId(position);
        clickSound.start();

        changeLayoutHeroes(ID_heroes);
        listView.setItemChecked(position, true);
        layDrawer.closeDrawer(navView);
    }

    private ActionBarDrawerToggle setDrawerToggle() {
        return new ActionBarDrawerToggle(this,layDrawer,toolbar,R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void changeLayoutHeroes (int ID_heroes) {

        int index = -1;
        for(int i = 0; i < heroesArrayList.size(); i++) {
            if (heroesArrayList.get(i).getId() == ID_heroes) {
                index = i;
            }
        }

        if (index < 0) {
            Log.v("DIM","This ID does not exist in this DB.");
            return;
        }

        Toast.makeText(this, heroesArrayList.get(index).getName(), Toast.LENGTH_SHORT).show();

        setTitle(heroesArrayList.get(index).getName());
        Glide.with(this).load(heroesArrayList.get(index).getPicture()).into((ImageView)findViewById(R.id.picture_heroes));
        ((TextView)findViewById(R.id.Identity_heroes)).setText(heroesArrayList.get(index).getIdentity());
        ((TextView)findViewById(R.id.Age_heroes)).setText(heroesArrayList.get(index).getAge());
        ((TextView)findViewById(R.id.Job_heroes)).setText(heroesArrayList.get(index).getJob());
        ((TextView)findViewById(R.id.Localisation_heroes)).setText(heroesArrayList.get(index).getLocalisation());
        ((TextView)findViewById(R.id.Afflilation_heroes)).setText(heroesArrayList.get(index).getAfflilation());
        ((TextView)findViewById(R.id.Citation_heroes)).setText(heroesArrayList.get(index).getCitation());
        ((TextView)findViewById(R.id.History_heroes)).setText(heroesArrayList.get(index).getHistory());
    }

    public void openAttackActivity(View bouton){

        Intent intent = new Intent(MainActivity.this, AttackActivity.class);

        intent.putExtra(EXTRA_ID, ID_heroes);
        databaseHelper.closeDataBase();
        clickSound.start();
        startActivity(intent);
    }

    public void openSkinGelleryActivity(View bouton){

        Intent intent = new Intent(MainActivity.this, SkinGalleryActivity.class);

        intent.putExtra(EXTRA_ID, ID_heroes);
        databaseHelper.closeDataBase();
        clickSound.start();
        startActivity(intent);
    }

    public void openMediaPage(View Bouton){

        Uri webpage = Uri.parse("https://playoverwatch.com/fr-fr/media/");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
}
