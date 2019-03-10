package uqac.dim.overdex;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class HeroesCursorAdaptater extends CursorAdapter {

        public HeroesCursorAdaptater(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView ID_heroes = (TextView)view.findViewById(R.id.ID_heroes);
            TextView Name_heroes = (TextView)view.findViewById(R.id.Name_heroes);

            int Id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String Name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));

            ID_heroes.setText(String.valueOf(Id));
            Name_heroes.setText(Name);
        }
}
