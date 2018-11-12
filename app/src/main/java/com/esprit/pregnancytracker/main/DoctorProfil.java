package com.esprit.pregnancytracker.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.SingletonMedecin;
import com.esprit.pregnancytracker.utils.UrlAddress;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorProfil extends AppCompatActivity  {
    TextView tvName , tvAdresse, tvEmail;
 ImageView imgprofil;
    public static Medecin med = new Medecin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profil);

        tvAdresse=(TextView)findViewById(R.id.tv_adrsse);
        tvName=(TextView)findViewById(R.id.tv_name);
        tvEmail =(TextView)findViewById(R.id.tv_email);
        imgprofil = (ImageView) findViewById(R.id.imgprofildoct);
        GetDoctors();
    }
    public void GetDoctors (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
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
                        tvName.setText(med.getNameMedecin());
                        tvEmail.setText(med.getEmailMedecin());
                        tvAdresse.setText(med.getAdresseMaedecin());


                    Picasso.with(getApplicationContext()).load(PregnancyTrackerURLS.URL_IMAGES_PATIENTES+med.getImage()).fit().centerCrop()
                            .placeholder(R.drawable.doctorname)
                            .error(R.drawable.doctorname)
                            .into(imgprofil);


                    VolleyLog.d(REQUEST_TAG, "med: " + med);

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

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }



}
