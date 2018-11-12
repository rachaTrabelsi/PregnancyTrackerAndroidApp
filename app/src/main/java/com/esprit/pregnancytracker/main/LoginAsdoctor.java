package com.esprit.pregnancytracker.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.SingletonMedecin;
import com.esprit.pregnancytracker.utils.ValuesClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginAsdoctor extends AppCompatActivity {

    CircularProgressButton btnlogin;
    EditText editusernamelogin, editpasswordlogin;

    TextView loginAsPatient;
    int id ;
    String mdpSaved,loginSaved,nameMedSaved,idMedSaved,addresseMedSaved,imageSaved,emailSaved;
    Patiente p = new Patiente();
    boolean logged = false;
    public final  static String INFO_USER= "login";
    public static final String url = PregnancyTrackerURLS.URL_SERVICE_LOGIN_MEDECIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_asdoctor);
        btnlogin = (CircularProgressButton) findViewById(R.id.btnloginDr);

        editpasswordlogin = (EditText) findViewById(R.id.editpasswordloginDr);
        editusernamelogin = (EditText) findViewById(R.id.editusernameloginDr);
        loginAsPatient  = (TextView) findViewById(R.id.log_as_patient);
        loginAsPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent( LoginAsdoctor.this , LoginActivity.class);
                startActivity(i);
            }
        });
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content("our application is in the testing phase, so for the safety of our patients we didn't give \n" +
                        "the user the opportunity to create an account as a doctor \n" +
                        "only the trusted gynecologists can contact us to have a doctor account so that they can follow their patient's state\n" +
                        "so to let you make your test to login as a doctor we created an account for you \n" +
                        "the login is \"testdoctor@test.com\" and the password is : 123\n" +
                        "thank you.")
                .positiveText(R.string.agree)
                .show();


    /*    SharedPreferences sharedPreferences = getSharedPreferences(INFO_USER, MODE_PRIVATE);
        loginSaved = sharedPreferences.getString("login", null);
        mdpSaved = sharedPreferences.getString("passwordMedecin", null);
        nameMedSaved = sharedPreferences.getString("nameMedecin", null);

        idMedSaved = sharedPreferences.getString("idMedecin", null);
        emailSaved = sharedPreferences.getString("emailMedecin", null);
        addresseMedSaved = sharedPreferences.getString("adresseMaedecin", null);
        imageSaved = sharedPreferences.getString("image", null);


        if (loginSaved != null && mdpSaved != null) {

            SingletonMedecin.getInstance().setIdMadecin(Integer.valueOf(idMedSaved));
            SingletonMedecin.getInstance().setNameMedecin((nameMedSaved));

            SingletonMedecin.getInstance().setImage((imageSaved));
            SingletonMedecin.getInstance().setAdresseMaedecin((addresseMedSaved));
            SingletonMedecin.getInstance().setEmailMedecin((emailSaved));
            Intent intent2 = new Intent(getBaseContext(), NewsForDoctorActivity.class);
            startActivity(intent2);


        }*/

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
                if (btnlogin.getProgress()==0){
                    btnlogin.setProgress(30);
                }
                else if (btnlogin.getProgress()==-1){
                    btnlogin.setProgress(0);
                }
                //  CheckEditTextIsEmptyOrNot();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (logged){

                            btnlogin.setProgress(0);
                            Intent intent = new Intent(LoginAsdoctor.this,MainDoctorActivity.class);
                            startActivity(intent);
                        }else {
                            btnlogin.setProgress(-1);
                        }
                    }
                },3000);
            }
        });
    }



    public void UserLogin() {
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
            Log.d("username",editusernamelogin.getText().toString().trim());
        String URLparam =url+"username="+ editusernamelogin.getText().toString().trim()
                +"&password="+editpasswordlogin.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(URLparam ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Log.e("response",ServerResponse);

                        try {
                           JSONObject dobj = new JSONObject(ServerResponse);
                            boolean error = dobj.getBoolean("error");
                            if(error) {

                                    Log.e("ressponseeroor","sorrry you try enter your 5ra ");

                                logged=false;
                            }        else {
                                try{
                                    //JSONArray array = new JSONArray(ServerResponse);
                                    // for (int i = 0; i < array.length(); i++) {

                                    JSONObject d = dobj.getJSONObject("user");
                                    Log.i("responselogin",String.valueOf(d.getInt("idMedecin")));
                                    SingletonMedecin.getInstance().setIdMadecin(d.getInt("idMedecin"));
                                    SingletonMedecin.getInstance().setNameMedecin(d.getString("nameMedecin"));
                                    SingletonMedecin.getInstance().setEmailMedecin(d.getString("emailMedecin"));
                                    SingletonMedecin.getInstance().setPasswordMedecin(d.getString("passwordMedecin"));
                                    SingletonMedecin.getInstance().setAdresseMaedecin(d.getString("adresseMedecin"));
                                    SingletonMedecin.getInstance().setImage(d.getString("image"));


                                  /*  SharedPreferences.Editor editor=getSharedPreferences(INFO_USER,MODE_PRIVATE).edit();
                                    editor.putString("login",editusernamelogin.getText().toString());
                                    editor.putString("mdp",editpasswordlogin.getText().toString());
                                    editor.putString("idMadecin",String.valueOf(d.getInt("idMedecin")));
                                    editor.putString("passwordMedecin",String.valueOf(d.getString("passwordMedecin")));
                                    editor.putString("nameMedecin",d.getString("nameMedecin"));
                                    editor.putString("adresseMedecin",d.getString("adresseMedecin"));
                                    editor.putString("emailMedecin",d.getString("emailMedecin"));
                                    editor.putString("image",d.getString("image"));
                                    editor.commit();*/
                                    logged = true;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }) {

        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);


    }








}
