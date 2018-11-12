package com.esprit.pregnancytracker.main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esprit.pregnancytracker.Fragments.HistoriqueMessaging;
import com.esprit.pregnancytracker.Fragments.MessagingPatienteFragment;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PatienteSingleton;

public class MessagingContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_container);
        replaceFrag( HistoriqueMessaging.newInstance(String.valueOf(PatienteSingleton.getInstance().getIdpatiente()) , ""));

    }
    public void replaceFrag(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.messagingContainerFrame,fragment).commit();
    }
}
