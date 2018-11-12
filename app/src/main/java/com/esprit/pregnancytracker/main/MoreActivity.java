package com.esprit.pregnancytracker.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Fragments.MessagingPatienteFragment;
import com.esprit.pregnancytracker.Models.SelfBidou;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

public class MoreActivity extends AppCompatActivity {
    public final  static String INFO_USER= "login";
    public static final String url = PregnancyTrackerURLS.URLSERVICE_LOGIN_PATIENTE;
    LinearLayout logoutlayout;
    LinearLayout rendezVouslayout;
    LinearLayout SendingMessageLayout;

    LinearLayout SelfBidouLayout;
    LinearLayout profilPatienteLayout;
    LinearLayout noteMenuLayout;
        String CONNECTED_USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        CONNECTED_USER = String.valueOf(PatienteSingleton.getInstance().getIdpatiente());
        profilPatienteLayout =(LinearLayout)findViewById(R.id.profilPatMenulayout);

        profilPatienteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this,ShowProfile.class);
                startActivity(intent);

            }
        });



        rendezVouslayout = (LinearLayout) findViewById(R.id.get_rendez_vous);
        rendezVouslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this,ShowDetailsRendezVous.class);
                intent.putExtra("Patid",String.valueOf( PatienteSingleton.getInstance().getIdpatiente()));
                startActivity(intent);
            }
        });

        SendingMessageLayout =(LinearLayout) findViewById(R.id.sendingMesaageToDoctor);
        SendingMessageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreActivity.this , MessagingContainerActivity.class);
                startActivity(i);

            }
        });

        SelfBidouLayout =(LinearLayout) findViewById(R.id.SelfBidoulayoutMenu);
        SelfBidouLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this,SelfBidouActivity.class);
                startActivity(intent);
            }
        });
        noteMenuLayout  =(LinearLayout) findViewById(R.id.NoteLayoutMenu);

        noteMenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this,NotesContainerActivity.class);
                startActivity(intent);

            }
        });

        logoutlayout = (LinearLayout)findViewById(R.id.logoutlayout);
        logoutlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().clear();
                getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().commit();
                Intent intent = new Intent(MoreActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }


    public void replaceFrag(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMenu,fragment).commit();
    }

}
