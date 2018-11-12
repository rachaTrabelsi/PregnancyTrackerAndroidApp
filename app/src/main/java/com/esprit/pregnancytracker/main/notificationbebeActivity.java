package com.esprit.pregnancytracker.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.notification;
import com.squareup.picasso.Picasso;

import com.esprit.pregnancytracker.Models.*;

public class notificationbebeActivity extends AppCompatActivity {
    ImageView imgnotifbb;
    TextView titrebb,descbb;
    notification not = new notification();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationbebe);
        imgnotifbb = (ImageView) findViewById(R.id.imagenotifbb);
        titrebb = (TextView)findViewById(R.id.titrenotifbb);
        descbb = (TextView)findViewById(R.id.descriptionnotifbb);
        descbb.setText(not.getDescnotif());
        titrebb.setText(not.getTitrenotif());
        Picasso.with(notificationbebeActivity.this).load(PregnancyTrackerURLS.URL_IMAGE_WEEKS+not.getImagenotif()).into(imgnotifbb);

    }
}
