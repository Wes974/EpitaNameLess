package uqac.dim.overdex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import uqac.dim.overdex.DataBase.Heroes;

public class HeroesAdaptater extends ArrayAdapter<Heroes> implements Filterable {

    ArrayList<Heroes> heroesArrayList;
    ArrayList<Heroes> tempList;
    Context context;

    public HeroesAdaptater(Context context, ArrayList<Heroes> heroesArrayList) {
        super(context, 0, heroesArrayList);
        this.context = context;
        this.heroesArrayList = heroesArrayList;
        this.tempList = heroesArrayList;
    }

    @Override
    public int getCount() {
        return heroesArrayList.size();
    }

    @Override
    public Heroes getItem(int position) {
        return heroesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return heroesArrayList.get(position).getId();
    }

    @Override
    public View getView(int id, View convertView, ViewGroup parent) {

        Heroes currentHeroes = heroesArrayList.get(id);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        ImageView logo_heroes = (ImageView) convertView.findViewById(R.id.Logo_heroes);
        TextView name_heroes = (TextView) convertView.findViewById(R.id.Name_heroes);

        Glide.with(context).load(currentHeroes.getLogo()).into(logo_heroes);
        name_heroes.setText(currentHeroes.getName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                if (tempList == null) {
                    tempList = new ArrayList<Heroes>(heroesArrayList);
                }

                if (charSequence == null || charSequence.length() == 0) {
                    results.values = tempList;
                    results.count = tempList.size();
                }
                else {
                    ArrayList<Heroes> filteredHeroes = new ArrayList<Heroes>();
                    for (Heroes heroes : tempList) {
                        if (heroes.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase() )) {
                            filteredHeroes.add(heroes);
                        }
                    }
                    results.values = filteredHeroes;
                    results.count = filteredHeroes.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                heroesArrayList = (ArrayList<Heroes>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}