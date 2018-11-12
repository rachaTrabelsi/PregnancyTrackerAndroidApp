package com.esprit.pregnancytracker.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esprit.pregnancytracker.Adapters.CustomGrid;
import com.esprit.pregnancytracker.Adapters.HistoriqueNotesAdapter;
import com.esprit.pregnancytracker.Fragments.UpdatingNoteFragment;
import com.esprit.pregnancytracker.Models.Dairy;
import com.esprit.pregnancytracker.Models.SelfBidou;
import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.AppSingleton;
import com.esprit.pregnancytracker.utils.PatienteSingleton;
import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.android.volley.request.SimpleMultiPartRequest;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.esprit.pregnancytracker.R.id.txt_cnxfailed;

public class SelfBidouActivity extends AppCompatActivity {
    int CONNECTEDUSER;
    static RequestQueue mRequestQueue = null;
    boolean IMAGE_STATUS = false;
    List<SelfBidou> selfies = new ArrayList<>();
    GridView grid;
    String filePath;
    ImageView signImage;
    public static ProgressDialog progressDialog;

    final List<SelfBidou> receveidSelf =new ArrayList<>();
    private int GALLERY = 1, CAMERA = 2;
    public static String urlImg = PregnancyTrackerURLS.URL_IMAGE_WATERS_SELF_BIDOU;
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_bidou);
        CONNECTEDUSER = PatienteSingleton.getInstance().getIdpatiente();
       Log.i ("idpatString" , String.valueOf(CONNECTEDUSER ));
        grid = (GridView) findViewById(R.id.gv_selfbidou);
        getSelfBidouByPatiente();

       SelfBidou slb1  = new SelfBidou("Month 1" , urlImg+"customizedplus.png");
        selfies.add(slb1);
        SelfBidou slb2  = new SelfBidou("Month 2" , urlImg+"customizedplus.png");
        selfies.add(slb2);
        SelfBidou slb3  = new SelfBidou("Month 3" , urlImg+"customizedplus.png");
        selfies.add(slb3);
        SelfBidou slb4  = new SelfBidou("Month 4" , urlImg+"customizedplus.png");
        selfies.add(slb4);
        SelfBidou slb5  = new SelfBidou("Month 5" , urlImg+"customizedplus.png");
        selfies.add(slb5);
        SelfBidou slb6  = new SelfBidou("Month 6" , urlImg+"customizedplus.png");
        selfies.add(slb6);
        SelfBidou slb7 = new SelfBidou("Month 7" , urlImg+"customizedplus.png");
        selfies.add(slb7);
        SelfBidou slb8  = new SelfBidou("Month 8" , urlImg+"customizedplus.png");
        selfies.add(slb8);
        SelfBidou slb9  = new SelfBidou("Month 9" , urlImg+"customizedplus.png");
        selfies.add(slb9);
        /*CustomGrid adapter = new CustomGrid(SelfBidouActivity.this,selfies);
        grid.setAdapter(adapter);*/
      grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.i("selectedGrid Item", selfies.get(i).getImageSelfi());
                showPictureDialog();
               // signImage = adapterView.

            }
        });

    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.connexionfaileddialog, null);
        dialogBuilder.setView(dialogView);

        final TextView edt =  dialogView.findViewById(txt_cnxfailed);

        //dialogBuilder.setTitle("Custom dialog");
       // dialogBuilder.setMessage("Enter text below");
        ImageView image =  dialogView.findViewById(R.id.imgdialog_cnx_failed);
        AlertDialog b = dialogBuilder.create();
        b.show();
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

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null )
        {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);
            filePath = getPath(tempUri, getApplicationContext());


            imgprofil.setImageBitmap(imageBitmap);
            progressDialog = new ProgressDialog(SelfBidouActivity.this);
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







    }*/
    public void  getSelfBidouByPatiente(){

        Log.i("oooooo",String.valueOf(CONNECTEDUSER ));
        final String  REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        ///PregnancyTrackerServices/CRUDSELFBIDOU/GetSefBidouByPatiente.php?
        String url = PregnancyTrackerURLS.URL_SERVICE_GET_SELFBIDOU_BY_PATIENTE+"idPatiente="+CONNECTEDUSER;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {


                Log.i(REQUEST_TAG, response.toString());
                try {
                    Log.d("Debug", response.toString());
                    JSONArray noteArray = new JSONArray(response);
                    for (int i =0; i< noteArray.length(); i++){
                        Log.d("Debugfor", response.toString());
                        SelfBidou self = new SelfBidou();
                        JSONObject selffromDB = noteArray.getJSONObject(i);
                        selfies.get(i).setImageSelfi(PregnancyTrackerURLS.URL_IMAGE_WATERS_SELF_BIDOU+selffromDB.getString("nameImage"));


                        Log.e("esmj esm image",   PregnancyTrackerURLS.URL_IMAGE_WATERS_SELF_BIDOU+selffromDB.getString("nameImage"));
                        System.out.print( selffromDB.getString("nameImage"));

                    }

                    CustomGrid adapter = new CustomGrid(SelfBidouActivity.this,selfies);

                    grid.setAdapter(adapter);
                    grid.deferNotifyDataSetChanged();

                   /* CustomGrid adapter = new CustomGrid(SelfBidouActivity.this,selfies);
                    grid.setAdapter(adapter);
                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String  selectedItem = adapterView.getItemAtPosition(i).toString();
                            Toast.makeText(getApplicationContext(), selectedItem,Toast.LENGTH_LONG).show();
                        }
                    });*/

                } catch (JSONException e) {
                    e.printStackTrace();
                    //hne n'afichi elli ma femma
                    Log.d("mafemmechdonne","hne n'afichi elli ma femma chay");
                   // showChangeLangDialog();
                    CustomGrid adapter = new CustomGrid(SelfBidouActivity.this,selfies);
                    grid.setAdapter(adapter);
                    grid.deferNotifyDataSetChanged();

                }
                System.out.print("***********YEEEEEEEEEEEESSSSSSS********");

            }


        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());

                System.out.print("***********Noooooooooooo********");
                Log.d("mafemmecnx","cnx9assity");
                showChangeLangDialog();
                CustomGrid adapter = new CustomGrid(SelfBidouActivity.this,selfies);
                grid.setAdapter(adapter);
                grid.deferNotifyDataSetChanged();
            }
        });

        System.out.print("***********Noooooooooooo********");

        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);


    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == 1) {

            Bitmap b = BitmapFactory.decodeByteArray(
                    data.getByteArrayExtra("byteArray"), 0,
                    data.getByteArrayExtra("byteArray").length);
            signImage.setImageBitmap(b);
            IMAGE_STATUS = true;
            Uri tempUri = getImageUri(getApplicationContext(), b);
            filePath = getPath(tempUri,getApplicationContext());
        }
    }



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static String getPath(Uri contentUri, Context ctx) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(ctx, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private boolean validateImage() {
        if (!IMAGE_STATUS)
            Toast.makeText(this, "Image ne peut pas etre vide", Toast.LENGTH_SHORT).show();
        return IMAGE_STATUS;
    }

    public static void AddImage(final String imagePath, final Context ctx) {
        Toast.makeText(ctx, "ahla", Toast.LENGTH_LONG).show();
        //Log.e("multpart",error.getMessage());
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(SimpleMultiPartRequest.Method.POST, PregnancyTrackerURLS.URL_SERVICE_INSERT_SELFBIDOU_BY_PATIENTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        //  Toast.makeText(ctx, response, Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_LONG).show();
//                Log.e("multpart",error.getMessage());
            }
        });
        smr.setRetryPolicy(new DefaultRetryPolicy(10000,5,5));
        smr.addFile("img", imagePath);
        Log.i("file","hello"+imagePath);
        smr.addStringParam("idPatiente",String.valueOf(PatienteSingleton.getInstance().getIdpatiente()));
        Log.e("smr","smr");
        //MyApplication.getInstance().addToRequestQueue(smr);
        mRequestQueue = Volley.newRequestQueue(ctx);
        mRequestQueue.add(smr);

    }

}
