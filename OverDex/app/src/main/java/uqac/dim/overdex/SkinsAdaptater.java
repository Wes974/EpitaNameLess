package uqac.dim.overdex;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide; // GLIDE !

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.Skins;


public class SkinsAdaptater extends ArrayAdapter<Skins> {


    ArrayList<Skins> skinsArrayList;
    Context context;

    public SkinsAdaptater(Context context, ArrayList<Skins> skinsArrayList) {
        super(context, 0, skinsArrayList);
        this.context = context;
        this.skinsArrayList = skinsArrayList;
    }

    //To do : getView() mais attention aux multiples extends
}
