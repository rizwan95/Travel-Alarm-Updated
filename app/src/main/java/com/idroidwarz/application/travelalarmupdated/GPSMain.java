package com.idroidwarz.application.travelalarmupdated;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class GPSMain extends Activity implements LocationListener {

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

      /*  if (datastring == null) {
            datastring = "Null data";
        }
        EditText tv = (EditText) findViewById(R.id.latView);
        tv.setText(datastring);
        EditText longvieww = (EditText) findViewById(R.id.longview);
        longvieww.setText(lonstring);
*/
        /********** get Gps location service LocationManager object ***********/
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                /* CAL METHOD requestLocationUpdates */

        // Parameters :
        //   First(provider)    :  the name of the provider with which to register
        //   Second(minTime)    :  the minimum time interval for notifications,
        //                         in milliseconds. This field is only used as a hint
        //                         to conserve power, and actual time between location
        //                         updates may be greater or lesser than this value.
        //   Third(minDistance) :  the minimum distance interval for notifications, in meters
        //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location)
        //                         method will be called for each location update


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000,   // 3 sec
                1, this);

        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/
    }

    /**
     * ********** Called after each 3 sec *********
     */
    @Override
    public void onLocationChanged(Location location) {

        Intent i = getIntent();
        Bundle databundle = i.getExtras();
        String datastring = databundle.getString("DATA");
        String lonstring = databundle.getString("DATA2");

        double curr_lat = location.getLatitude();

        double curr_long = location.getLongitude();

        String currlat = Double.toString(curr_lat);
        String currlong = Double.toString(curr_long);

        if(!currlat.equals(datastring)&&!currlong.equals(lonstring)){
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
            mp.start();


        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderDisabled(String provider) {

        /******** Called when User off Gps *********/

        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        /******** Called when User on Gps  *********/

        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

}