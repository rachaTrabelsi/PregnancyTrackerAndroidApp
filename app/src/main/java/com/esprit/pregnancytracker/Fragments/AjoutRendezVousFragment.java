package com.esprit.pregnancytracker.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.Models.TokensFcm;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.app.Config;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.main.ShowProfile;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.PushingNtification;
import com.esprit.pregnancytracker.utils.UrlAddress;
import com.google.firebase.messaging.FirebaseMessaging;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//* {@link AjoutRendezVousFragment.OnFragmentInteractionListener} interface
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link AjoutRendezVousFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjoutRendezVousFragment extends Fragment {
  public   TextView txPatienteName, txNameDoctor;
    EditText getdate, gethour;
    int day, month, year , hour , minute;
    TimePickerDialog.OnTimeSetListener listener;
    boolean is24hourView;
    Button  submit;

    private static final String TAG = AjoutRendezVousFragment.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String CONNECTEDUSER = String.valueOf( PatienteSingleton.getInstance().getIdpatiente());
    private String DoctorForConnectedUSer;
    private String MedecinID;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), CONNECTEDUSER + "userconnecté", Toast.LENGTH_LONG);
        txNameDoctor = view.findViewById(R.id .tv_name_of_doctor);
        txPatienteName = view .findViewById(R.id.tv_name_of_patiente);
        getdate = view.findViewById(R.id.tf_pickdate_rendez_vous);
        gethour = view.findViewById(R.id.tf_pickhour_rendez_vous);

       // submit =view.findViewById(R.id.btn_submit_ajout_rendezVous);
       // txtRegId =view.findViewById(R.id.txt_reg_id);
      ///  txtMessage = view.findViewById(R.id.txt_push_message);



    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i ("connecteduser", CONNECTEDUSER);
      //  Log.i("medecin",DoctorForConnectedUSer);
        Log.i("patientename",String.valueOf(PatienteSingleton.getInstance().getIdMedecin()));
        txNameDoctor.setText(DoctorForConnectedUSer);
        txPatienteName.setText(PatienteSingleton.getInstance().getNamepatiente());
        getdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                day = cal.get(Calendar.DAY_OF_MONTH);
                month= cal.get(Calendar.MONTH);
                year = cal.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener(){



                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                      //  2018-02-23
                        getdate.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                       // getdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

                    }
                }, year,month,day);
                datePickerDialog.setTitle("Pick a date");
                datePickerDialog.show();
            }
        });

        gethour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                hour = cal.get(Calendar.HOUR);
                minute = cal.get(Calendar.MINUTE);

                TimePickerDialog timepickerdialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour , int minute) {
                                gethour.setText(hour+":"+minute);
                            }
                        }, hour, minute, true);

                    timepickerdialog.show();


            }
        });




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
                        Log.i("TOKEN",token );
                        listfcm.add(token);

                    }
                    Log.i("listFCM",String.valueOf( listfcm.size()));

                    SharedPreferences pref = getContext().getSharedPreferences(Config.SHARED_PREF, 0);
                    final String regId = pref.getString("regId", null);

                    Log.e("TAG", "Firebase reg id: " + regId);

                    if (!TextUtils.isEmpty(regId)) {
                        Toast.makeText(getContext().getApplicationContext(), "Firebase Reg Id: " + regId, Toast.LENGTH_LONG).show();
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


    public void AjoutRendezVousfrompatiente() {

                Log.i("entires",  getdate.getText().toString().trim()+" "+gethour.getText().toString().trim());
        Log.i("entirespat",  CONNECTEDUSER);
        Log.i("entirespat", String.valueOf( PatienteSingleton.getInstance().getIdMedecin()));
        String url = PregnancyTrackerURLS.URLSERVICE+"dateRendezVous="+
                getdate.getText().toString().trim()+" "+gethour.getText().toString().trim()+
                "&idPatiente="+ CONNECTEDUSER+
                "&idMedecin="+PatienteSingleton.getInstance().getIdMedecin();



        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";


        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("responsed'ajout", response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");
                showCnxFailedDialog();

            }
        });
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);}


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
            AppSingleton.getInstance(getContext()).addToRequestQueue(tokenrequest, REQUEST_TAG);


            Toast.makeText(getContext(), "Firebase Reg Id is not received yet!", Toast.LENGTH_LONG).show();


        }


        public void insertToken ( String  regID   ) {

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
            AppSingleton.getInstance(getContext()).addToRequestQueue(tokenrequest, REQUEST_TAG);


            Toast.makeText(getContext(), "Firebase Reg Id is not received yet!", Toast.LENGTH_LONG).show();


        }




    public AjoutRendezVousFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mRegistrationBroadcastReceiver);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @param param3 Parameter 3.
     * @return A new instance of fragment AjoutRendezVousFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AjoutRendezVousFragment newInstance(String param1, String param2 ,String param3) {
        AjoutRendezVousFragment fragment = new AjoutRendezVousFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_rendezvous_ajout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_rendezVous_sending:
                // do s.th.
                Log.i("idexpediteur",CONNECTEDUSER);
                AjoutRendezVousfrompatiente();
                mRegistrationBroadcastReceiver = new BroadcastReceiver() {


                    @Override
                    public void onReceive(Context context, Intent intent) {

                        // checking for type intent filter
                        if (intent.equals(Config.REGISTRATION_COMPLETE)) {
                            // gcm successfully registered
                            // now subscribe to `global` topic to receive app wide notifications
                            FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                            getTokensfcm();

                        } else if (intent.equals(Config.PUSH_NOTIFICATION)) {
                            // new push notification is received

                            String message = intent.getAction();//oumour bundle

                            Toast.makeText(getContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                            txtMessage.setText(message);
                        }
                    }
                };
                getTokensfcm();
                if(getdate.getText().toString().trim().equals("") || gethour.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext() , "You should enter the date and hour of your appointement please ", Toast.LENGTH_LONG).show();
                }else {
                    AjoutRendezVousfrompatiente();
                    getTokensfcm();
                }


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CONNECTEDUSER = getArguments().getString(ARG_PARAM1);
            DoctorForConnectedUSer = getArguments().getString(ARG_PARAM2);
            MedecinID = getArguments().getString(ARG_PARAM3);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajout_rendez_vous, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void showCnxFailedDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.connexionfaileddialog, null);
        dialogBuilder.setView(dialogView);

        final TextView edt =  dialogView.findViewById(R.id.txt_cnxfailed);

        ImageView image =  dialogView.findViewById(R.id.imgdialog_cnx_failed);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


}

       /* submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegistrationBroadcastReceiver = new BroadcastReceiver() {


                    @Override
                    public void onReceive(Context context, Intent intent) {

                        // checking for type intent filter
                        if (intent.equals(Config.REGISTRATION_COMPLETE)) {
                            // gcm successfully registered
                            // now subscribe to `global` topic to receive app wide notifications
                            FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                            getTokensfcm();

                        } else if (intent.equals(Config.PUSH_NOTIFICATION)) {
                            // new push notification is received

                            String message = intent.getAction();//oumour bundle

                            Toast.makeText(getContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                            txtMessage.setText(message);
                        }
                    }
                };
                getTokensfcm();
                if(getdate.getText().toString().trim().equals("") || gethour.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext() , "You should enter the date and hour of your appointement please ", Toast.LENGTH_LONG).show();
                }else {
                    AjoutRendezVousfrompatiente();
                   getTokensfcm();
                }




            }
        });*/
