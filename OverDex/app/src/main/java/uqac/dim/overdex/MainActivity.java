package uqac.dim.overdex;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();
        databaseHelper.openDatabase();
        heroesArrayList = databaseHelper.getHeroesList();

        heroesadaptaer = new HeroesAdaptater(this,heroesArrayList);
        listView.setAdapter(heroesadaptaer);
        listView.setOnItemClickListener(new DrawerItemClickListener());

        changeLayoutHeroes(ID_heroes);
    }

    @Override
    protected void onStop(){
        super.onStop();
        databaseHelper.closeDataBase();
    }

    public void initLay() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        layDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navigation_view);
        listView = (ListView) findViewById(R.id.nav_listView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v("DIM", Integer.toString(ID_heroes) + " Save");
        outState.putInt("ID", ID_heroes);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            Log.v("DIM",Integer.toString(savedInstanceState.getInt("ID")) + " Restore");
            changeLayoutHeroes(savedInstanceState.getInt("ID"));
        }
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
            }
    }


    private void selectItem(int position) {
        ID_heroes = heroesArrayList.get(position).getId();
        Toast.makeText(this, heroesArrayList.get(position).getName() + " " + ID_heroes, Toast.LENGTH_LONG).show();
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

    public void  changeLayoutHeroes (int ID_heroes) {

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
        setTitle(heroesArrayList.get(index).getName());
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
        startActivity(intent);
    }
}
