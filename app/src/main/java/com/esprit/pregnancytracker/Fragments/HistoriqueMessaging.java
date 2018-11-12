package com.esprit.pregnancytracker.Fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
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

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.esprit.pregnancytracker.Adapters.HistoriqueNotesAdapter;
import com.esprit.pregnancytracker.Adapters.MessagesHistoriqueAdapter;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.Message;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.main.MessagingContainerActivity;
import com.esprit.pregnancytracker.main.NotesContainerActivity;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.esprit.pregnancytracker.R.id.txt_cnxfailed;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoriqueMessaging#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoriqueMessaging extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView lv_msg;
    TextView txt_alt ;
    public  Effectstype effect;
   public static Message msg ;
    public HistoriqueMessaging() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoriqueMessaging.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoriqueMessaging newInstance(String param1, String param2) {
        HistoriqueMessaging fragment = new HistoriqueMessaging();

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
        msg = new Message();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historique_messaging, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_msg = (ListView) view.findViewById(R.id.lv_msg_hist);
        lv_msg.deferNotifyDataSetChanged();
        txt_alt = view.findViewById(R.id.alt_txt);
        txt_alt.setVisibility(View.INVISIBLE);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_msg);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
                ((MessagingContainerActivity)getActivity()).replaceFrag(MessagingPatienteFragment.newInstance(String.valueOf(
                        PatienteSingleton.getInstance().getIdpatiente()),""));
            }
        });
        getMsgessByPatiente();
    }


    public void  getMsgessByPatiente(){
        final List<Message> listMsgs =new ArrayList<>();

        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        //http://192.168.1.105/PregnancyTrackerServices/CRUDNote/getNotesByPatiente.php?idPatiente=1
        String url = PregnancyTrackerURLS.URLSERVICE_Get_MESSAGE_BY_PATIENTE +"idPatiente="+ PatienteSingleton.getInstance().getIdpatiente();

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {


                Log.i(REQUEST_TAG, response.toString());
                try {

                    JSONArray msgArray = new JSONArray(response);
                    for (int i =0; i< msgArray.length(); i++){

                        JSONObject msgfromDB = msgArray.getJSONObject(i);
                       msg.setIdMessage(msgfromDB.getInt("idMessage"));
                        msg.setSubject(msgfromDB.getString("subject"));
                        msg.setBody(msgfromDB.getString("body"));
                        msg.setIdDestinataire(msgfromDB.getInt("idMedecin"));
                        msg.setIdExpediteur(msgfromDB.getInt("idPatiente"));
                        msg.setDateEnvoi(msgfromDB.getString("dateEnvoi"));
                        getDoctorByMsg(msg.getIdDestinataire());
//                        Log.i ("namedoc",msg.getNameExpd());
                        listMsgs.add(msg);
                    }
                    MessagesHistoriqueAdapter pa = new MessagesHistoriqueAdapter(getContext(),
                            listMsgs);
                    lv_msg.setAdapter(pa);
                    lv_msg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getContext(),"press more to to delete this message"+listMsgs.get(i).getIdMessage()
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    });
                    lv_msg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            effect = Effectstype.Shake;
                            dialogShowDeletingItem(listMsgs.get(i).getIdMessage());
                            deleteMsg(listMsgs.get(i).getIdMessage());
                            return false;
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                    txt_alt.setVisibility(View.VISIBLE);
                    txt_alt.setText("No Message");
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
                        deleteAllMsg();
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
    public void  deleteAllMsg(){

        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        final String  url = PregnancyTrackerURLS.URLSERVICE_DELETE_ALL_MESSAGES;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                Log.i("responseNoteDeleting" ,response );
                getMsgessByPatiente();
                lv_msg.setVisibility(View.INVISIBLE);
                txt_alt.setVisibility(View.VISIBLE);
                txt_alt.setText("No Message");
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

    public void dialogShowDeletingItem(int   idMsg){
        final int  idmsg= idMsg;
      final   NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(getContext());


        dialogBuilder
                .withTitle("Delete Message")                                  //.withTitle(null)  no title
                .withTitleColor("#ffffff")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("Are you sure to delete this message")                     //.withMessage(null)  no Msg
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

                        deleteMsg(idmsg);
                        Toast.makeText(v.getContext(), "Message deleted", Toast.LENGTH_SHORT).show();
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

    public void  getDoctorByMsg(int idDoctor){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String url = PregnancyTrackerURLS.URLSERVICERendezVousMedecin +"idMedecin="+idDoctor;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                Log.i(REQUEST_TAG, response.toString());
                try {
                    JSONArray msgArray = new JSONArray(response);
                    for (int i =0; i< msgArray.length(); i++){
                        Medecin med = new Medecin();
                        JSONObject MedfromDB = msgArray.getJSONObject(i);
                        med.setNameMedecin(MedfromDB.getString("nameMedecin"));
                        med.setImage(MedfromDB.getString("image"));
                        msg.setImage(med.getImage());
                        msg.setNameExpd(med.getNameMedecin());
                        Log.i ("namedoc",msg.getNameExpd());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
            }
        });
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }




    public void  deleteMsg(int idMsg){

        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        final String  url = PregnancyTrackerURLS.URLSERVICE_DELETE_MESSAGE +"idMessage="+idMsg;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                lv_msg.deferNotifyDataSetChanged();

                Log.i("responseMsgDeleting" ,response );

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

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
