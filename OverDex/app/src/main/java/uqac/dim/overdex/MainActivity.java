package uqac.dim.overdex;

import android.content.res.Configuration;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toolbar;

import uqac.dim.overdex.DataBase.DatabaseHelper;

public class MainActivity extends AppCompatActivity  {

    private ListView listView;
    private DrawerLayout layDrawer;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private ActionBarDrawerToggle drawerToggle;

    private DatabaseHelper databaseHelper;
    private HeroesCursorAdaptater heroesAdaptaer;
    private Cursor cursor;


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

        //heroesAdaptaer = new HeroesCursorAdaptater();
        //listView.setAdapter(heroesAdaptaer);
        listView.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void initLay() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        layDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navigation_view);
        listView = (ListView) findViewById(R.id.nav_listView);
    }

    /*private void setupDrawerContent(NavigationView navView) {
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }*/

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
    }


    /*public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        layDrawer.closeDrawers();
    }*/

    private void selectItem(int position) {

        switch (position) {
        }
        listView.setItemChecked(position, true);
        listView.setSelection(position);
        layDrawer.closeDrawer(listView);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
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

