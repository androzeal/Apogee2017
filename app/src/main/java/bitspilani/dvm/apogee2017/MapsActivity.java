package bitspilani.dvm.apogee2017;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    int currentapiVersion;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    final Activity activity=this;
    final int LOCATION_REQUEST_CONSTANT=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        currentapiVersion = android.os.Build.VERSION.SDK_INT;
        final String msg="Success";

        if(currentapiVersion>=23) {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {


                    if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                Manifest.permission.ACCESS_FINE_LOCATION)) {

                            // Show an expanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                        } else {

                            // No explanation needed, we can request the permission.

                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CONSTANT);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }
                    return msg;
                }

                @Override
                protected void onPostExecute(String s) {
//                Log.d("Neo", "Neo");

                    start();

                }
            }.execute(null, null, null);

        }
        else
        {
            start();
        }
}

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        Location l = new Location("dummy");
        for (String provider : providers) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                l = locationManager.getLastKnownLocation(provider);
            }
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        currentapiVersion = android.os.Build.VERSION.SDK_INT;
        Location myLocation = new Location("dummy");
        // Add a marker in Sydney and move the camera

        if(currentapiVersion<23) {
            try {
                mMap.setMyLocationEnabled(true);
            }
            catch (SecurityException e)
            {
                Log.d("Maps","Security error");
            }
        }


//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            myLocation = getLastKnownLocation();
        }
//        LatLng me=new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        LatLng gymg = new LatLng(28.359211, 75.590495);
        LatLng medc = new LatLng(28.357417, 75.591219);
//        LatLng srground = new LatLng(28.365923,75.587759);
        LatLng anc = new LatLng(28.360346, 75.589632);
        LatLng sac = new LatLng(28.360710, 75.585639);
        LatLng fd3 = new LatLng(28.363988, 75.586274);
        LatLng clocktower = new LatLng(28.363906, 75.586980);
        LatLng fd2 = new LatLng(28.364059, 75.587873);
        LatLng uco = new LatLng(28.363257, 75.590715);
        LatLng icici = new LatLng(28.357139, 75.590436);
        LatLng axis = new LatLng(28.361605, 75.585046);
        LatLng fk = new LatLng(28.361076, 75.585457);
        LatLng ltc = new LatLng(28.365056, 75.590092);
        LatLng nab = new LatLng(28.362228, 75.587346);


        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(clocktower).
                tilt(60).
                zoom(17).
                bearing(0).
                build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().position(anc).title("ANC").snippet("All Night Canteen"));
        mMap.addMarker(new MarkerOptions().position(sac).title("SAC").snippet("Student Activity Center"));
        mMap.addMarker(new MarkerOptions().position(fd3).title("FD3").snippet("Faculty Division-III(31xx-32xx)"));
        mMap.addMarker(new MarkerOptions().position(clocktower).title("Clock Tower").snippet("Auditorium"));
        mMap.addMarker(new MarkerOptions().position(fd2).title("FD2").snippet("Faculty Division-II(21xx-22xx)"));
        mMap.addMarker(new MarkerOptions().position(uco).title("UCO Bank ATM"));
        mMap.addMarker(new MarkerOptions().position(icici).title("ICICI ATM"));
        mMap.addMarker(new MarkerOptions().position(axis).title("AXIS Bank ATM"));
        mMap.addMarker(new MarkerOptions().position(fk).title("FoodKing").snippet("Restaurant"));
        mMap.addMarker(new MarkerOptions().position(ltc).title("LTC").snippet("Lecture Theater Complex(510x)"));
        mMap.addMarker(new MarkerOptions().position(nab).title("NAB").snippet("New Academic Block(60xx-61xx)"));
        mMap.addMarker(new MarkerOptions().position(gymg).title("GYMG").snippet("Gym Grounds"));
        mMap.addMarker(new MarkerOptions().position(medc).title("MedC").snippet("Medical Center"));
//        mMap.addMarker(new MarkerOptions().position(me).title("You are here!").snippet("Consider yourself located"));
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setBuildingsEnabled(true);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CONSTANT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    try {
                        mMap.setMyLocationEnabled(true);
                    }
                    catch (SecurityException e)
                    {
                        Log.d("Maps","Security error");
                    }

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    finishActivity(0);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void start()
    {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER

    }

    }
