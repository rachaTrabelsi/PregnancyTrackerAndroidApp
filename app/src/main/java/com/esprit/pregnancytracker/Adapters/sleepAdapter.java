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

import com.bumptech.glide.Glide;
import com.esprit.pregnancytracker.Models.*;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.DetailsInformationActivity;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

import java.util.List;

/**
 * Created by Asus on 23/12/2017.
 */

public class sleepAdapter  extends RecyclerView.Adapter<sleepAdapter.sleepViewHolder> {
    private Context mCtx;
    private List<sleep> sleepList;
    String categorie;
    public sleepAdapter(Context mCtx,List<sleep> sleepList) {
        this.mCtx = mCtx;
        this.sleepList = sleepList;
    }

    @Override
    public sleepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.info_item, null);
        return new sleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(sleepViewHolder holder, int position) {
        sleep sp = sleepList.get(position);
        //loading the image
        Glide.with(mCtx)
                .load(PregnancyTrackerURLS.URL_IMAGE_SLEEP_INFORMATIONS+sp.getImageSleep())
                .into(holder.imageView);
        holder.textViewTitle.setText(sp.getTitlesleep());
        holder.desc = sp.getDescriptionsleep();
        holder.imageD = sp.getImageSleep();

    }

    @Override
    public int getItemCount() {

        return sleepList.size();
    }

    class sleepViewHolder extends RecyclerView.ViewHolder {
        String desc,imageD;
        TextView textViewTitle;
        ImageView imageView;

        public sleepViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.txtinfo);
            imageView = itemView.findViewById(R.id.imginfo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorie ="sleep";
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
