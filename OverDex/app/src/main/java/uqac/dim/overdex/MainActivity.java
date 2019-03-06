package uqac.dim.overdex;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import uqac.dim.overdex.DataBase.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelp = new DatabaseHelper(getApplicationContext());

        try {
            dbHelp.createDatabase();
        }
        catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            dbHelp.openDatabase();
            Log.v("DIM", "DB open");
        }
        catch(SQLException sqle){
            throw sqle;
        }
    }
}

