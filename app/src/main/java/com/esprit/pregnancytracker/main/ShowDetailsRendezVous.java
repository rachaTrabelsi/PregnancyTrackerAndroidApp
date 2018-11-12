package com.esprit.pregnancytracker.main;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Fragments.AjoutRendezVousFragment;
import com.esprit.pregnancytracker.Fragments.ShowDetailRendezVousForPatienteFragment;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShowDetailsRendezVous extends AppCompatActivity  implements  ShowDetailRendezVousForPatienteFragment.OnFragmentInteractionListener{

    String idpatString;
   static RendezVous rdvglob ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_rendez_vous);
        rdvglob= new RendezVous();




            Bundle extras = getIntent().getExtras();

                idpatString = extras.getString("Patid");

        Log.i ("idpatString" , String.valueOf(idpatString ));
      //  getRendezfordetails();

        replaceFrag(ShowDetailRendezVousForPatienteFragment.newInstance(idpatString  , ""));





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



    //fragmentDetailRendezVousContainer

    public void replaceFrag(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDetailRendezVousContainer,fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    public void  getRendezfordetails (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String URLSERVICERendezVous= PregnancyTrackerURLS.URL_SERVICE_SHOWRENDEZVOUS_IF_EXISTS;
        String url = URLSERVICERendezVous +"idPatiente="+PatienteSingleton.getInstance().getIdpatiente()+"&etat=0";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                Log.i(REQUEST_TAG, response.toString());
                try {
                    JSONArray rdvArray = new JSONArray(response);
                    ;
                    JSONObject rdv = rdvArray.getJSONObject(0);
                    rdvglob.setIdMedecin(rdv.getInt("idMedecin"));
                    rdvglob.setIdPatiente(rdv.getInt("idPatiente"));
                    rdvglob.setDateRendezVous(rdv.getString("dateRendezVous"));
                    Log.i("reeeendezvous", rdvglob.getDateRendezVous());
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                    Date convertedDate = new Date();
                    try {
                        convertedDate = dateFormat.parse(rdvglob.getDateRendezVous());
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(convertedDate);
                    if ((currentTime.getYear()> convertedDate.getYear())
                            ||( convertedDate.getMonth() < currentTime.getMonth())
                            || currentTime.getDay()<convertedDate.getDay() ){

                        UpdateRDV();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");
            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                VolleyLog.d("getRendezfordetails", "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }

    public void UpdateRDV(){

        String url= PregnancyTrackerURLS.URL_SERVICE_UPDATE_RENDEZ_VOUS+"idRendezVous="+rdvglob.getIdRendezVous();
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("responseUdatingRDV" ,response );


            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }





}
