package com.esprit.pregnancytracker.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;


import com.esprit.pregnancytracker.Models.Patiente;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;

import com.esprit.pregnancytracker.utils.ValuesClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;


public class ShowProfile extends AppCompatActivity {

    TextView  usernametv ,duedatetv, emailtv,tv_changepic ;


    public final  static String CONNECTED_USER=String.valueOf(PatienteSingleton.getInstance().getIdpatiente());
    private int GALLERY = 1, CAMERA = 2;
   public static ProgressDialog progressDialog;
    private ImageView profilPicture;
    Button btn_edit;
    String filePath;
    Bitmap imageBitmap;
    static RequestQueue mRequestQueue = null;
    public static Patiente patiente = new Patiente() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Log.i("CONNECTED_USER",CONNECTED_USER);
        setContentView(R.layout.activity_show_profile);
        profilPicture = (ImageView) findViewById(R.id.profilPicture);
        tv_changepic = (TextView) findViewById(R.id.changeavatar);
        usernametv = (TextView) findViewById(R.id.Result_username);
        duedatetv = (TextView) findViewById(R.id.Result_birdhdate);
        emailtv = (TextView) findViewById(R.id.Result_email);
        btn_edit = (Button) findViewById(R.id.btn_edit_profil);
        tv_changepic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowProfile.this, EditProfile.class);
                i.putExtra("CONNECTED_PATIENTE", CONNECTED_USER);

                startActivity(i);

            }
        });
        usernametv.setText(PatienteSingleton.getInstance().getUsername());
        duedatetv.setText(PatienteSingleton.getInstance().getBirthdate());
        emailtv.setText(PatienteSingleton.getInstance().getEmail());

        Picasso.with(getApplicationContext()).load(PregnancyTrackerURLS.URL_IMAGES_PATIENTES+PatienteSingleton.getInstance().getImage()).fit().centerCrop()
                .placeholder(R.drawable.female)
                .error(R.drawable.female)
                .into(profilPicture);


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


            profilPicture.setImageBitmap(imageBitmap);
            progressDialog = new ProgressDialog(ShowProfile.this);
            progressDialog.setMessage("Uploading, please wait...");
            progressDialog.show();

            filePath = getPath(tempUri, getApplicationContext());
            AddImage(filePath, getApplicationContext());

        }
        if  (requestCode == GALLERY && resultCode == RESULT_OK && data != null )
        {
            profilPicture.setImageBitmap(imageBitmap);
            progressDialog = new ProgressDialog(ShowProfile.this);
            progressDialog.setMessage("Uploading, please wait...");
            progressDialog.show();
            Uri tempUri = data.getData();


            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), tempUri);
                filePath = getPath(tempUri, getApplicationContext());
                profilPicture.setImageBitmap(imageBitmap);
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
                        Toast.makeText(ShowProfile.this, "Uploaded Successful", Toast.LENGTH_LONG).show();

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
