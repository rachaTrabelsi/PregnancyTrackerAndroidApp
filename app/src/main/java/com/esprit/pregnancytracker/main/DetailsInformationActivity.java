package com.esprit.pregnancytracker.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;


public class DetailsInformationActivity extends AppCompatActivity {

    String titreInfo,imgname,desc,categorie;
    TextView titre,description;
    Toolbar toolbar;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details_information);
        titreInfo = getIntent().getStringExtra("titre");
        imgname = getIntent().getStringExtra("image");
        desc = getIntent().getStringExtra("desc");
        description=(TextView)findViewById(R.id.descriptioninfo);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        titre = (TextView)findViewById(R.id.titreInfo);
        imageview = (ImageView)findViewById(R.id.imageDetails);
        titre.setText(titreInfo);
        categorie = getIntent().getStringExtra("categorie");

        if (categorie.equals("sleep")) {
            Glide.with(this)
                    .load(PregnancyTrackerURLS.URL_IMAGE_SLEEP_INFORMATIONS+imgname)
                    .into(imageview);


        }
        if (categorie.equals("water")) {
            Glide.with(this)
                    .load(PregnancyTrackerURLS.URL_IMAGE_WATERS_INFORMATIONS+imgname)
                    .into(imageview);


        }
        if (categorie.equals("sport")){
            Glide.with(this)
                    .load(imgname)
                    .into(imageview);

        }
        if (categorie.equals("alimentationtoeat")){
            Glide.with(this)
                    .load(PregnancyTrackerURLS.URL_IMAGES_IMAGES_TO_EAT +imgname)
                    .into(imageview);
        }
        if (categorie.equals("alimentationnottoeat")){
            Glide.with(this)
                    .load(PregnancyTrackerURLS.URL_IMAGES_IMAGES_NOT_TO_EAT+imgname)
                    .into(imageview);

        }

        description.setText(desc);
        toolbar.setTitle(titreInfo);

    }
}
