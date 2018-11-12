package com.esprit.pregnancytracker.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.app.Config;
import com.esprit.pregnancytracker.main.NotesContainerActivity;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.esprit.pregnancytracker.R.id.txt_cnxfailed;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjoutNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjoutNote extends Fragment {


    TextView Currentdate;
    EditText edt_subjectnote , edt_body;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String CONNECTEDUSER;
    private String mParam2;


    public AjoutNote() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AjoutNote.
     */
    // TODO: Rename and change types and number of parameters
    public static AjoutNote newInstance(String param1, String param2) {
        AjoutNote fragment = new AjoutNote();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajout_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        edt_body = view.findViewById(R.id.edit_body_note);
        edt_subjectnote = view.findViewById(R.id.edit_subject_note);
        Currentdate = view.findViewById(R.id.date_of_new_note);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       // Calendar cal = Calendar.getInstance();
        Date cal = new Date ();
        System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43
        Currentdate.setText(dateFormat.format(cal));
        //date_of_new_note
//        Log.i("edtvaluebody",edt_body.getText().toString());

    }
    @Override
                public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
                super.onCreateOptionsMenu(menu, inflater);
                inflater.inflate(R.menu.menu_note, menu);
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_note_sending:
                        // do s.th.
                        Log.i("idexpediteur",CONNECTEDUSER);
                        Log.i("edtvaluebody",edt_subjectnote.getText().toString());

                        AjoutNote();


                        return true;
       /*     case R.id.actionToListNote:

                ((NotesContainerActivity)getActivity()).replaceFrag(HistoriqueNotesfragment.newInstance(CONNECTEDUSER,""));


                return true;*/
                    default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void AjoutNote(){
        //http://192.168.1.105/PregnancyTrackerServices/CRUDNote/InsertNote.php?idPatiente=1&
        // body=rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr&subject=oooooooooooooooooooo

        String url= PregnancyTrackerURLS.URLSERVICE_INSERTING_NOTE+"idPatiente="+CONNECTEDUSER+"&body=+"
               +edt_body.getText().toString()+"&subject="+edt_subjectnote.getText().toString();
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("responseAjoutNote" ,response );


            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");
                showCnxFailedDialog();

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }


    public void showCnxFailedDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.connexionfaileddialog, null);
        dialogBuilder.setView(dialogView);

        final TextView edt =  dialogView.findViewById(txt_cnxfailed);

        ImageView image =  dialogView.findViewById(R.id.imgdialog_cnx_failed);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}
