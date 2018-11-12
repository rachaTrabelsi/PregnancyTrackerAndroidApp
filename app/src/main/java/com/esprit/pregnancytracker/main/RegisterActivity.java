package com.esprit.pregnancytracker.main;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;


import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    String filePath;
    Bitmap imageBitmap;
    static RequestQueue mRequestQueue = null;
    private int GALLERY = 1, CAMERA = 2;
    public static ProgressDialog progressDialog;
    EditText edtfirstname, edtusername, edtemail, edtpassword, edtconfirmpassword,edtbirthdate,edtlastname,edtweeks;
    Button btnSave,btnUpload;
    ImageView imguser;
    Bitmap bitmap;
    public String idpatadded;
    String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    final int CODE_GALLERY_REQUEST = 999;
    int j,m,a,yy;
    public static final String urlRegister = PregnancyTrackerURLS.URLSERVICE_REGISTER_PATIENTE ;

    public RegisterActivity() {
        idpatadded = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtfirstname = (EditText)findViewById(R.id.edtfirstname);
        edtweeks = (EditText)findViewById(R.id.edtweeks);
        edtusername = (EditText)findViewById(R.id.edtusername);
        edtemail = (EditText)findViewById(R.id.edtemail);
        edtpassword = (EditText)findViewById(R.id.edtpassword);
        edtconfirmpassword = (EditText)findViewById(R.id.edtconfirmparssword);
        edtbirthdate = (EditText)findViewById(R.id.edtbirthdate);
        btnSave = (Button) findViewById(R.id.btnsave);
        btnUpload  = (Button) findViewById(R.id.btncamera);
        imguser = (ImageView) findViewById(R.id.imageuser);

        edtbirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                j = c.get(Calendar.DAY_OF_MONTH);
                m=c.get(Calendar.MONTH);
                a=c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        edtbirthdate.setText(year+"/"+month+"/"+day);
                        yy=year;
                    }


                }
                        ,j,m,a);


                datePickerDialog.show();

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edtemail.getText().toString().matches(emailPattern)) && (edtpassword.getText().toString().equals(edtconfirmpassword.getText().toString()))) {
                    registerUser();
                }
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
               /* ActivityCompat.requestPermissions(
                        RegisterActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );*/
            }
        });
    }

 /*   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length> 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),CODE_GALLERY_REQUEST);
            }
            else{
                Toast.makeText(RegisterActivity.this,"you don't have permission to access ! ",Toast.LENGTH_LONG).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/
  /*  private String  ImageToString (Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte [] imageByte = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageByte,Base64.DEFAULT);
        return encodedImage;
    }
*/
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imguser.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    private void registerUser (){
        //Toast.makeText(RegisterActivity.this, "ok", Toast.LENGTH_LONG).show();
        final String username = edtusername.getText().toString().trim();
        final String firstname = edtfirstname.getText().toString().trim();
        final String email = edtemail.getText().toString().trim();
        final String password = edtpassword.getText().toString().trim();
        final String confirmpassword = edtconfirmpassword.getText().toString().trim();
        final String birthdate = edtbirthdate.getText().toString().trim();
        final String weeks = edtweeks.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!(username.equals(""))&&!(firstname.equals(""))&&!(email.equals(""))&&!(password.equals(""))&&!(confirmpassword.equals(""))&&!(birthdate.equals(""))&&!(weeks.equals(""))) {
                    if (!(password.equals(confirmpassword))) {
                        Toast.makeText(RegisterActivity.this, "The password doesn't match the confirm password !", Toast.LENGTH_LONG).show();
                    }
                    if (email.matches(emailPattern)) {

                        MDToast.makeText(RegisterActivity.this, "valid email address",
                                MDToast.LENGTH_SHORT).show();
                        progressDialog = new ProgressDialog(RegisterActivity.this);
                        progressDialog.setMessage("Uploading, please wait...");
                        progressDialog.show();
                        Log.i("responseRegister",response);

                        idpatadded= response;
                      //  AddImage(filePath, getApplicationContext());

                        Intent i = new Intent(RegisterActivity.this ,LoginActivity.class);
                        startActivity(i);
                    } else {
                        MDToast.makeText(RegisterActivity.this, "Invalid email address",
                                MDToast.TYPE_WARNING).show();
                        // mail.setBackgroundResource(R.drawable.edit_red_line);
                    }
                }
                else {
                    MDToast mdToast = MDToast.makeText(RegisterActivity.this, "Some informations are missing", 5, MDToast.TYPE_ERROR);
                    mdToast.show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        MDToast mdToast = MDToast.makeText(RegisterActivity.this, "No connection", 5, MDToast.TYPE_ERROR);
                        mdToast.show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
              //  String imageData =add+bitmap.toString()+".jpg";
                params.put("username", username);
                params.put("password", password);
                params.put("dateinscription", currentdate);
                params.put("confirmpassword", confirmpassword);
                params.put("namePatiente", firstname);
                params.put("email", email);
                params.put("birthdate", birthdate);
               // params.put("image", imageData);
                params.put("nbsemaine",weeks);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
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


            imguser.setImageBitmap(imageBitmap);
            /*progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Uploading, please wait...");
            progressDialog.show();*/

          /*  filePath = getPath(tempUri, getApplicationContext());
            AddImage(filePath, getApplicationContext());*/

        }
        if  (requestCode == GALLERY && resultCode == RESULT_OK && data != null )
        {
            imguser.setImageBitmap(imageBitmap);
           /* progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Uploading, please wait...");
            progressDialog.show();*/
            Uri tempUri = data.getData();


            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), tempUri);
                filePath = getPath(tempUri, getApplicationContext());
                imguser.setImageBitmap(imageBitmap);
                //AddImage(filePath, getApplicationContext());


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

        //Log.e("multpart","ok");
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST,PregnancyTrackerURLS.URLSERVICE_UPDATE_IMG_PATIENTE ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        MDToast.makeText(RegisterActivity.this, "Uploaded Successful", MDToast.TYPE_SUCCESS).show();
                        Log.i("responseadd",response);


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
        Log.i("smrId","hello"+idpatadded);
        smr.addStringParam("idPatiente",idpatadded);

        mRequestQueue = Volley.newRequestQueue(ctx);
        mRequestQueue.add(smr);

    }


}
