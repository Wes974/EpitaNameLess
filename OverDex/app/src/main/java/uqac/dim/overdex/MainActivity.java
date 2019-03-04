package uqac.dim.overdex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import uqac.dim.overdex.DataBase.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
    }
}
