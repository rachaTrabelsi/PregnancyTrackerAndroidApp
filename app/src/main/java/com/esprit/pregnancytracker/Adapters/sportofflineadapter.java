package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.pregnancytracker.Models.sport;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.DetailsInformationActivity;

import java.util.List;


/**
 * Created by Asus on 04/01/2018.
 */

public class sportofflineadapter extends RecyclerView.Adapter<sportofflineadapter.sportViewHolder> {
    private Context mCtx;
    private List<sport> sports;
    String categorie;
    public sportofflineadapter(Context mCtx,List<sport> sports) {
        this.mCtx = mCtx;
        this.sports = sports;
    }

    @Override
    public sportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.info_item, null);
        return new sportViewHolder(view);
    }


    @Override
    public void onBindViewHolder(sportViewHolder holder, int position) {
        sport sp = sports.get(position);
        //loading the image
        holder.textViewTitle.setText(sp.getNameSport());
        holder.desc = sp.getDescriptionSport();
        holder.imageD = sp.getImageSoprt();

    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    class sportViewHolder extends RecyclerView.ViewHolder {
        String desc,imageD;
        TextView textViewTitle;
        ImageView imageView;

        public sportViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.txtinfo);
            imageView = itemView.findViewById(R.id.imginfo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorie = "sport";
                    Intent intent = new Intent(mCtx.getApplicationContext(), DetailsInformationActivity.class);
                    intent.putExtra("titre", textViewTitle.getText());
                    intent.putExtra("image", imageD);
                    intent.putExtra("desc", desc);
                    intent.putExtra("categorie", categorie);
                    mCtx.startActivity(intent);
                    Toast.makeText(mCtx.getApplicationContext(),imageD,Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
