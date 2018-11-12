package com.esprit.pregnancytracker.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esprit.pregnancytracker.Adapters.alimentationtoeatAdapter;
import com.esprit.pregnancytracker.Adapters.informationsAdapter;
import com.esprit.pregnancytracker.Adapters.nottoeatAdapteroffline;
import com.esprit.pregnancytracker.Adapters.toeatAdapteroffline;
import com.esprit.pregnancytracker.Models.DataContextInformations;
import com.esprit.pregnancytracker.Models.alimentationnottoeat;
import com.esprit.pregnancytracker.Models.alimentationtoeat;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class InformationActivity extends AppCompatActivity {
    alimentationnottoeat a = new alimentationnottoeat();
    ImageView img;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    List<alimentationnottoeat> alimentationnottoeats;
    List<alimentationtoeat> alimentationtoeats;
    String categorie ;
    public static final String urlnottoeat = PregnancyTrackerURLS.URL_SERVICE_INFO_NOT_TO_EAT;
    public static final String urltoeat = PregnancyTrackerURLS.URL_SERVICE_INFO_TO_EAT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        loading();
        categorie=getIntent().getStringExtra("categorie");
       // Toast.makeText(InformationActivity.this,categorie,Toast.LENGTH_LONG).show();
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_alimentationtoeat) {

                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    loading();
                    loadAlimentationtoeat();


                }
                if (tabId == R.id.tab_alimentationnottoeat) {
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                    loading();
                    loadAlimentationnottoeat();
                }
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_alimentationtoeat) {

                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    loadAlimentationtoeat();


                }
                if (tabId == R.id.tab_alimentationnottoeat) {
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                    loadAlimentationnottoeat();
                }
            }
        });
        if (categorie == "alimentation"){
           // Toast.makeText(InformationActivity.this,"aa",Toast.LENGTH_LONG).show();
        }
        else if (categorie == "sport"){

        }
        img = (ImageView)findViewById(R.id.imginfo);
        recyclerView = (RecyclerView)findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this) ;
        recyclerView.setLayoutManager(layoutManager);

        alimentationnottoeats = new ArrayList<>();
        alimentationtoeats = new ArrayList<>();

        loadAlimentationtoeat();

    }
    private void loadAlimentationnottoeat() {
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnottoeat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject nottoeat = array.getJSONObject(i);

                                //adding the product to product list

                                alimentationnottoeats.add(new alimentationnottoeat(
                                        nottoeat.getInt("idnotAlim"),
                                        nottoeat.getString("titlenot"),
                                        nottoeat.getString("descriptionnot"),
                                        nottoeat.getString("imagenottoeat")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            informationsAdapter adapter = new informationsAdapter(InformationActivity.this, alimentationnottoeats);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DataContextInformations.setAlimnottoeat();
                        alimentationnottoeats= DataContextInformations.getalimentationnottoealt();
                        nottoeatAdapteroffline adapter = new nottoeatAdapteroffline(InformationActivity.this, alimentationnottoeats);
                        recyclerView.setAdapter(adapter);                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }
    private void loadAlimentationtoeat() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urltoeat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject toeat = array.getJSONObject(i);

                                //adding the product to product list
                                alimentationtoeats.add(new alimentationtoeat(
                                        toeat.getInt("idAlimentation"),
                                        toeat.getString("title"),
                                        toeat.getString("description"),
                                        toeat.getString("imagetoeat")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            alimentationtoeatAdapter adapter = new alimentationtoeatAdapter(InformationActivity.this, alimentationtoeats);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DataContextInformations.setAlimList();
                        alimentationtoeats= DataContextInformations.getalimentationtoealt();
                        toeatAdapteroffline adapter = new toeatAdapteroffline(InformationActivity.this, alimentationtoeats);
                        recyclerView.setAdapter(adapter);
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void loading (){
        final android.app.AlertDialog dialog = new SpotsDialog(this);
        dialog.show();
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 5000);
    }
}
