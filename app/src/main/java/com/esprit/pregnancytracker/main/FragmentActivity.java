package com.esprit.pregnancytracker.main;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esprit.pregnancytracker.Fragments.PatientesByDoctor;
import com.esprit.pregnancytracker.Fragments.dummy.DummyContent;
import com.esprit.pregnancytracker.R;

public class FragmentActivity extends AppCompatActivity implements PatientesByDoctor.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        replaceFrag(new PatientesByDoctor());

    }

    public void replaceFrag(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
