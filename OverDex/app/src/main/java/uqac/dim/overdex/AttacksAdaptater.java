package uqac.dim.overdex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.Attacks;


public class AttacksAdaptater extends ArrayAdapter<Attacks> {

    ArrayList<Attacks> attacksArrayList;
    Context context;

    public AttacksAdaptater(Context context, ArrayList<Attacks> attacksArrayList) {
        super(context, 0, attacksArrayList);
        this.context = context;
        this.attacksArrayList = attacksArrayList;
    }

    @Override
    public View getView(int id, View convertView, ViewGroup parent) {

        Attacks currentAttacks = attacksArrayList.get(id);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview_attacks, parent, false);
        }

        ImageView picture_attack = (ImageView) convertView.findViewById(R.id.Picture_attacks);
        TextView name_attacks = (TextView) convertView.findViewById(R.id.Name_attacks);
        TextView description_attacks = (TextView) convertView.findViewById(R.id.Descr_attacks);

        Glide.with(context).load(currentAttacks.getLogo()).into(picture_attack);
        name_attacks.setText(currentAttacks.getName());
        description_attacks.setText(currentAttacks.getDescrition());
        return convertView;
    }
}
