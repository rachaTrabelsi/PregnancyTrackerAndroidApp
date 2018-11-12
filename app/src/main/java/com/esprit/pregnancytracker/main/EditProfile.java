package com.esprit.pregnancytracker.main;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;




import com.esprit.pregnancytracker.Models.Medecin;

import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.esprit.pregnancytracker.utils.UrlAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EditProfile extends AppCompatActivity {

    private int GALLERY = 1, CAMERA = 2;
    public static ProgressDialog progressDialog;
    static RequestQueue mRequestQueue = null;
    String filePath;
    Bitmap imageBitmap;
       public  static int idmedecin;
    ArrayAdapter<String> dataAdapter;
    Spinner editMedecein;
    TextView changePic;
    EditText editName, editUserName, editMail, editBirthdate, editpassowrd, editconfirmPassword, nbsemaine;
    Button submitedit, canceledit;
    ImageView imgprofil;
    int day, month, year;
    private Gson gson;
    private String item;
    private static int RESULT_LOAD_IMAGE = 1;
    private static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public final  static String INFO_USER= "login";
      public   String idpatString ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idpatString =  getIntent().getStringExtra("CONNECTED_PATIENTE");
        Log.i("ConnectedUser",idpatString);
        setContentView(R.layout.activity_edit_profile);
        editName = (EditText) findViewById(R.id.namePat_edit);
        editUserName = (EditText) findViewById(R.id.edit_username);
        editpassowrd = (EditText) findViewById(R.id.password_edit);
        editconfirmPassword = (EditText) findViewById(R.id.confirmPassword_edit);
        editMail = (EditText) findViewById(R.id.email_edit);
        editBirthdate = (EditText) findViewById(R.id.newbirthdate_edit);
        submitedit = (Button) findViewById(R.id.btn_submit_editing);
        canceledit = (Button) findViewById(R.id.btn_cancel);
        changePic = (TextView) findViewById(R.id.change_picture);
        imgprofil = (ImageView) findViewById(R.id.img_profil);
        nbsemaine =(EditText) findViewById(R.id.nbresemaine_edit) ;

        editName.setText(PatienteSingleton.getInstance().getNamepatiente());
        editUserName.setText(PatienteSingleton.getInstance().getUsername());


        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date)formatter.parse(PatienteSingleton.getInstance().getBirthdate());
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(EditProfile.this, "you must enter the due date", Toast.LENGTH_LONG).show();

        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
        final  String duedate = newFormat.format(date);




        editBirthdate.setText(duedate);
        editMail.setText(PatienteSingleton.getInstance().getEmail());
        nbsemaine.setText(String.valueOf(PatienteSingleton.getInstance().getNbsemaine()));
        List<String> listIds = GetDoctors();
        System.out.print(listIds.size());
        changePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
        Picasso.with(getApplicationContext()).load(PregnancyTrackerURLS.URL_IMAGES_PATIENTES+PatienteSingleton.getInstance().getImage()).fit().centerCrop()
                .placeholder(R.drawable.female)
                .error(R.drawable.female)
                .into(imgprofil);



        final SharedPreferences sharedPreferences=getSharedPreferences(INFO_USER,MODE_PRIVATE);

        submitedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetDoctors ();
                if (editName.getText().toString().trim() != null
                       && editUserName.getText().toString().trim() != null
                        && editpassowrd.getText().toString().trim()!= null
                        && editconfirmPassword.getText().toString().trim() != null
                        && editMail.getText().toString().trim() != null
                        && editBirthdate.getText().toString().trim() != null

                        && nbsemaine.getText().toString().trim() != null ) {
                   updateProfilePatiente();
                }else {

                    Toast.makeText(EditProfile.this, "You should fill all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        editMedecein = (Spinner) findViewById(R.id.edit_doctor);




        editMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextWatcher EmailEntryWatcher = new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String working = s.toString();
                        boolean isValid = true;

                                if (working.equals("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                                    isValid = true;
                                    working += working;
                                    editMail.setText(working);
                                    editMail.setSelection(working.length());
                                } else {
                                    isValid = false;
                                }


                                if (!isValid) {
                                    editMail.setError("Enter a valid mail");
                                    Toast.makeText(getBaseContext(), "Enter a valid mail", Toast.LENGTH_LONG);
                                } else {
                                    ///edtdate.setError(null);
                                }


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                };

            }
        });

        canceledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCancel = new Intent(EditProfile.this, ShowProfile.class);
                startActivity(intentCancel);

            }
        });


    }





    public void updateProfilePatiente() {
        String start_dt = editBirthdate.getText().toString();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = (Date)formatter.parse(start_dt);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(EditProfile.this, "you must enter the due date", Toast.LENGTH_LONG).show();

        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        final  String duedate = newFormat.format(date);

        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
       String url = PregnancyTrackerURLS.URLSERVICE_UPDATE_PATIENTE_PROFIL+"idPatiente="+idpatString+"&namePatiente="+editName.getText().toString().trim()
                +"&username="+editUserName.getText().toString().trim()
                +"&password="+editpassowrd.getText().toString().trim()
                +"&confirmpassword="+editconfirmPassword.getText().toString().trim()
                +"&email="+editMail.getText().toString().trim()
                +"&birthdate="+duedate
                +"&medecindirect="+idmedecin
               +"&nbsemaine="+nbsemaine.getText().toString().trim();

        StringRequest strReq = new StringRequest( url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("responseUpdate", response);
                System.out.println("respoooooooooooooooooooooooooooonse");

                PatienteSingleton.getInstance().setUsername(editUserName.getText().toString().trim());
                PatienteSingleton.getInstance().setNbsemaine(Integer.parseInt(nbsemaine.getText().toString().trim()));
                PatienteSingleton.getInstance().setNamepatiente(editName.getText().toString().trim());
                PatienteSingleton.getInstance().setPassword(editpassowrd.getText().toString().trim());
                PatienteSingleton.getInstance().setConfirmpassword(editconfirmPassword.getText().toString().trim());
                PatienteSingleton.getInstance().setBirthdate(duedate);
                PatienteSingleton.getInstance().setEmail(editMail.getText().toString().trim());
                PatienteSingleton.getInstance().setIdMedecin(idmedecin);
                getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().clear();
                getSharedPreferences(INFO_USER,MODE_PRIVATE).edit().commit();

                SharedPreferences.Editor editor=getSharedPreferences(INFO_USER,MODE_PRIVATE).edit();
                editor.putString("login",editUserName.getText().toString().trim());
                editor.putString("mdp",editpassowrd.getText().toString().trim());

                editor.putString("nbsemaine",nbsemaine.getText().toString().trim());
                editor.putString("namePatiente",editName.getText().toString().trim());
                editor.putString("username",editUserName.getText().toString().trim());
                editor.putString("email",editMail.getText().toString().trim());
                editor.putString("birthdate",duedate);
                editor.putInt("medecindirect",idmedecin);
                editor.commit();
                /* editor.putString("image",d.getString("image"));
                editor.commit();*/
                Toast.makeText(EditProfile.this , "Your Profile successfully updated ", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("responseUpdate", "errreu update");





            }
        });


        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }


    public  void pickdateAction (View v) {

        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month= cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),new DatePickerDialog.OnDateSetListener(){



            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                editBirthdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        }, year,month,day);
        datePickerDialog.setTitle("Update your birthdate");
        datePickerDialog.show();



    }
      public List<String> GetDoctors (){
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequestdoctorrrr";
        final List<String> medecinsforSpinner = new ArrayList<>();
        medecinsforSpinner.add("None");
        String  url = PregnancyTrackerURLS.URLSERVICEMEdecin;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(REQUEST_TAG, response.toString());

                System.out.println(response);
                try {

                    JSONArray array = new JSONArray(response);
                    System.out.println("response legnth" + array.length());
                    for (int i = 0; i < array.length(); i++) {
                        Medecin med = new Medecin();
                        JSONObject d = array.getJSONObject(i);
                        System.out.println("for spinner" + d.getString("nameMedecin"));
                        String name = d.getString("nameMedecin");
                        medecinsforSpinner.add(name);
                        System.out.println("list for spinner " + medecinsforSpinner.size());

                    }
                    dataAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_dropdown_item, medecinsforSpinner);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    editMedecein.setAdapter(dataAdapter);

                    editMedecein.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            item = adapterView.getItemAtPosition(i).toString();
                            System.out.println(item);
                            // Showing selected spinner item
                            Log.i("doctorpicked:", item);
                            if (i==0){
                                idmedecin = -1;
                            }else{
                                idmedecin =i;
                            }




                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);

        return  medecinsforSpinner;
    }




    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA);
            startActivityForResult(intent, CAMERA);

        }
        startActivityForResult(intent, CAMERA);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null )
        {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);
            filePath = getPath(tempUri, getApplicationContext());


            imgprofil.setImageBitmap(imageBitmap);
            progressDialog = new ProgressDialog(EditProfile.this);
            progressDialog.setMessage("Uploading, please wait...");
            progressDialog.show();

            filePath = getPath(tempUri, getApplicationContext());
            AddImage(filePath, getApplicationContext());

        }
        if  (requestCode == GALLERY && resultCode == RESULT_OK && data != null )
        {
            imgprofil.setImageBitmap(imageBitmap);
            progressDialog = new ProgressDialog(EditProfile.this);
            progressDialog.setMessage("Uploading, please wait...");
            progressDialog.show();
            Uri tempUri = data.getData();


            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), tempUri);
                filePath = getPath(tempUri, getApplicationContext());
                imgprofil.setImageBitmap(imageBitmap);
                AddImage(filePath, getApplicationContext());


            } catch (IOException e) {
                e.printStackTrace();
            }




        }







    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



    public static String getPath(Uri contentUri,Context ctx) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(ctx, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    public void AddImage(final String imagePath, final Context ctx) {

        Log.e("multpart","ok");
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST,PregnancyTrackerURLS.URLSERVICE_UPDATE_IMG_PATIENTE ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        Toast.makeText(EditProfile.this, "Uploaded Successful", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("multpart",error.getMessage());
            }
        });
        smr.setRetryPolicy(new DefaultRetryPolicy(10000,5,5));
        smr.addFile("img", imagePath);
        Log.i("file","hello"+imagePath);
        smr.addStringParam("idPatiente",String.valueOf(PatienteSingleton.getInstance().getIdpatiente()));
        Log.e("smr","smr");
        mRequestQueue = Volley.newRequestQueue(ctx);
        mRequestQueue.add(smr);

    }


}

