package com.esprit.pregnancytracker.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Response;

import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.UrlAddress;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by a on 18/12/2017.
 */

public class HistoriqueRendezVousForDoctorAdapter extends ArrayAdapter<RendezVous> {
    public static  String URLSERVICE = "http://"+ UrlAddress.MyIPAddress+"/PregnancyTrackerServices/getPatiente.php";
    Context ctx ;
    public  static  class ViewHolder{
        TextView txtdate;
        TextView txtnamePatiente;

    }
    public HistoriqueRendezVousForDoctorAdapter(@NonNull Context context, @LayoutRes int resource , List<RendezVous> rd) {
        super(context, resource , rd);
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        RendezVous rdv =getItem(position);
        HistoriqueRendezVousForDoctorAdapter.ViewHolder vHolder;
        if(convertView ==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_rendez_vous,parent,false);
            vHolder= new HistoriqueRendezVousForDoctorAdapter.ViewHolder();
            vHolder.txtdate= (TextView)convertView.findViewById(R.id.date_rendez_vous);
            vHolder.txtnamePatiente=(TextView)convertView.findViewById(R.id.name_of_doc_or_pat);
            convertView.setTag(vHolder);


        }else {
            vHolder=(HistoriqueRendezVousForDoctorAdapter.ViewHolder) convertView.getTag();


        }


        vHolder.txtdate.setText(rdv.getDateRendezVous());


        vHolder.txtnamePatiente.setText( rdv.getPatienteName());
        return convertView;
    }


    public String  gePatiente(int idrdv ){
        final Patiente p = new Patiente();
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String  url=URLSERVICE+"?idPatiente="+String.valueOf(idrdv);
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(REQUEST_TAG, response.toString());
                System.out.println(response);
                try {

                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth"+array.length());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject d = array.getJSONObject(i);
                       p.setNamePatiente(d.getString("namePatiente"));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error);
                System.out.print("***********Noooooooooooo********");

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(ctx).addToRequestQueue(strReq, REQUEST_TAG);
        return p.getNamePatiente() ;
    }



}
