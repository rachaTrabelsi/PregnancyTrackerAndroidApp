package com.esprit.pregnancytracker.service;



        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v4.content.LocalBroadcastManager;
        import android.util.Log;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.error.VolleyError;
        import com.android.volley.request.StringRequest;
        import com.esprit.pregnancytracker.Models.Patiente;
        import com.esprit.pregnancytracker.utils.AppSingleton;
        import com.esprit.pregnancytracker.utils.PatienteSingleton;
        import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
        import com.google.firebase.iid.FirebaseInstanceId;
        import com.google.firebase.iid.FirebaseInstanceIdService;

        import com.esprit.pregnancytracker.app.Config;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "sendRegistrationToServer:" + refreshedToken);
        Log.e("rigId" , refreshedToken);
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
        Log.e("Taktouk", token);

       final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        String URL= PregnancyTrackerURLS.URL_SERVICE_INSERT_FCMtoken+"idPatiente="
                + PatienteSingleton.getInstance().getIdpatiente() + "&token=" + token;
        StringRequest tokenrequest = new StringRequest(Request.Method.GET,  URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("successInsert", response);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("successInsert","fail");
            }
        }
        );
        AppSingleton.getInstance(getBaseContext()).addToRequestQueue(tokenrequest, REQUEST_TAG);







    }



    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        pref.edit().clear();
        editor.putString("regId", token);
        editor.commit();
    }
}