package com.esprit.pregnancytracker.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Adapters.PatienteAdpater;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.FragmentActivity;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.UrlAddress;

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
 * Activities that contain this fragment must implement the
 * {@link PatientesByDoctor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PatientesByDoctor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientesByDoctor extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
        ListView lv_patientes;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PatientesByDoctor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientesByDoctor.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientesByDoctor newInstance(String param1, String param2) {
        PatientesByDoctor fragment = new PatientesByDoctor();
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                .title(R.string.titlerdv)
                .content(R.string.contentrdv)
                .positiveText(R.string.agreerdv)
                .show();

        lv_patientes = (ListView) view.findViewById(R.id.list_patientes_by_doctor);
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";


        String  url = PregnancyTrackerURLS.URLSERVICE_GET_PATIENTE_BY_Doctor+"medecindirect=1";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            final List<Patiente> ps = new ArrayList<>();
            @Override
            public void onResponse(String response) {

                Log.d(REQUEST_TAG, response.toString());

                System.out.println(response);
                try {

                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth"+array.length());
                    for (int i = 0; i < array.length(); i++) {
                        final Patiente p = new Patiente();
                        JSONObject d = array.getJSONObject(i);
//                        System.out.println("for spinner"+d.getString("nameMedecin"));
                        //   String name=d.getString("nameMedecin");
                        p.setIdPatiente(d.getInt("idPatiente"));
                        p.setNamePatiente(d.getString("namePatiente"));
                        p.setEmail(d.getString("email"));
                        p.setPassord(d.getString("password"));
                        p.setBirdhdate(convertStringtoDate(d.getString("birthdate")));

                        ps.add(p);
                        System.out.println("list fraaaaaaaaaaaaaaaaaaaagment"+ps.size());

                        PatienteAdpater pa = new PatienteAdpater(getContext(),
                                android.R.layout.activity_list_item,ps);

                        lv_patientes.setAdapter(pa);

                        lv_patientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent (getActivity() , ShowDetailsRendezVous.class);
                                intent.putExtra("Patid", String.valueOf(ps.get(i).getIdPatiente()));
                                startActivity(intent);
                                     }
                        });


                    }

                    System.out.println("listpbyd "+ps.size());


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext() , "you have no patient Here!", Toast.LENGTH_LONG).show();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_patientes_by_doctor, container, false);


        return view;
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public Date convertStringtoDate (String dateString ){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(convertedDate);
        return  convertedDate;
    }




}
