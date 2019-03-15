package uqac.dim.overdex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.Heroes;

public class HeroesAdaptater extends ArrayAdapter<Heroes> {

    ArrayList<Heroes> heroesArrayList;
    Context context;

    public HeroesAdaptater(Context context, ArrayList<Heroes> heroesArrayList) {
        super(context, 0, heroesArrayList);
        this.context = context;
        this.heroesArrayList = heroesArrayList;
    }

    @Override
    public View getView(int id, View convertView, ViewGroup parent) {

        Heroes currentheroes = heroesArrayList.get(id);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView id_heroes = (TextView) convertView.findViewById(R.id.ID_heroes);
        TextView name_heroes = (TextView) convertView.findViewById(R.id.Name_heroes);
        id_heroes.setText(Integer.toString(currentheroes.getId()));
        name_heroes.setText(currentheroes.getName());

        Log.v("DIM",Integer.toString(currentheroes.getId()));
        Log.v("DIM",currentheroes.getName());

        return convertView;
    }
}