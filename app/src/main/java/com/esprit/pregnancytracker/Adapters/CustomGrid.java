package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esprit.pregnancytracker.Models.SelfBidou;
import com.esprit.pregnancytracker.Models.sport;
import com.esprit.pregnancytracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 30/12/2017.
 */

public class CustomGrid extends BaseAdapter {
    List<SelfBidou> selfies = new ArrayList<>();
   static SelfBidou selfi = new SelfBidou();

      Context ctx ;
    public CustomGrid(Context ctx ,   List<SelfBidou> selfies) {
        this.selfies = selfies;
        this.ctx = ctx;
    }

    public  static  class ViewHolder {
        TextView nbreweek;
        ImageView imgSelfi;

    }


    @Override
    public int getCount() {
        return selfies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub

        CustomGrid.ViewHolder vHolder=null;

        if (view == null) {

             LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.single_self,viewGroup,false);
            vHolder= new CustomGrid.ViewHolder();
            vHolder.nbreweek= view.findViewById(R.id.weeknbre);
            vHolder.imgSelfi=view.findViewById(R.id.grid_image);
            selfi = selfies.get(i);

            vHolder.nbreweek.setText(selfi.getWeek());

           // Picasso.with(ctx).load(selfi.getImageSelfi()).into(vHolder.imgSelfi);
            Picasso.with(ctx).load(selfi.getImageSelfi()).fit().centerCrop()
                    .placeholder(R.drawable.customizedplus)
                    .error(R.drawable.oups)
                    .into(vHolder.imgSelfi);

            view.setTag(vHolder);
        } else {
            vHolder=(CustomGrid.ViewHolder) view.getTag();
        }
        /*selfi = selfies.get(i);

        vHolder.nbreweek.setText(selfi.getWeek());

        Picasso.with(ctx).load(selfi.getImageSelfi()).into(vHolder.imgSelfi);
        Picasso.with(ctx).load(selfi.getImageSelfi()).fit().centerCrop()
                .placeholder(R.drawable.plus)
                .error(R.drawable.sad)
                .into(vHolder.imgSelfi);*/


        return view;


}


    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
