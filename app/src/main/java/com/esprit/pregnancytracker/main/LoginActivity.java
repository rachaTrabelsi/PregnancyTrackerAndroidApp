package com.esprit.pregnancytracker.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.Medecin;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.SingletonMedecin;
import com.esprit.pregnancytracker.utils.UrlAddress;
import com.esprit.pregnancytracker.utils.ValuesClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText editusernamelogin, editpasswordlogin;
    CircularProgressButton btnLogin;
    TextView btnRegister , loginAsDoctor;
    int id  ;
    String mdpSaved,loginSaved,namepatienteSaved,
            nbsemaineSaved,idpatienteSaved,dateInscriptionSaved,birthdateSaved,
            imageSaved,emailSaved,usernameSaved, idmedecin ;
    Patiente p = new Patiente();
    boolean logged = false;
    public final  static String INFO_USER= "login";
    public static final String url = PregnancyTrackerURLS.URLSERVICE_LOGIN_PATIENTE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editusernamelogin = (EditText) findViewById(R.id.editusernamelogin);
        editpasswordlogin = (EditText) findViewById(R.id.editpasswordlogin);
        btnLogin = (CircularProgressButton) findViewById(R.id.btnlogin);
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        loginAsDoctor = (TextView) findViewById(R.id.log_as_doctor);
        final SharedPreferences sharedPreferences=getSharedPreferences(INFO_USER,MODE_PRIVATE);
        loginSaved=sharedPreferences.getString("login",null);
        mdpSaved=sharedPreferences.getString("mdp",null);
        namepatienteSaved = sharedPreferences.getString("namePatiente",null);
        nbsemaineSaved= sharedPreferences.getString("nbsemaine",null);
        idpatienteSaved= sharedPreferences.getString("idPatiente",null);
        dateInscriptionSaved  = sharedPreferences.getString("dateinscription",null);
        emailSaved = sharedPreferences.getString("email",null);
        birthdateSaved =sharedPreferences.getString("birthdate",null);
        imageSaved = sharedPreferences.getString("image",null);
        usernameSaved = sharedPreferences.getString("username",null);
        idmedecin = sharedPreferences.getString("medecindirect",null);
        if (loginSaved!=null && mdpSaved!= null){

          PatienteSingleton.getInstance().setNbsemaine(Integer.valueOf(nbsemaineSaved));
            PatienteSingleton.getInstance().setIdpatiente(Integer.valueOf(idpatienteSaved));
            PatienteSingleton.getInstance().setNamepatiente((namepatienteSaved));
            PatienteSingleton.getInstance().setUsername((usernameSaved));
            PatienteSingleton.getInstance().setImage((imageSaved));
            PatienteSingleton.getInstance().setBirthdate((birthdateSaved));
            PatienteSingleton.getInstance().setEmail((emailSaved));
            PatienteSingleton.getInstance().setIdMedecin((Integer.parseInt(idmedecin))) ;
            Intent intent2= new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent2);

            //  Toast.makeText(LoginActivity.this,PatienteSingleton.getInstance().getIdpatiente(),Toast.LENGTH_LONG).show();

        }
        loginAsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBaseContext() .getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().clear();
                getBaseContext() .getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().commit();
                loginSaved = null;
                mdpSaved = null;
                Intent i = new Intent(LoginActivity.this, LoginAsdoctor.class);
                startActivity(i);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        btnLogin.setIndeterminateProgressMode(true);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
                if (btnLogin.getProgress()==0){
                    btnLogin.setProgress(30);
                }
                else if (btnLogin.getProgress()==-1){
                    btnLogin.setProgress(0);
                }
                //  CheckEditTextIsEmptyOrNot();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (logged){
                            Intent registerIntent = new Intent(LoginActivity.this,HomeActivity.class);
                            LoginActivity.this.startActivity(registerIntent);
                             btnLogin.setProgress(0);
                        }else {
                            btnLogin.setProgress(-1);
                        }
                    }
                },3000);
            }
        });
    }
    public void UserLogin() {
        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // Matching server responce message to our text.
                         if(ServerResponse.trim().equals("no")) {
                            // If response matched then show the toast.

                            logged=false;
                        }

                        else {
                            try {
                                JSONArray array = new JSONArray(ServerResponse);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject d = array.getJSONObject(i);
                                    PatienteSingleton.getInstance().setIdpatiente(d.getInt("idPatiente"));
                                    PatienteSingleton.getInstance().setUsername(d.getString("username"));
                                    PatienteSingleton.getInstance().setNbsemaine(d.getInt("nbsemaine"));
                                    PatienteSingleton.getInstance().setNamepatiente(d.getString("namePatiente"));
                                    PatienteSingleton.getInstance().setDateinscription(d.getString("dateinscription"));
                                    PatienteSingleton.getInstance().setBirthdate(d.getString("birthdate"));
                                    PatienteSingleton.getInstance().setEmail(d.getString("email"));
                                    PatienteSingleton.getInstance().setImage(d.getString("image"));
                                    PatienteSingleton.getInstance().setIdMedecin(d.getInt("medecindirect"));
                                    getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().clear();
                                    getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().commit();
                                    SharedPreferences.Editor editor=getSharedPreferences(INFO_USER,MODE_PRIVATE).edit();

                                    editor.putString("login",editusernamelogin.getText().toString());
                                    editor.putString("mdp",editpasswordlogin.getText().toString());
                                    editor.putString("idPatiente",String.valueOf(d.getInt("idPatiente")));
                                    editor.putString("nbsemaine",String.valueOf(d.getInt("nbsemaine")));
                                    editor.putString("namePatiente",d.getString("namePatiente"));
                                    editor.putString("dateinscription",d.getString("dateinscription"));
                                    editor.putString("username",d.getString("username"));
                                    editor.putString("email",d.getString("email"));
                                    editor.putString("birthdate",d.getString("birthdate"));
                                    editor.putString("image",d.getString("image"));
                                    editor.putString("medecindirect",d.getString("medecindirect"));
                                    editor.commit();


                                    ValuesClass.idPatiente=d.getInt("idPatiente");
                                    ValuesClass.usernamepatiente=d.getString("username");
                                    ValuesClass.emailpatiente = d.getString("email");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                           // Finish the current Login activity.
                            logged = true;
                            // Showing Echo Response Message Coming From Server.
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                     //   Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("username", editusernamelogin.getText().toString().trim());
                params.put("password", editpasswordlogin.getText().toString().trim());
                return params;
            }
        };
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }
}
