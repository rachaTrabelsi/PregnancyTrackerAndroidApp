package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.esprit.pregnancytracker.Fragments.HistoriqueMessaging;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.Message;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MessagesHistoriqueAdapter extends ArrayAdapter <Message>{
    Context ctx ;
   List<Message> listMessages;
    ListView lv_msg;
    public MessagesHistoriqueAdapter(@NonNull Context ctx, @NonNull List<Message> listMessages) {
        super(ctx, 0, 0, listMessages);
        this.ctx  =ctx ;
       this.listMessages =  new ArrayList<>();


    }
    public  static  class ViewHolder{
        TextView txtexpedname ;
        TextView txtdate;
        TextView txtsubject;
        TextView txtbody;
        ImageView imgExped;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Message msg =getItem(position);
        MessagesHistoriqueAdapter.ViewHolder vHolder;
        if(convertView ==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_message,parent,false);
            vHolder= new MessagesHistoriqueAdapter.ViewHolder();
            vHolder.txtbody= (TextView)convertView.findViewById(R.id.bodyhisto);
            vHolder.txtsubject=(TextView)convertView.findViewById(R.id.subjectpart);
            vHolder.txtexpedname=(TextView)convertView.findViewById(R.id.nom_expid);
            vHolder.txtdate=(TextView)convertView.findViewById(R.id.date_Msg);
            vHolder.imgExped=(ImageView) convertView.findViewById(R.id.image_exp);
            convertView.setTag(vHolder);


        }else {
            vHolder=(MessagesHistoriqueAdapter.ViewHolder) convertView.getTag();


        }


        vHolder.txtsubject.setText(msg.getSubject());
        vHolder.txtbody.setText( msg.getBody());
        vHolder.txtexpedname.setText(msg.getNameExpd());

        vHolder.txtdate.setText(msg.getDateEnvoi());


        Picasso.with(ctx).load(PregnancyTrackerURLS.URL_IMAGES_PATIENTES+msg.getImage()).fit().centerCrop()
                .placeholder(R.drawable.doctorname)
                .error(R.drawable.doctorname)
                .into(vHolder.imgExped);





        return convertView;
    }





}
