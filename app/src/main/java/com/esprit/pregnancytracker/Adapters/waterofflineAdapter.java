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

import com.esprit.pregnancytracker.Models.water;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.DetailsInformationActivity;

import java.util.List;


/**
 * Created by Asus on 04/01/2018.
 */

public class waterofflineAdapter  extends RecyclerView.Adapter<waterofflineAdapter.waterViewHolder>  {
    private Context mCtx;
    private List<water> waters;
    String categorie;
    public waterofflineAdapter(Context mCtx,List<water> waters) {
        this.mCtx = mCtx;
        this.waters = waters;
    }

    @Override
    public waterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.info_item, null);
        return new waterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(waterViewHolder holder, int position) {
        water wt = waters.get(position);
        //loading the image

        holder.textViewTitle.setText(wt.getTitleWater());
        holder.desc = wt.getDescriptionWater();
        holder.imageD = wt.getImageWater();

    }

    @Override
    public int getItemCount() {
        return waters.size();
    }

    class waterViewHolder extends RecyclerView.ViewHolder {
        String desc,imageD;
        TextView textViewTitle;
        ImageView imageView;

        public waterViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.txtinfo);
            imageView = itemView.findViewById(R.id.imginfo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorie="water";
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
