package com.esprit.pregnancytracker.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esprit.pregnancytracker.Adapters.sleepAdapter;
import com.esprit.pregnancytracker.Adapters.sleepofflineadapter;
import com.esprit.pregnancytracker.Adapters.sportAdapter;
import com.esprit.pregnancytracker.Adapters.sportofflineadapter;
import com.esprit.pregnancytracker.Adapters.waterAdapter;
import com.esprit.pregnancytracker.Adapters.waterofflineAdapter;
import com.esprit.pregnancytracker.Models.DataContextInformations;
import com.esprit.pregnancytracker.Models.sleep;
import com.esprit.pregnancytracker.Models.sport;
import com.esprit.pregnancytracker.Models.water;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class sportwatersleepActivity extends AppCompatActivity {
    ImageView img;
    RecyclerView recyclerView2;
    RecyclerView.LayoutManager layoutManager;
    String categorie;
    List<sport> sports;
    List<sleep> sleeps;
    List<water> waters;
    public static final String urlsport = PregnancyTrackerURLS.URLSERVICE_SELECT_SPORT ;
    public static final String urlsleep =  PregnancyTrackerURLS.URLSERVICE_SELECT_SLEEP;
    public static final String urlwater =  PregnancyTrackerURLS.URLSERVICE_SELECT_WATER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportwatersleep);
        img = (ImageView)findViewById(R.id.imginfo);
        recyclerView2 = (RecyclerView)findViewById(R.id.recylcerViewSportSleepWater);
        recyclerView2.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this) ;
        recyclerView2.setLayoutManager(layoutManager);
        loading();
        sports = new ArrayList<>();
        sleeps = new ArrayList<>();
        waters = new ArrayList<>();
        categorie = getIntent().getStringExtra("categorie");
        if (categorie.equals("sport")){
          loadSport();
        }
       if (categorie.equals("sleep")) {
            loadSleep();
        }
        if (categorie.equals("water")) {
            loadwater();
        }


    }
    private void loadSport() {
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlsport,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject sp = array.getJSONObject(i);

                                //adding the product to product list
                                sports.add(new sport(
                                        sp.getInt("idSport"),
                                        sp.getString("titlesport"),
                                        sp.getString("descriptionsport"),
                                        sp.getString("imagesport")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            sportAdapter adapter = new sportAdapter(sportwatersleepActivity.this, sports);
                            recyclerView2.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sports= DataContextInformations.getsport();
                        sportofflineadapter adapter = new sportofflineadapter(sportwatersleepActivity.this, sports);
                        recyclerView2.setAdapter(adapter);
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void loadSleep() {
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlsleep,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject sp = array.getJSONObject(i);

                                //adding the product to product list
                                sleeps.add(new sleep(
                                        sp.getInt("idSleep"),
                                        sp.getString("titlesleep"),
                                        sp.getString("descriptionsleep"),
                                        sp.getString("imagesleep")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            sleepAdapter adapter = new sleepAdapter(sportwatersleepActivity.this, sleeps);
                            recyclerView2.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sleeps= DataContextInformations.getsleep();
                        sleepofflineadapter adapter = new sleepofflineadapter(sportwatersleepActivity.this, sleeps);
                        recyclerView2.setAdapter(adapter);
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void loadwater() {
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlwater,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject sp = array.getJSONObject(i);

                                //adding the product to product list
                                waters.add(new water(
                                        sp.getInt("idWater"),
                                        sp.getString("titleWater"),
                                        sp.getString("descriptionWater"),
                                        sp.getString("imageWater")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview

                            waterAdapter adapter = new waterAdapter(sportwatersleepActivity.this, waters);
                            recyclerView2.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DataContextInformations.setWater();
                        waters= DataContextInformations.getwater();
                        waterofflineAdapter adapter = new waterofflineAdapter(sportwatersleepActivity.this, waters);
                        recyclerView2.setAdapter(adapter);
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

        handler.postDelayed(runnable, 10000);
    }

}
