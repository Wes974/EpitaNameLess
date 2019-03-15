package uqac.dim.overdex;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.DatabaseHelper;
import uqac.dim.overdex.DataBase.Heroes;

public class MainActivity extends AppCompatActivity  {

    private ListView listView;
    private DrawerLayout layDrawer;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private ActionBarDrawerToggle drawerToggle;

    private DatabaseHelper databaseHelper;
    private ArrayList<Heroes> heroesArrayList;
    private HeroesAdaptater heroesadaptaer;


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
    }

    public void initLay() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        layDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navigation_view);
        listView = (ListView) findViewById(R.id.nav_listView);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
            }
    }


    private void selectItem(int position) {

        Toast.makeText(this, "Choix " + (position+1), Toast.LENGTH_LONG).show();

        listView.setItemChecked(position, true);
        setTitle("TITRE");
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
}

