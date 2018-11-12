package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by a on 28/11/2017.
 */

public class PatienteAdpater extends ArrayAdapter<Patiente> {
    Context ctx;
    public  static  class ViewHolder{
        TextView txtName;
        TextView txtemail;
        ImageView imgpat ;

    }

    public PatienteAdpater(@NonNull Context ctx, @LayoutRes int resource,  List<Patiente>ps) {
        super(ctx, resource ,ps);
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Patiente p =getItem(position);
        ViewHolder vHolder;
        if(convertView ==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_patiente_by_doctor,parent,false);
            vHolder= new ViewHolder();
            vHolder.txtName= (TextView)convertView.findViewById(R.id.tv_doc_name_patiente);
            vHolder.txtemail=(TextView)convertView.findViewById(R.id.tv_doc_email_patiente);
            vHolder.imgpat=(ImageView)convertView.findViewById(R.id.imgpatDoct);
            Picasso.with(ctx).load(PregnancyTrackerURLS.URL_IMAGES_PATIENTES+p.getImage()).fit().centerCrop()
                    .placeholder(R.drawable.female)
                    .error(R.drawable.female)
                    .into(vHolder.imgpat);

            convertView.setTag(vHolder);


        }else {
            vHolder=(ViewHolder) convertView.getTag();


        }
        vHolder.txtName.setText(p.getNamePatiente());
        vHolder.txtemail.setText(p.getEmail());
        return convertView;

    }
}
