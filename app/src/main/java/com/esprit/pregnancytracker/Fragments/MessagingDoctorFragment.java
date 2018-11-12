package com.esprit.pregnancytracker.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.app.Config;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.PushingNtification;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagingDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagingDoctorFragment extends Fragment {
    int destinaoreId;
    EditText from  ,  subject , Message;
    Spinner listTO;
    ArrayAdapter<String> dataAdapter;
    private String item;
    List<Medecin> meds = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MessagingDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagingDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagingDoctorFragment newInstance(String param1, String param2) {
        MessagingDoctorFragment fragment = new MessagingDoctorFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
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
                SendAMessage();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messaging_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listTO = (Spinner) view.findViewById(R.id.spin_pick_Doctot_send_To);
        List<String> listEmails = GetPatients();
        System.out.print(listEmails.size());

        from = view.findViewById(R.id.edt_from);
        subject = view.findViewById(R.id.edt_subject);
        Message = view.findViewById(R.id.body);
        GetDoctorInfo();

    }

    public void SendAMessage(){
        //URLSERVICEINSERTMESSAGE
        //idPatiente=1&idMedecin=2&subject=%22oooooo%22&body=%22jfnkfkfjk%22
        Log.i("iddestinataire",String.valueOf(destinaoreId));


        String url= PregnancyTrackerURLS.URLSERVICE_INSERT_MESSAGE +"idPatiente="+destinaoreId+
                "&idMedecin="+1+ "&subject="+subject.getText().toString()+"&body="+Message.getText().toString();

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






    public List<String> GetPatients (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequestdoctorrrr";

        final List<String> patientsforSpinner = new ArrayList<>();
        final List<Integer> IdPatfromSpinner = new ArrayList<>();

        String  url = PregnancyTrackerURLS.URLSERVICE_GET_PATIENTE_BY_Doctor+"medecindirect=1";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(REQUEST_TAG, response.toString());

                System.out.println(response);
                try {

                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth"+array.length());
                    for (int i = 0; i < array.length(); i++) {
                        Patiente pat = new Patiente();
                        JSONObject d = array.getJSONObject(i);
                        System.out.println("for spinner"+d.getString("namePatiente"));
                        String name=d.getString("email");
                        pat.setIdPatiente(d.getInt("idPatiente"));

                        patientsforSpinner.add(name);
                        IdPatfromSpinner.add(pat.getIdPatiente());
                        System.out.println("list for spinner "+patientsforSpinner.size());

                    }

                    System.out.println("list for spinner ba3d elfor  "+patientsforSpinner.size());
                    dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, patientsforSpinner);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    listTO.setAdapter(dataAdapter);
                    listTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            item = adapterView.getItemAtPosition(i).toString();
                            destinaoreId= IdPatfromSpinner.get(i);
                            Log.i("doctorIDpicked:", String.valueOf(destinaoreId));
                            System.out.println(item);
                            // Showing selected spinner item
                            Log.i("doctorpicked:", item);

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
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
        return  patientsforSpinner;
    }



    public void GetDoctorInfo(){
        String url= PregnancyTrackerURLS.URL_GET_DOCTOR_PATIENTE+"idMedecin=1";
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray  parray = new JSONArray(response);
                    JSONObject p = parray.getJSONObject(0);
                    from.setText(p.getString("emailMedecin"));


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

}
