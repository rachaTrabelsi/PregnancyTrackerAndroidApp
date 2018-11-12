package com.esprit.pregnancytracker.Fragments;


import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;


import com.android.volley.Response;

import com.android.volley.VolleyLog;

import com.esprit.pregnancytracker.Adapters.HistoriqueNotesAdapter;
import com.esprit.pregnancytracker.Adapters.HistoriqueRendezVousForPatienteAdapter;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.RendezVous;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.NotesContainerActivity;
import com.esprit.pregnancytracker.main.ShowDetailsRendezVous;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.esprit.pregnancytracker.R.id.txt_cnxfailed;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoriqueNotesfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  HistoriqueNotesfragment extends Fragment {
    public  Effectstype effect;
    TextView txtnothing;
    ListView lv_notes;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String CONNECTEDUSER;
    private String mParam2;


    public HistoriqueNotesfragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoriqueNotesfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoriqueNotesfragment newInstance(String param1, String param2) {
        HistoriqueNotesfragment fragment = new HistoriqueNotesfragment();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_delete_all, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_all:
                // do s.th.
                effect = Effectstype.Shake;

                 dialogShow();

                 deleteAllNote();
                lv_notes.deferNotifyDataSetChanged();
                return true;
       /*     case R.id.actionToListNote:

                ((NotesContainerActivity)getActivity()).replaceFrag(HistoriqueNotesfragment.newInstance(CONNECTEDUSER,""));


                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    public void dialogShow(){
      final  NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(getContext());


        dialogBuilder
                .withTitle("Deleting All !!!")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("Wait a minute please !")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#069DA5")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(getResources().getDrawable(R.drawable.ic_surprised_deleting))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(effect)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .setCustomView(R.layout.custom_view_delet_all,getContext())         //.setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "deleting all", Toast.LENGTH_SHORT).show();
                        deleteAllNote();
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                        dialogBuilder.dismiss();
                    }
                })
                .show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historique_notesfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_notes = view.findViewById(R.id.lv_notes);
        lv_notes.deferNotifyDataSetChanged();
        txtnothing = view.findViewById(R.id.no_thing_to_display);
        txtnothing.setVisibility(View.INVISIBLE);
        //no_thing_to_display
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                ((NotesContainerActivity)getActivity()).replaceFrag(AjoutNote.newInstance(CONNECTEDUSER,""));
            }
        });
        getNotesByPatiente();
    }



    public void  getNotesByPatiente(){
        final List<Dairy> listNotes =new ArrayList<>();
        Log.i("oooooo",CONNECTEDUSER);
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        //http://192.168.1.105/PregnancyTrackerServices/CRUDNote/getNotesByPatiente.php?idPatiente=1
        String url = PregnancyTrackerURLS.URLSERVICE_GetNotes_BY_PATIENTE +"idPatiente="+CONNECTEDUSER;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {


                Log.i(REQUEST_TAG, response.toString());
                try {

                    JSONArray noteArray = new JSONArray(response);
                    for (int i =0; i< noteArray.length(); i++){
                        Dairy note = new Dairy();
                        JSONObject notefromDB = noteArray.getJSONObject(i);

                        note.setIdNote(notefromDB.getInt("idNote"));
                        note.setIdPatiente(notefromDB.getInt("idPatiente"));
                        note.setSubject(notefromDB.getString("subject"));
                        note.setBody(notefromDB.getString("bodyNote"));
                        note.setDateNote(notefromDB.getString("dateNote"));

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
                        Date convertedDate = new Date();
                        try {
                            convertedDate = dateFormat.parse(note.getDateNote());

                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();

                        }
                        System.out.println(convertedDate);

                        note.setDayOfWeek( new SimpleDateFormat("EE").format(convertedDate));
                         note.setDayMonth(new SimpleDateFormat("dd MMM").format(convertedDate));
                        note.setYear(new SimpleDateFormat("yyyy").format(convertedDate));

                        listNotes.add(note);



                    }
                    HistoriqueNotesAdapter pa = new HistoriqueNotesAdapter(getContext(),
                            android.R.layout.activity_list_item,listNotes);


                    lv_notes.setAdapter(pa);
                    lv_notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getContext(),"note  "+listNotes.get(1).getIdNote()
                                    ,Toast.LENGTH_SHORT).show();
                            //net3adalil fragment detail/update
                            ((NotesContainerActivity)getActivity()).replaceFrag(UpdatingNoteFragment.newInstance(
                                    String.valueOf(listNotes.get(i).getIdNote()),""));

                        }
                    });
                    lv_notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                            effect = Effectstype.Shake;

                            dialogShowDeletingItem(String.valueOf(listNotes.get(i).getIdNote()));
                              //  deleteNote(String.valueOf(listNotes.get(i).getIdNote()));
                            return false;
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                    //hne n'afichi elli ma femma chay
                    txtnothing.setVisibility(View.VISIBLE);
                    txtnothing.setText("make now your first dairy ! let's go");

                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");







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

    public void dialogShowDeletingItem(String  idNote){
        final String idnote= idNote;
       final  NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(getContext());


        dialogBuilder
                .withTitle("Delete dairy !")                                  //.withTitle(null)  no title
                .withTitleColor("#ffffff")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("Really you want delet all your dairies for your baby")                     //.withMessage(null)  no Msg
                .withMessageColor("#ffffff")                              //def  | withMessageColor(int resid)
                .withDialogColor("#21a4b3")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(getResources().getDrawable(R.drawable.ic_surprised_deleting))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(effect)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .setCustomView(R.layout.custom_view,getContext())         //.setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "deleteItem", Toast.LENGTH_SHORT).show();
                        deleteNote(idnote);
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "cancel", Toast.LENGTH_SHORT).show();
                        dialogBuilder.dismiss();
                    }
                })
                .show();

    }

    public void  deleteNote(String idNote){

        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        final String  url = PregnancyTrackerURLS.URLSERVICE_DELETING_NOTE +"idNote="+idNote;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                    lv_notes.deferNotifyDataSetChanged();

                Log.i("responseNoteDeleting" ,response );

            } }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                        System.out.print("***********Noooooooooooo********");
                        showCnxFailedDialog();

                    }
                });
                AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);
            }

    public void  deleteAllNote(){

        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        final String  url = PregnancyTrackerURLS.URLSERVICE_Delete_ALL_NOTES;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                Log.i("responseNoteDeleting" ,response );
                getNotesByPatiente();
                lv_notes.setVisibility(View.INVISIBLE);
                txtnothing.setVisibility(View.VISIBLE);
                txtnothing.setText("make now your first dairy ! let's go");
            } }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                System.out.print("***********Noooooooooooo********");
                showCnxFailedDialog();

            }
        });
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
