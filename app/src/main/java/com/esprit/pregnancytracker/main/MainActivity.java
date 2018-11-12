package com.esprit.pregnancytracker.main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
   EditText editusernamelogin, editpasswordlogin;
    CircularProgressButton btnLogin;
    TextView btnRegister;
    boolean logged = true;
    String usernameedt,passwordedt;
    public static final String PREFS_USER = "prefs_user";
    public static final String url = PregnancyTrackerURLS.URLSERVICE_LOGIN_PATIENTE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editusernamelogin = (EditText) findViewById(R.id.editusernamelogin);
        editpasswordlogin = (EditText) findViewById(R.id.editpasswordlogin);
        btnLogin = (CircularProgressButton) findViewById(R.id.btnlogin);
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        SharedPreferences preferences = getSharedPreferences(PREFS_USER, MODE_PRIVATE);
        final String userPrefs = preferences.getString("username", null);
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_USER, MODE_PRIVATE).edit();
        editor.putString("username", editusernamelogin.getText().toString());
        editor.commit();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
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
                                 Intent registerIntent = new Intent(MainActivity.this,HomeActivity.class);
                                 MainActivity.this.startActivity(registerIntent);
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
                        Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                        Patiente p = new Patiente();
                        try {

                            JSONArray array = new JSONArray(ServerResponse);
                            //   System.out.println("response legnth" + array.length());
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject d = array.getJSONObject(i);
                                p.setIdPatiente(d.getInt("idPatiente"));
                                p.setUsername(d.getString("username"));
                                p.setNbsemaine(d.getInt("nbsemaine"));
                                p.setNamePatiente(d.getString("namePatiente"));

                                //  String img = (d.getString("imcIdealMin"));

                                System.out.println("list for idspinner " + p.getUsername() + "id: " + p.getIdPatiente());
                                // waterF.setText(med.getImcIdealMax().toString());

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(MainActivity.this, "Logged In Successfully"+ p.getIdPatiente(), Toast.LENGTH_LONG).show();
                        // Finish the current Login activity.
                        logged = true;
                        // Showing Echo Response Message Coming From Server.


                        if(ServerResponse.trim().equals("no")) {
                            // If response matched then show the toast.
                            Toast.makeText(MainActivity.this, "aa"+ServerResponse, Toast.LENGTH_LONG).show();
                            logged=false;
                             }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
       // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }
}


