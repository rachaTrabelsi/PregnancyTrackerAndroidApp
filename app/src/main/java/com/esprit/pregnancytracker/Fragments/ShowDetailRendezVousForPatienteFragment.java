package com.esprit.pregnancytracker.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.main.ShowProfile;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.UrlAddress;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowDetailRendezVousForPatienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowDetailRendezVousForPatienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowDetailRendezVousForPatienteFragment extends Fragment  {
    TextView patientName , DoctorName , dayrest ;
    FloatingActionButton btnshowRendezVous , btnAddRendezVous , reminder  ;
    RendezVous rdvglob = new RendezVous();
    public Medecin med = new Medecin();
    public Patiente pat = new Patiente();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String CONNECTEDUSER;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShowDetailRendezVousForPatienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowDetailRendezVousForPatienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowDetailRendezVousForPatienteFragment newInstance(String param1, String param2) {
        ShowDetailRendezVousForPatienteFragment fragment = new ShowDetailRendezVousForPatienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CONNECTEDUSER = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patientName = view.findViewById(R.id.tv_pat_name_patiente);
        DoctorName = view .findViewById(R.id.tv_pat_name_doctor);
        dayrest = view.findViewById(R.id.period_last);
        btnAddRendezVous =   view.findViewById(R.id.btn_add_appointement);
        btnshowRendezVous =  view.findViewById(R.id.btn_show_RendezVous);

        reminder = view.findViewById(R.id.btn_reminder_RendezVous);

            rdvglob = new RendezVous();

    }

    @Override
    public void onStart() {
                super.onStart();
//                Log.i ("CONNECTEDUSERfilstart" , CONNECTEDUSER );
             //   Toast.makeText(getContext(), CONNECTEDUSER, Toast.LENGTH_LONG);

       // Log.i ("idmederendezvousart" ,  String.valueOf( PatienteSingleton.getInstance().getIdMedecin()));
                getRendezfordetails();




                btnshowRendezVous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      //  ((MainActivity)getActivity()).replaceFrag( ThirdBlankFragment.newInstance("aaaa","hhh"));
                        ((ShowDetailsRendezVous)getActivity()).replaceFrag(HistoriqueRendezVousPatFragment.newInstance(CONNECTEDUSER,""));
                    }
                });
                btnAddRendezVous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((ShowDetailsRendezVous)getActivity()).replaceFrag(AjoutRendezVousFragment.newInstance(CONNECTEDUSER,med.getNameMedecin(),String.valueOf( med.getIdMadecin())));
                    }
                });
                reminder.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View view) {
                        Log.d("","reminderrrrrr");
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                        notificationIntent.addCategory("android.intent.category.DEFAULT");
                        PendingIntent broadcast = PendingIntent.getBroadcast(getContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.SECOND, 15);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_detail_rendez_vous_for_patiente, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public void  getRendezfordetails (){
        Log.i("patrdvfrag", CONNECTEDUSER);
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
         String URLSERVICERendezVous= PregnancyTrackerURLS.URL_SERVICE_SHOWRENDEZVOUS_IF_EXISTS;
            String url = URLSERVICERendezVous +"idPatiente="+CONNECTEDUSER+"&etat=0";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                    Log.i(REQUEST_TAG, response.toString());
                    try {
                        JSONArray rdvArray = new JSONArray(response);

                        JSONObject rdv = rdvArray.getJSONObject(0);
                        rdvglob.setIdMedecin(rdv.getInt("idMedecin"));
                        rdvglob.setIdPatiente(rdv.getInt("idPatiente"));
                        rdvglob.setDateRendezVous(rdv.getString("dateRendezVous"));
                        Log.i("reeeendezvous", rdvglob.getDateRendezVous());
                        dayrest.setText(rdvglob.getDateRendezVous());

                       //// getMedecinforthisPatiente();
                        getPatiente();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        dayrest.setText("you didn't take an appointement");
                        patientName.setText(pat.getNamePatiente());
                       // getMedecinforthisPatiente();
                        getPatiente();
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
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }

    public void  getMedecinforthisPatiente (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String URLSERVICERendezVous= PregnancyTrackerURLS.URL_SERVICE_GET_Medecin;
        String url = URLSERVICERendezVous +"idMedecin="+pat.getMedecindirect();
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i(REQUEST_TAG, response.toString());
                try {
                    JSONArray medArray = new JSONArray(response);

                    JSONObject medv = medArray.getJSONObject(0);
                    med.setIdMadecin(medv.getInt("idMedecin"));
                   med.setEmailMedecin(medv.getString("emailMedecin"));
                    med.setNameMedecin(medv.getString("nameMedecin"));
                    DoctorName.setText(med.getNameMedecin());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");




            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                VolleyLog.d("erreurMedecin", "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }

    public void  getPatiente (){
        Log.i("patrdvfragname", CONNECTEDUSER);
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String URLSERVICERendezVous= PregnancyTrackerURLS.URLSERVICE_GET_PATIENTE;
        String url = URLSERVICERendezVous +"idPatiente="+CONNECTEDUSER;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i(REQUEST_TAG, response.toString());
                try {
                    JSONArray medArray = new JSONArray(response);

                    JSONObject patv = medArray.getJSONObject(0);
                    pat.setNamePatiente(patv.getString("namePatiente"));
                    pat.setMedecindirect(patv.getInt("medecindirect"));
                    patientName.setText(pat.getNamePatiente());
                    getMedecinforthisPatiente();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");




            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                VolleyLog.d("erreurMedecin", "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
