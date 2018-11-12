package com.esprit.pregnancytracker.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;

import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Adapters.HistoriqueRendezVousForPatienteAdapter;
import com.esprit.pregnancytracker.Adapters.PatienteAdpater;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.main.ShowProfile;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.UrlAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link HistoriqueRendezVousPatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoriqueRendezVousPatFragment extends Fragment {

    ListView lv_historiqueRendezVous;
    final  List<RendezVous> rendezVousList = new ArrayList<>();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String CONNECTEDUSER;
    private String mParam2;



    public HistoriqueRendezVousPatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoriqueRendezVousPatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoriqueRendezVousPatFragment newInstance(String param1, String param2) {
        HistoriqueRendezVousPatFragment fragment = new HistoriqueRendezVousPatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment*
        Log.i("oooooo",CONNECTEDUSER);
        getRendezvousByPatiente();
        final View view = inflater.inflate(R.layout.fragment_historique_rendez_vous_pat, container, false);
        lv_historiqueRendezVous = (ListView) view.findViewById(R.id.historique_rendezvous);
       /* lv_historiqueRendezVous.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getContext(),"patiente  "+rendezVousList.get(i)
                        ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (getActivity() , ShowDetailsRendezVous.class);
                startActivity(intent);
                //  ((FragmentActivity)getActivity()).replaceFrag(new ShowDetailRendezVousForPatienteFragment());
            }
        });*/




                    System.out.println("listpbyd "+rendezVousList.size());
        return view;
    }
    public void  getRendezvousByPatiente(){
        Log.i("oooooo",CONNECTEDUSER);
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
         String url = PregnancyTrackerURLS.URL_SERVICE_SHOWRENDEZVOUS_IF_EXISTS +"idPatiente="+CONNECTEDUSER+"&etat=1";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                Log.i(REQUEST_TAG, response.toString());
                try {
                    JSONArray rdvArray = new JSONArray(response);
                    for (int i =0; i< rdvArray.length(); i++){
                        RendezVous rdvcont = new RendezVous();
                        JSONObject rdv = rdvArray.getJSONObject(i);
                        rdvcont.setIdPatiente(rdv.getInt("idPatiente"));
                        rdvcont.setDateRendezVous(rdv.getString("dateRendezVous"));
                        rdvcont.setIdMedecin(rdv.getInt("idMedecin"));
                       rdvcont.setDoctorName( getMedecinforthisPatiente(String.valueOf(rdvcont.getIdMedecin())));

                        rendezVousList.add(rdvcont);



                    }
                    HistoriqueRendezVousForPatienteAdapter pa = new HistoriqueRendezVousForPatienteAdapter(getContext(),
                            android.R.layout.activity_list_item,rendezVousList);


                    lv_historiqueRendezVous.setAdapter(pa);



                } catch (JSONException e) {
                    e.printStackTrace();
                   //hne n'afichi elli ma femma chay
                    Toast.makeText(getContext(),"you have not any appointment here ", Toast.LENGTH_LONG).show();

                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");







            }


        },new ErrorListener() {

            @Override
            public void onErrorResponse(com.android.volley.error.VolleyError error) {

            }


        });
        // Adding String request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }
    public String  getMedecinforthisPatiente (String idmedecin){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        final Medecin med = new Medecin();

        String url =PregnancyTrackerURLS.URLSERVICERendezVousMedecin +"idMedecin="+idmedecin;
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");




            }


        },new ErrorListener() {

            @Override
            public void onErrorResponse(com.android.volley.error.VolleyError error) {

            }


        });
        // Adding String request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
    return med.getNameMedecin();}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
