package com.esprit.pregnancytracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.app.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a on 27/12/2017.
 */

public class PushingNtification {
    public  Context mContext ;
    public final String CONNECTEDUSER;
    public PushingNtification(Context mContext, String CONNECTEDUSER) {
        this.mContext= mContext;
        this.CONNECTEDUSER = CONNECTEDUSER;
    }

    public List<String> getTokensfcm(){
        final List <String> listfcm = new ArrayList<>();

        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(Request.Method.GET, PregnancyTrackerURLS.URLSERVICELISTgetFCMtoken, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray tokensexits = new JSONArray(response);
                    for (int i =0 ; i< tokensexits.length(); i++){
                        JSONObject d =  tokensexits.getJSONObject(i);
                        String  token = d.getString("token");
                        Log.i("TOKENfb",token );
                        listfcm.add(token);

                    }
                    Log.i("listFCM",String.valueOf( listfcm.size()));

                    SharedPreferences pref = mContext.getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
                    final String regId = pref.getString("regId", null);

                    Log.e("TAG", "Firebase reg id: " + regId);

                    if (!TextUtils.isEmpty(regId)) {
                        Toast.makeText(mContext.getApplicationContext(), "Firebase Reg Id: " + regId, Toast.LENGTH_LONG).show();
                        System.out.println("taktouk     " + regId);
                        Log.i("mawjoudwelale" , String.valueOf(listfcm.size()));

                        Log.i("mawjoudwelale" , String.valueOf(listfcm.contains(regId)));


                        if (listfcm.contains(regId)) {
                            Log.i("insertedToken"," Toekn exist deja ");
                            PushNotif();
                            Log.i("sentNotif"," notif sent  ");
                        }else {
                            insertToken(regId);
                            Log.i("insertedNewToken","Tokeninserted");
                            PushNotif();
                        }


                    }






                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("listFCM","liste vide");
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }


        );


        return listfcm ;
    }



   /* public  void displayFirebaseRegId(String CONNECTEDUSER) {
        SharedPreferences pref = mContext.getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        final String regId = pref.getString("regId", null);

        Log.e("TAG", "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            Toast.makeText(mContext.getApplicationContext(), "Firebase Reg Id: " + regId, Toast.LENGTH_LONG).show();
            System.out.println("taktouk     " + regId);
            Log.i("mawjoudwelale" , String.valueOf(getTokensfcm().size()));

            Log.i("mawjoudwelale" , String.valueOf(getTokensfcm().contains(regId)));


            if (getTokensfcm().contains(regId)) {
                Log.i("insertedToken"," Toekn exist deja ");
                PushNotif(CONNECTEDUSER);
                Log.i("sentNotif"," notif sent  ");
            }else {
                insertToken(regId,CONNECTEDUSER );
                Log.i("insertedNewToken","Tokeninserted");
                PushNotif(CONNECTEDUSER);
            }


        }
    }*/



    public void  PushNotif () {

        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String URL=  PregnancyTrackerURLS.URLSERVICELISTPushFCM +"?idUser="+CONNECTEDUSER;
        StringRequest tokenrequest = new StringRequest(Request.Method.POST,  URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        AppSingleton.getInstance(mContext.getApplicationContext()).addToRequestQueue(tokenrequest, REQUEST_TAG);


        Toast.makeText(mContext.getApplicationContext(), "Firebase Reg Id is not received yet!", Toast.LENGTH_LONG).show();


    }


    public void insertToken (String  regID   ) {

        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String URL=PregnancyTrackerURLS.URLSERVICELISTgetFCMtoken+"idPatiente="
                + CONNECTEDUSER + "&token=" + regID;
        StringRequest tokenrequest = new StringRequest(Request.Method.POST,  URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        AppSingleton.getInstance(mContext.getApplicationContext()).addToRequestQueue(tokenrequest, REQUEST_TAG);


        Toast.makeText(mContext.getApplicationContext(), "Firebase Reg Id is not received yet!", Toast.LENGTH_LONG).show();


    }


}
