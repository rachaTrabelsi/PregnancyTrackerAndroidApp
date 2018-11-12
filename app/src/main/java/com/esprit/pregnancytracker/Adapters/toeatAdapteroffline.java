package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.esprit.pregnancytracker.Models.alimentationtoeat;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.DetailsInformationActivity;



import java.io.ByteArrayOutputStream;
import java.util.List;



/**
 * Created by Asus on 04/01/2018.
 */

public class toeatAdapteroffline extends RecyclerView.Adapter<toeatAdapteroffline.alimentationtoeatViewHolder> {
    private Context mCtx;
    private List<alimentationtoeat> toeatlist;
    String categorie;
    public toeatAdapteroffline(Context mCtx, List<alimentationtoeat> toeatlist) {
        this.mCtx = mCtx;
        this.toeatlist = toeatlist;
    }

    @Override
    public alimentationtoeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.info_item, null);
        return new alimentationtoeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(alimentationtoeatViewHolder holder, int position) {
        alimentationtoeat toeat = toeatlist.get(position);
        //loading the image

        holder.textViewTitle.setText(toeat.getTitle());
        holder.desc = toeat.getDescription();
        holder.imageD = toeat.getImagetoeat();


    }

    @Override
    public int getItemCount() {
        return toeatlist.size();
    }

    class alimentationtoeatViewHolder extends RecyclerView.ViewHolder {
        String desc,imageD;
        TextView textViewTitle;
        ImageView imageView;

        public alimentationtoeatViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.txtinfo);
            imageView = itemView.findViewById(R.id.imginfo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorie = "alimentationtoeat";
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
