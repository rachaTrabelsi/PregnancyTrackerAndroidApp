package com.esprit.pregnancytracker.main;


        import android.app.AlarmManager;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.BitmapFactory;
        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.net.ParseException;
        import android.os.Build;
        import android.provider.ContactsContract;
        import android.support.annotation.IdRes;
        import android.support.annotation.RequiresApi;
        import android.support.design.internal.NavigationMenu;
        import android.support.v4.app.NotificationCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;

        import com.android.volley.VolleyLog;

        import com.android.volley.error.VolleyError;
        import com.android.volley.request.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.esprit.pregnancytracker.Models.Notification;

        import com.esprit.pregnancytracker.R;
        import com.esprit.pregnancytracker.utils.AlarmReceiverbb;
        import com.esprit.pregnancytracker.utils.DataSourceNotif;
        import com.esprit.pregnancytracker.utils.PatienteSingleton;
        import com.esprit.pregnancytracker.utils.PregnancyTrackerURLS;
        import com.esprit.pregnancytracker.utils.notification;
        import com.hitomi.cmlibrary.CircleMenu;
        import com.hitomi.cmlibrary.OnMenuSelectedListener;
        import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
        import com.roughike.bottombar.BottomBar;
        import com.roughike.bottombar.OnTabReselectListener;
        import com.roughike.bottombar.OnTabSelectListener;
        import com.squareup.picasso.Picasso;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;
        import java.util.Locale;




public class HomeActivity extends AppCompatActivity {
    TextView namepatiente, nbweeks, titleweek, descweek, imc;
    ImageView imgbb;
    Button addbtn;
    static int weekyear;
    static  int upweek;
    public static final String urlselectnotification = PregnancyTrackerURLS.URLSERVICE_SELECT_NOTIFICATION ;

    CircleMenu circleMenu;
    notification not = new notification();
    String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//************************************************************************************************


        System.out.print("dddd" +PatienteSingleton.getInstance().getNbsemaine());

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBarHome);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_informations){
                    Intent intent = new Intent(HomeActivity.this,LifeStyleActivity.class);
                    startActivity(intent);
                }
                else if (tabId == R.id.tab_nearhospitals){
                    Intent intent = new Intent(HomeActivity.this,MapActivity.class);
                    startActivity(intent);
                }
                else if (tabId == R.id.tab_more){
                    Intent intent = new Intent(HomeActivity.this,MoreActivity.class);
                    startActivity(intent);
                }
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                }
            }
        });
        addbtn = (Button)findViewById(R.id.btnadd);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ImcActivity.class);
                startActivity(intent);
            }
        });
        //********************************************************************************
        notification();
        namepatiente = (TextView) findViewById(R.id.namepatiente);
        nbweeks = (TextView) findViewById(R.id.nbweeks);
        titleweek = (TextView) findViewById(R.id.titleweek);
        descweek = (TextView) findViewById(R.id.descweek);
        imgbb = (ImageView) findViewById(R.id.bbimg);
        Calendar calendar = Calendar.getInstance();
        if ((calendar.get(Calendar.DAY_OF_WEEK))== 3) {
            NotificationCompat.Builder notificationbuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(HomeActivity.this)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_friends)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.week4))
                    .setContentTitle("notification title")
                    .setContentText("notification text");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationbuilder.build());}

        //******************************************Notification*******************************************
        Calendar sCalendar = Calendar.getInstance();
        String dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        if (!(currentdate.equals(PatienteSingleton.getInstance().getDateinscription()))&&dayLongName.equals("Thursday")){
            updateweek();
            Calendar calender = Calendar.getInstance();
            //Log.d("Current Week:" + calender.get(Calendar.WEEK_OF_YEAR));
            PatienteSingleton.getInstance().setNbsemaine(PatienteSingleton.getInstance().getNbsemaine()+1);
            Toast.makeText(this,"matchy matchy "+calendar.get(Calendar.WEEK_OF_YEAR),Toast.LENGTH_LONG).show();


         /* try {
                String dob = "2018/02/15";
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date d = sdf.parse(dob);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                int week = cal.get(Calendar.WEEK_OF_YEAR);
                Toast.makeText(this,"matchy week  "+week,Toast.LENGTH_LONG).show();

            } catch (ParseException e) {
                e.printStackTrace();
            }*/

        }


        //*************************************************************************************************
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        int month = rightNow.get(Calendar.MONTH);
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        weekyear = rightNow.get(Calendar.WEEK_OF_YEAR);
        //Toast.makeText(this,currentHour,Toast.LENGTH_LONG).show();
        System.out.print("heure actuelle " +currentHour);
        LinearLayout v = (LinearLayout) findViewById(R.id.homeLayout);
      /* if ((currentHour >= 18) && (currentHour <= 00)) {
            v.setBackgroundResource(R.drawable.nighthomepage);
            namepatiente.setTextColor(Color.WHITE);
            nbweeks.setTextColor(Color.WHITE);
        } else if ((currentHour < 18) && (currentHour > 00)) {
            v.setBackgroundResource(R.drawable.dayhomepage);
        }*/
        Toast.makeText(this, String.valueOf(currentHour), Toast.LENGTH_LONG).show();
        int id = getResources().getIdentifier("week" + weekyear, "drawable", this.getPackageName());
        imgbb.setImageResource(id);
        final String arrayActions[]={"AjoutFemme","InformationFemme","AjoutBebe","HistoriqueBebe"};


        namepatiente.setText(PatienteSingleton.getInstance().getUsername());
        nbweeks.setText((PatienteSingleton.getInstance().getNbsemaine()+" weeks"));
        GetNotification();
    }

    public void GetNotification() {

        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";



        String urlpass = urlselectnotification +"id="+ PatienteSingleton.getInstance().getNbsemaine();
        StringRequest strReq = new StringRequest(Request.Method.GET, urlpass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("la valeur de retour "+REQUEST_TAG, response.toString());
                //Patiente pat = gson.fromJson(response,Patiente.class);
                try {
                    JSONArray array = new JSONArray(response);
                    //   System.out.println("response legnth" + array.length());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject d = array.getJSONObject(i);
                        int id = d.getInt("idnotif");
                        descweek.setText(d.getString("descnotif"));
                        not.setTitrenotif(d.getString("titlenotif"));
                        titleweek.setText(not.getTitrenotif().toString());
                        not.setDescnotif(descweek.getText().toString());
                        not.setImagenotif(d.getString("imagenotif"));
                        Picasso.with(HomeActivity.this).load(PregnancyTrackerURLS.URL_IMAGE_WEEKS+d.getString("imagenotif")).into(imgbb);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                //  System.out.print("***********Noooooooooooo********");
                Notification n = new Notification();
                DataSourceNotif.setNotifications();
                n=DataSourceNotif.getNotificationById(PatienteSingleton.getInstance().getNbsemaine());

                descweek.setText(n.getDescnotif());
                titleweek.setText(n.getTitlenotif());
                Resources res = getResources();
                String mDrawableName = n.getImagenotif();
                int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resID );
                imgbb.setImageDrawable(drawable);

                Toast.makeText(HomeActivity.this,n.getTitlenotif(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(strReq);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void notification (){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar rightNow = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 1);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void notificationbb (){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(this, AlarmReceiverbb.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar rightNow = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 4);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }

    public void updateweek() {
        final String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";
        // final List<Medecin> medecinsforSpinner = new ArrayList<>();
        upweek=PatienteSingleton.getInstance().getNbsemaine()+1;
        String urlweek = PregnancyTrackerURLS.URLSERVICE_UPDATE_WEEK+ "nbsemaine="+upweek+"&idPatiente="+ PatienteSingleton.getInstance().getIdpatiente();
        StringRequest strReq = new StringRequest(Request.Method.GET, urlweek, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(REQUEST_TAG, response.toString());
                //Patiente pat = gson.fromJson(response,Patiente.class);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(REQUEST_TAG, "Error: " + error.getMessage());
                //  System.out.print("***********Noooooooooooo********");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(strReq);
    }

    public void actionbutton(){

    }
    @Override
    public void onBackPressed() {
        this.finish();
    }

}