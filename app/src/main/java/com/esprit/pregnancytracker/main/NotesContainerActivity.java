package com.esprit.pregnancytracker.main;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.esprit.pregnancytracker.Adapters.HistoriqueNotesAdapter;
import com.esprit.pregnancytracker.Fragments.HistoriqueNotesfragment;
import com.esprit.pregnancytracker.Fragments.ShowDetailRendezVousForPatienteFragment;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PatienteSingleton;

public class NotesContainerActivity extends AppCompatActivity {
int idpatString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_container);
        idpatString = PatienteSingleton.getInstance().getIdpatiente();

        Log.i ("idpatString" , String.valueOf(idpatString) );
        replaceFrag(HistoriqueNotesfragment.newInstance( String.valueOf(idpatString) , ""));
    }
    public void replaceFrag(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCrudNotesContainer,fragment).commit();
    }




    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
