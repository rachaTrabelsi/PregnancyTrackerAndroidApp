package com.esprit.pregnancytracker.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.app.Config;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.PushingNtification;
import com.esprit.pregnancytracker.utils.SingletonMedecin;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// * {@link MessagingPatienteFragment.OnFragmentInteractionListener} interface
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link MessagingPatienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagingPatienteFragment extends Fragment {
        int destinaoreId;
    EditText from  ,  subject , Message;
    Spinner    listTO;
    ArrayAdapter<String> dataAdapter;
    private String item;
    List<Medecin> meds = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String CONNECTED_USER;
    private String mParam2;


    private static final String TAG = AjoutRendezVousFragment.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;  PushingNtification pushNotif= null;
 //   private OnFragmentInteractionListener mListener;

    public MessagingPatienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagingPatienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagingPatienteFragment newInstance(String param1, String param2) {
        MessagingPatienteFragment fragment = new MessagingPatienteFragment();
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
            CONNECTED_USER = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        listTO = (Spinner) view.findViewById(R.id.spin_pick_Doctot_send_To);
        List<String> listEmails = GetDoctors();
        System.out.print(listEmails.size());

            from = view.findViewById(R.id.edt_from);
            subject = view.findViewById(R.id.edt_subject);
            Message = view.findViewById(R.id.body);
        GetPatienteInfo();
       // Log.i("idexpediteur",CONNECTED_USER);
        from.setText(PatienteSingleton.getInstance().getEmail());
        pushNotif = new PushingNtification(getContext(), CONNECTED_USER);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messaging_patiente, container, false);

        return view;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menuformessaing, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sending:
                // do s.th.
                Log.i("idexpediteur",CONNECTED_USER);

                mRegistrationBroadcastReceiver = new BroadcastReceiver() {


                    @Override
                    public void onReceive(Context context, Intent intent) {

                        // checking for type intent filter
                        if (intent.equals(Config.REGISTRATION_COMPLETE)) {
                            // gcm successfully registered
                            // now subscribe to `global` topic to receive app wide notifications
                            FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                            pushNotif.getTokensfcm();

                        } else if (intent.equals(Config.PUSH_NOTIFICATION)) {
                            // new push notification is received

                            String message = intent.getAction();//oumour bundle


                        }
                    }
                };
                SendAMessage();
                pushNotif.getTokensfcm();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void SendAMessage(){
        //URLSERVICEINSERTMESSAGE
        //idPatiente=1&idMedecin=2&subject=%22oooooo%22&body=%22jfnkfkfjk%22
            Log.i("iddestinataire",String.valueOf(destinaoreId));
        Log.i("idexpediteur",CONNECTED_USER);

        String url= PregnancyTrackerURLS.URLSERVICE_INSERT_MESSAGE +"idPatiente="+CONNECTED_USER+
                "&idMedecin="+destinaoreId+ "&subject="+subject.getText().toString()+"&body="+Message.getText().toString();

        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("responsechnya" ,response );
            }

        },
                new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");
                Log.i("responsechnya" ,"errorrr de cnx aslan" );
            }

        });
        // Adding String request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }



    public void GetPatienteInfo(){
     String url= PregnancyTrackerURLS.URLSERVICE_GET_PATIENTE+"idPatiente="+CONNECTED_USER;
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray  parray = new JSONArray(response);
                    JSONObject p = parray.getJSONObject(0);
                    from.setText(p.getString("email"));


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
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }
    public List<String> GetDoctors (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequestdoctorrrr";

        final List<String> medecinsforSpinner = new ArrayList<>();
        final List<Integer> IdmedecinsfromSpinner = new ArrayList<>();

        String  url = PregnancyTrackerURLS.URLSERVICEMEdecin;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(REQUEST_TAG, response.toString());

                System.out.println(response);
                try {

                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth"+array.length());
                    for (int i = 0; i < array.length(); i++) {
                        Medecin med = new Medecin();
                        JSONObject d = array.getJSONObject(i);
                        System.out.println("for spinner"+d.getString("nameMedecin"));
                        String name=d.getString("emailMedecin");
                        med.setIdMadecin(d.getInt("idMedecin"));

                        medecinsforSpinner.add(name);
                        IdmedecinsfromSpinner.add(med.getIdMadecin());
                        System.out.println("list for spinner "+medecinsforSpinner.size());

                    }

                    System.out.println("list for spinner ba3d elfor  "+medecinsforSpinner.size());
                    dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, medecinsforSpinner);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    listTO.setAdapter(dataAdapter);
                    listTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            item = adapterView.getItemAtPosition(i).toString();
                            destinaoreId= IdmedecinsfromSpinner.get(i);
                            Log.i("doctorIDpicked:", String.valueOf(destinaoreId));
                            System.out.println(item);
                            // Showing selected spinner item
                            Log.i("doctorpicked:", item);

                            Toast.makeText(getContext(), "Selected: "+String.valueOf(listTO.getSelectedItem()) + item, Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            Toast.makeText(getContext(), "Nothind Selected " + item, Toast.LENGTH_LONG).show();

                        }
                    });


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
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);

        return  medecinsforSpinner;
    }


}
