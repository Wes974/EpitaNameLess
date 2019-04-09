package uqac.dim.overdex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide; // GLIDE !

import java.util.ArrayList;

import uqac.dim.overdex.DataBase.Skins;


public class SkinsAdaptater extends RecyclerView.Adapter<SkinsAdaptater.ViewHolder> {


    ArrayList<Skins> skinsArrayList;
    Context context;

    public SkinsAdaptater(Context context, ArrayList<Skins> skinsArrayList) {
        this.context = context;
        this.skinsArrayList = skinsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycleview_skins, viewGroup, false);
        SkinsAdaptater.ViewHolder viewHolder = new SkinsAdaptater.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(skinsArrayList.get(i).getImage()).into(viewHolder.Image);
    }

    @Override
    public int getItemCount() {
        return skinsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView Image;
        public ViewHolder(View itemView) {
            super(itemView);
            Image = (ImageView) itemView.findViewById(R.id.skin);
        }

    }
}
