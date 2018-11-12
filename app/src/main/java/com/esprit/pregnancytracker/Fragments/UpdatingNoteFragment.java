package com.esprit.pregnancytracker.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Adapters.HistoriqueRendezVousForPatienteAdapter;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.esprit.pregnancytracker.R.id.txt_cnxfailed;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdatingNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatingNoteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String IDNOTE;
    private String mParam2;
    EditText bodyupdated , subjectupdating;
            TextView datenote;

                public UpdatingNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatingNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatingNoteFragment newInstance(String param1, String param2) {
        UpdatingNoteFragment fragment = new UpdatingNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bodyupdated = view.findViewById(R.id.edit_body_note_updating);
        subjectupdating = view.findViewById(R.id.edit_subject_note_updating);
        datenote = view.findViewById(R.id.date_of_note);
        getNoteByID();
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
                Log.i("idnoteUpdated",IDNOTE);

                UpdateNote();



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            IDNOTE = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_updating_note, container, false);
    }


    public void  getNoteByID(){
        Log.i("oooooo",IDNOTE);
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String url = PregnancyTrackerURLS.URLSERVICE_Get_NOTE_BY_ID +"idNote="+IDNOTE;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                Log.i(REQUEST_TAG, response.toString());
                try {
                    JSONArray rdvArray = new JSONArray(response);

                        Dairy notetoDisplay = new Dairy();
                        JSONObject note = rdvArray.getJSONObject(0);
                    notetoDisplay.setIdPatiente(note.getInt("idPatiente"));
                    notetoDisplay.setDateNote(note.getString("dateNote"));
                    notetoDisplay.setBody(note.getString("bodyNote"));
                    notetoDisplay.setSubject(note.getString("subject"));

                    bodyupdated.setText(notetoDisplay.getBody());
                    subjectupdating.setText(notetoDisplay.getSubject());
                    datenote.setText(notetoDisplay.getDateNote());





                } catch (JSONException e) {
                    e.printStackTrace();
                    //hne n'afichi elli ma femma chay

                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");
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



    public void UpdateNote(){
        //http://192.168.1.105/PregnancyTrackerServices/CRUDNote/InsertNote.php?idPatiente=1&
        // body=rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr&subject=oooooooooooooooooooo

        String url= PregnancyTrackerURLS.URLSERVICE_UPDATING_NOTE+"idNote="+IDNOTE+"&body=+"
                +bodyupdated.getText().toString()+"&subject="+subjectupdating.getText().toString();
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("responseUdatingNote" ,response );


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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.connexionfaileddialog, null);
        dialogBuilder.setView(dialogView);

        final TextView edt =  dialogView.findViewById(txt_cnxfailed);

        ImageView image =  dialogView.findViewById(R.id.imgdialog_cnx_failed);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}
