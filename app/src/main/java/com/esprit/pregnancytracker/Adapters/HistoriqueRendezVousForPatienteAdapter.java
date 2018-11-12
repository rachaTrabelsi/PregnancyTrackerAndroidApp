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
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.UrlAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by a on 18/12/2017.
 */

public class HistoriqueRendezVousForPatienteAdapter extends ArrayAdapter<RendezVous>{
    public String URLSERVICEMEdecin= "http://"+ UrlAddress.MyIPAddress+"/PregnancyTrackerServices/getMedecin.php?idMedecin=";

    Context ctx ;

    public HistoriqueRendezVousForPatienteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RendezVous> objects) {
        super(context, resource, objects);
        this.ctx = context;
    }

    public  static  class ViewHolder{
        TextView txtdate;
        TextView txtnameDoctor;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        RendezVous rdv =getItem(position);
        HistoriqueRendezVousForPatienteAdapter.ViewHolder vHolder;
        if(convertView ==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.row_rendez_vous,parent,false);
            vHolder= new HistoriqueRendezVousForPatienteAdapter.ViewHolder();
            vHolder.txtdate= (TextView)convertView.findViewById(R.id.date_rendez_vous);
            vHolder.txtnameDoctor=(TextView)convertView.findViewById(R.id.name_of_doc_or_pat);
            convertView.setTag(vHolder);


        }else {
            vHolder=(HistoriqueRendezVousForPatienteAdapter.ViewHolder) convertView.getTag();


        }


        vHolder.txtdate.setText(rdv.getDateRendezVous());
        GetDoctors(rdv.getIdMedecin());

        vHolder.txtnameDoctor.setText( rdv.getDoctorName());
        return convertView;
    }


    public String GetDoctors (int rdvid ){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        // final List<Medecin> medecinsforSpinner = new ArrayList<>();

        final Medecin med = new Medecin();
        String  url = URLSERVICEMEdecin+ String.valueOf(rdvid);
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

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

                        med.setIdMadecin(d.getInt("idMedecin"));
                        med.setNameMedecin(d.getString("nameMedecin"));
                        med.setAdresseMaedecin(d.getString("adresseMedecin"));
                        med.setEmailMedecin(d.getString("emailMedecin"));
                        med.setImage(d.getString("image"));



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
        return med.getNameMedecin();

    }


}
