package com.esprit.pregnancytracker.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;


import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esprit.pregnancytracker.Adapters.TestPagerAdapter;
import com.esprit.pregnancytracker.Fragments.ImcFragment;
import com.esprit.pregnancytracker.Fragments.SleepFragment;
import com.esprit.pregnancytracker.Fragments.WaterFragment;
import com.esprit.pregnancytracker.Models.Ideal;
import com.esprit.pregnancytracker.Models.PagerModel;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.ValuesClass;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.valdesekamdem.library.mdtoast.MDToast;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import dmax.dialog.SpotsDialog;



public class ImcActivity extends AppCompatActivity implements WaterFragment.OnFragmentInteractionListener {
    public static final String urlInsert =PregnancyTrackerURLS.URLSERVICE_REGISTER_PATIENTE;
    public static final String urlselectinfoFemme = PregnancyTrackerURLS.URL_SELECT_IDEAL;
    String waterValue;
    String taille1,taille2,poids1,poids2,aaa;
    Button confirm ;
    Switch cig, alcohol;
    public Dialog dialog;
    String poid,taille,mood,cigchecked,alcoholchecked;
    Double imc;
    String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    final Ideal med = new Ideal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        GetIDeal();
        confirm= (Button)findViewById(R.id.confirmbtn);
        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_imc) {

                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.

                    replaceFragment(new ImcFragment());
                }
                if (tabId == R.id.tab_water) {
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                    replaceFragment(new WaterFragment());
                }
                if (tabId == R.id.tab_sleep) {
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                    replaceFragment(new SleepFragment());
                }

            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_imc) {

                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.

                    replaceFragment(new ImcFragment());
                }
                if (tabId == R.id.tab_water) {
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                    replaceFragment(new WaterFragment());
                }
                if (tabId == R.id.tab_sleep) {
                    // The tab with id R.id.tab_favorites was reselected,
                    // change your content accordingly.
                    replaceFragment(new SleepFragment());
                }
            }
        });
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        mood="Bad";
                        break;
                    case SmileRating.GOOD:
                        mood="Good";
                        break;
                    case SmileRating.GREAT:

                        mood="Great";
                        break;
                    case SmileRating.OKAY:
                        mood="Okay";
                        break;
                    case SmileRating.TERRIBLE:
                        mood="Terrible";
                        break;
                }
            }
        });
        waterValue = ValuesClass.waterVal;
        poids1 = ValuesClass.poid1Val;
        taille1 = ValuesClass.taille1Val;
        Toast.makeText(this,"Poid "+poids1,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"taille "+taille1,Toast.LENGTH_LONG).show();
        confirmer();
    }
    public void confirmer(){

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((String.valueOf(PatienteSingleton.getInstance().getIdpatiente()))!="")&&((ValuesClass.getPoid1Val())!=null)&&((ValuesClass.getTaille1Val())!=null)&&((currentdate)!="")&&((mood)!="")&&((ValuesClass.getWaterVal())!="")&&((ValuesClass.getSleepVal())!="")) {
                    cig = (Switch) findViewById(R.id.cigaretteswictch);
                    alcohol = (Switch) findViewById(R.id.alcoholswitch);
                    if (cig.isChecked()) {
                        cigchecked = "1";
                    } else {
                        cigchecked = "0";
                    }
                    if (alcohol.isChecked()) {
                        alcoholchecked = "1";
                    } else {
                        alcoholchecked = "0";
                    }
                    imc = Double.valueOf(ValuesClass.getPoid1Val()) / (Double.valueOf(ValuesClass.getTaille1Val()) * Double.valueOf(ValuesClass.getTaille1Val()));
                    loading();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog = new Dialog(ImcActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.result_layout);
                            List<PagerModel> pagerModels = new ArrayList<PagerModel>();
                            pagerModels.add(new PagerModel(1, "IMC", "Your IMC is" + String.valueOf(imc) + "and the ideal IMC should be between " + String.valueOf(med.getImcIdealMax()) + "and " + String.valueOf(med.getImcIdealMin()),"imcpopup"));
                            pagerModels.add(new PagerModel(2, "Water", "You drink " + ValuesClass.waterVal + " L and the ideal is 2L per day ","waterpopup.jpg"));
                            pagerModels.add(new PagerModel(3, "Sleep", "You slept for " + ValuesClass.sleepVal + " and the perfect hours of sleep is 8 hours","sleep"));
                            TestPagerAdapter testPagerAdapter = new TestPagerAdapter(ImcActivity.this, pagerModels);
                            AutoScrollViewPager pager = (AutoScrollViewPager) dialog.findViewById(R.id.viewPager);
                            pager.setAdapter(testPagerAdapter);
                            CirclePageIndicator circlePageIndicator = (CirclePageIndicator) dialog.findViewById(R.id.pageIndicator);
                            circlePageIndicator.setViewPager(pager);
                            circlePageIndicator.setCurrentItem(0);
                            dialog.show();
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    MDToast mdToast = MDToast.makeText(ImcActivity.this, "No connection", 5, MDToast.TYPE_ERROR);
                                    mdToast.show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();
                            // Adding All values to Params.
                            // The firs argument should be same sa your MySQL database table columns.
                            params.put("idPatiente",(String.valueOf(PatienteSingleton.getInstance().getIdpatiente())));
                            params.put("weight", ValuesClass.getPoid1Val());
                            params.put("taille", ValuesClass.getTaille1Val());
                            params.put("dateState", currentdate);
                            params.put("water", ValuesClass.waterVal);
                            params.put("sleep", ValuesClass.sleepVal);
                            params.put("mood", mood);
                            params.put("cigarette", cigchecked);
                            params.put("alcohol", alcoholchecked);
                            params.put("imcState", String.valueOf(imc));
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(ImcActivity.this);
                    // Adding the StringRequest object into requestQueue.
                    requestQueue.add(stringRequest);

                }
                else {
                    Toast.makeText(ImcActivity.this,"Please enter all the informations",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentContainer,fragment);
        ft.commit();
    }
    public void GetIDeal (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        // final List<Medecin> medecinsforSpinner = new ArrayList<>();



        StringRequest strReq = new StringRequest(urlselectinfoFemme, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(REQUEST_TAG, response.toString());
                //Patiente pat = gson.fromJson(response,Patiente.class);
                System.out.println(response);
                try {

                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth"+array.length());
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject d = array.getJSONObject(i);

                        med.setIdIdeal(d.getInt("idIdeal"));
                        med.setImcIdealMax((float) d.getDouble("imcIdealMax"));
                        aaa=med.getImcIdealMax().toString();

                        med.setImcIdealMin((float) d.getDouble("imcIdealMin"));
                        med.setGetRecommendedWeightMax((float) d.getDouble("recommendedWeightMax"));
                        med.setRecommendedWeightMin((float) d.getDouble("recommendedWeightMin"));
                        med.setWeightPerWeek((float) d.getDouble("weightPerWeek"));
                        System.out.println("list for spinner "+med.getImcIdealMax());
                        // waterF.setText(med.getImcIdealMax().toString());


                    }


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
        //  AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
        Toast.makeText(ImcActivity.this,aaa,Toast.LENGTH_LONG).show();
        RequestQueue rq = Volley.newRequestQueue(ImcActivity.this);
        rq.add(strReq);
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {

    }
    public void loading (){
        final android.app.AlertDialog dialog = new SpotsDialog(this,R.style.Custom);
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

        handler.postDelayed(runnable, 7000);
    }
}
