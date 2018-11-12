package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;

import java.util.List;

/**
 * Created by a on 28/12/2017.
 */

public class HistoriqueNotesAdapter extends ArrayAdapter<Dairy> {

    Context ctx;
    public HistoriqueNotesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Dairy> dairies) {
        super(context, resource, dairies);
        ctx = context;
    }


    public  static  class ViewHolder{
        TextView txtdayMonth;
        TextView txtyear;
        TextView txtsubject;
        TextView txtbody;
        TextView txtdayOfWeek;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Dairy note =getItem(position);
        HistoriqueNotesAdapter.ViewHolder vHolder;
        if(convertView ==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_note,parent,false);
            vHolder= new HistoriqueNotesAdapter.ViewHolder();
            vHolder.txtbody= (TextView)convertView.findViewById(R.id.body_note);
            vHolder.txtsubject=(TextView)convertView.findViewById(R.id.subject_note);
            vHolder.txtdayMonth=(TextView)convertView.findViewById(R.id.day_of_month_note);
            vHolder.txtdayOfWeek=(TextView)convertView.findViewById(R.id.day_ofweek_note);
            vHolder.txtyear=(TextView)convertView.findViewById(R.id.year_note);
            convertView.setTag(vHolder);


        }else {
            vHolder=(HistoriqueNotesAdapter.ViewHolder) convertView.getTag();


        }


        vHolder.txtsubject.setText(note.getSubject());


        vHolder.txtbody.setText( note.getBody());
        vHolder.txtdayMonth.setText(note.getDayMonth());
        vHolder.txtdayOfWeek.setText(note.getDayOfWeek());
        vHolder.txtyear.setText(note.getYear());


        return convertView;
    }

}
