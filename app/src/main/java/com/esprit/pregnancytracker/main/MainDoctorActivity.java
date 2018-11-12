package com.esprit.pregnancytracker.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Adapters.NewsForDoctorAdapter;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.NewsForDoctor;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.SingletonMedecin;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainDoctorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView rvlistnews;
        ImageView imageDoct;
    TextView nameDoctor;
    TextView emailDoct;
    LinearLayout layoutdoct;
    List<NewsForDoctor> newslist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor2);


        rvlistnews = findViewById(R.id.listNewsForDoctorr);
        Log.e("ena fil create", "ena fil create");
        //LayoutInflater inflater = ;

        LayoutInflater inflater = getLayoutInflater();

        View layoutdoct = inflater.inflate(R.layout.nav_header_main_doctor,null);

            imageDoct= (ImageView)layoutdoct.findViewById(R.id.imageDoctorDrawer);
            nameDoctor = (TextView) layoutdoct.findViewById(R.id.name_drawer);
            emailDoct =(TextView) layoutdoct.findViewById(R.id.mail_drawer);
        GetDoctors ();
        getNews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void GetDoctors (){
        final String  REQUEST_TAG = "getdoct";
       final  Medecin med = new Medecin();
        String  url = PregnancyTrackerURLS.URL_SERVICE_GET_MEDECIN+"idMedecin=1";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(REQUEST_TAG, response.toString());
                System.out.println(response);
                try {
                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth"+array.length());
                    JSONObject d = array.getJSONObject(0);
                    med.setIdMadecin(d.getInt("idMedecin"));
                    med.setNameMedecin(d.getString("nameMedecin"));
                    med.setAdresseMaedecin(d.getString("adresseMedecin"));
                    med.setEmailMedecin(d.getString("emailMedecin"));
                    med.setImage(d.getString("image"));
                    System.out.println("list for spinner "+med.getNameMedecin());
                    nameDoctor.setText(med.getNameMedecin());
                    emailDoct.setText(med.getEmailMedecin());
                    Picasso.with(getApplicationContext()).load(med.getImage()).fit().centerCrop()
                            .placeholder(R.drawable.doctorname)
                            .error(R.drawable.doctorname)
                            .into(imageDoct);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_doctor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.img_profilmed) {
            Intent i = new Intent(MainDoctorActivity.this , DoctorProfil.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.contactPat) {
            Intent i = new Intent(MainDoctorActivity.this, DoctorMessagingContainer.class);
            startActivity(i);


        } else if (id == R.id.PatientesByDoctor) {
            Intent i = new Intent(MainDoctorActivity.this , FragmentActivity.class);
            startActivity(i);

        }else if (id == R.id.logout) {
            Intent i = new Intent(MainDoctorActivity.this , LoginAsdoctor.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    public void  getNews(){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        Log.i("getNews", "rrrrrrrrrr");
        String url = PregnancyTrackerURLS.URL_SERVICE_GET_NEWS_FOR_DOCTOR;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(REQUEST_TAG, response);
                try {

                    JSONArray NewsArray = new JSONArray(response);
                    for (int i =0; i< NewsArray.length(); i++){
                        NewsForDoctor nd = new NewsForDoctor();
                        JSONObject newsfromDB = NewsArray.getJSONObject(i);

                        nd.setId(newsfromDB.getInt("idNews"));
                        nd.setTitre(newsfromDB.getString("titre"));
                        nd.setUrl(newsfromDB.getString("lien"));
                        nd.setImage(newsfromDB.getString("image"));
                        newslist.add(nd);

                    }


                    NewsForDoctorAdapter nfa = new NewsForDoctorAdapter(getApplicationContext(),newslist);
                    rvlistnews.setAdapter(nfa);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

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
