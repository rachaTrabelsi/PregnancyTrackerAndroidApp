package com.esprit.pregnancytracker.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.esprit.pregnancytracker.Fragments.HistoriqueDoctorFragment;
import com.esprit.pregnancytracker.Fragments.HistoriqueMessaging;
import com.esprit.pregnancytracker.Fragments.MessagingDoctorFragment;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PatienteSingleton;

public class DoctorMessagingContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_messaging_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        replaceFrag( HistoriqueDoctorFragment.newInstance("1" , ""));

    }
    public void replaceFrag(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.doctorMessagingContainr,fragment).commit();
    }
}
