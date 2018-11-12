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
import com.esprit.pregnancytracker.Models.alimentationnottoeat;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.DetailsInformationActivity;
import com.esprit.pregnancytracker.main.HomeActivity;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Asus on 23/12/2017.
 */


public class informationsAdapter extends RecyclerView.Adapter<informationsAdapter.informationViewHolder>{
    private Context mCtx;
    private List<alimentationnottoeat> nottoeatlist;
    String categorie;
    public informationsAdapter(Context mCtx, List<alimentationnottoeat> nottoeatlist) {
        this.mCtx = mCtx;
        this.nottoeatlist = nottoeatlist;
    }

    @Override
    public informationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.info_item, null);
        return new informationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(informationViewHolder holder, int position) {
        alimentationnottoeat nottoeat = nottoeatlist.get(position);
        //loading the image

        Picasso.with(mCtx)
                .load(PregnancyTrackerURLS.URL_IMAGES_IMAGES_NOT_TO_EAT+nottoeat.getImagenotoeat())
                .into(holder.imageView);
        holder.textViewTitle.setText(nottoeat.getTitlenot());
        holder.desc = nottoeat.getDescriptionnot();
        holder.imageD = nottoeat.getImagenotoeat();

    }

    @Override
    public int getItemCount() {
        return nottoeatlist.size();
    }

    class informationViewHolder extends RecyclerView.ViewHolder {
        String desc,imageD;
        TextView textViewTitle;
        ImageView imageView;
        public informationViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtinfo);
            imageView = itemView.findViewById(R.id.imginfo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorie = "alimentationnottoeat";
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
